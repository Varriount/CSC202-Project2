package food;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import utils.ItemList;
import utils.util;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

public class CategoryList extends ItemList<FoodCategory> {

    private static final long serialVersionUID = -979371385867290004L;

    public FoodCategory searchForCategory(String categoryName) {
	for (FoodCategory category : this) {
	    if (category.getName().compareTo(categoryName) == 0) {
		return category;
	    }
	}
	return null;
    }

    /**
     * Load a list of categories and their foods from a file saved using java's
     * native serialization routines.
     * 
     * @param filename
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromBinaryFile(String filename) throws IOException,
	    ClassNotFoundException {
	InputStream file = new FileInputStream(filename);
	InputStream buffer = new BufferedInputStream(file);
	ObjectInput input = new ObjectInputStream(buffer);

	CategoryList categories = (CategoryList) input.readObject();
	this.setHeadNode(categories.getHeadNode());

	input.close();
	file.close();
    }

    /**
     * Write the list of categories and their foods to a file using java's
     * native binary serialization routines.
     * 
     * @param filename
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void writeToBinaryFile(String filename) throws IOException {
	OutputStream file = new FileOutputStream(filename);
	OutputStream buffer = new BufferedOutputStream(file);
	ObjectOutput output = new ObjectOutputStream(buffer);
	output.writeObject(this);
	output.flush();
	output.close();
    }

    /**
     * Load a list of food categories and their food items from a text file.
     * 
     * @param filename
     *            The file to load from.
     * @throws IOException
     */
    public void loadFromTextFile(String filename) throws IOException {
	InputStream file = new FileInputStream(filename);
	Scanner input = new Scanner(file);
	int numberOfCategories = input.nextInt();
	util.println(numberOfCategories);

	for (int i = 0; i < numberOfCategories; i++) {
	    input.nextLine();
	    FoodCategory category = new FoodCategory();
	    category.loadFromScanner(input);
	    add(category);
	}

	input.close();
	file.close();
    }

    public void writeToTextFile(String filename) throws IOException {
	OutputStream file = new FileOutputStream(filename);
	PrintWriter output = new PrintWriter(file);
	output.write(this.size());
	for (FoodCategory category : this) {
	    category.writeToStream(output);
	}
    }

    /**
     * Load a list of food categories and their food items from a file
     * containing JSON formatted text.
     * 
     * @param filename
     *            The file to load from.
     * @throws IOException
     */
    public void loadFromJsonFile(String filename) throws IOException,
	    ClassNotFoundException {
	InputStream file = new FileInputStream(filename);
	JsonReader input = new JsonReader(file);
	CategoryList categories = (CategoryList) input.readObject();
	this.setHeadNode(categories.getHeadNode());

	input.close();
	file.close();
    }

    /**
     * Write the list of categories and their food items to a file, in JSON
     * format.
     * 
     * @param filename
     * @throws IOException
     */
    public void writeToJsonFile(String filename) throws IOException {
	HashMap<String, Object> options = new HashMap<>();
	options.put(JsonWriter.PRETTY_PRINT, true);

	OutputStream file = new FileOutputStream(filename);
	JsonWriter output = new JsonWriter(file, options);
	output.write(this);
	output.flush();
	output.close();
    }

    /**
     * Load a list of food categories and their food items from an excel file.
     * 
     * @param filename
     *            The file to load from.
     * @throws IOException
     */
    public void loadFromExcelFile(String filename) throws IOException {
	FileInputStream file = new FileInputStream(filename);
	HSSFWorkbook workbook = new HSSFWorkbook(file);
	HSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	rowIterator.next(); // Discard Column Names
	while (rowIterator.hasNext()) {
	    Row row = rowIterator.next();
	    Iterator<Cell> cellIterator = row.cellIterator();

	    // Category, Name, Description, Special, Price, Quantity, Size
	    String categoryName = cellIterator.next().getStringCellValue();
	    String name = cellIterator.next().getStringCellValue();
	    String description = cellIterator.next().getStringCellValue();
	    String specialOrder = cellIterator.next().getStringCellValue();
	    double priceString = cellIterator.next().getNumericCellValue();
	    double quantityString = cellIterator.next().getNumericCellValue();
	    double sizeString = cellIterator.next().getNumericCellValue();

	    FoodItem item = new FoodItem();
	    item.setName(name);
	    item.setDescription(description);
	    item.setSpecialOrder(specialOrder);
	    item.setPrice(new BigDecimal(priceString));
	    item.setQuantity(new Integer((int) quantityString));
	    item.setSize(new Integer((int) sizeString));

	    FoodCategory category = searchForCategory(categoryName);
	    if (category == null) {
		category = new FoodCategory();
		category.setName(categoryName);
		add(category);
	    }
	    category.getItems().add(item);
	}
	file.close();
    }

    /**
     * Search for a food item, by name, in the categories that the category list
     * holds.
     * 
     * @param foodName
     *            The name of the food to search for.
     * @return
     */
    public ArrayList<FoodItem> searchForFood(String foodName) {
	ArrayList<FoodItem> result = new ArrayList<>();
	for (FoodCategory category : this) {
	    for (FoodItem food : category.getItems()) {
		if (food.getName().contains(foodName)) {
		    result.add(food);
		}
	    }
	}
	return result;
    }
}
