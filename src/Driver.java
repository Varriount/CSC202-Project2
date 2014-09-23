import java.io.IOException;
import java.math.BigDecimal;

import utils.util;
import food.CategoryList;
import food.FoodCategory;
import food.FoodItem;

public class Driver {
    public static CategoryList categories;

    public static void main(String[] args) throws IOException {
	categories = new CategoryList();
	util.println("Please select a run method:");
	util.println("1) Run with GUI (No Simulator)");
	util.println("2) Run with Console (Simulator)");
	util.println("");
	String option = util.readLine();

	if (option.equals("1")) {
	    VenderWindow window = new VenderWindow();
	    window.frame.setVisible(true);
	} else if (option.equals("2")) {
	    util.println("Filename?");
	    String filename = util.readLine();
	    util.println("");

	    util.println("Select loading method:");
	    util.println("1) Load from Excel File");
	    util.println("2) Load from Json File");
	    util.println("3) Load from Text File");
	    util.println("4) Load from Binary File");
	    String input = util.readLine();
	    util.println("");

	    if (input.equals("1")) {
		try {
		    categories.loadFromExcelFile(filename);
		} catch (IOException e) {
		    util.println("File not found");
		    e.printStackTrace();
		    System.exit(2);
		}
	    } else if (input.equals("2")) {
		try {
		    categories.loadFromJsonFile(filename);
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    util.println("File not found");
		    e.printStackTrace();
		    System.exit(2);
		}
	    } else if (input.equals("3")) {
		try {
		    categories.loadFromTextFile(filename);
		} catch (IOException e) {
		    util.println("File not found");
		    e.printStackTrace();
		    System.exit(2);
		}
	    } else if (input.equals("4")) {
		try {
		    categories.loadFromBinaryFile(filename);
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    util.println("File not found");
		    e.printStackTrace();
		    System.exit(2);
		}
	    }
	    runTests();
	}
    }

    /**
     * Run a series of tests of the held categoryList
     * @throws IOException 
     */
    private static void runTests() throws IOException {
	util.println("Starting state:");
	util.println(util.reprObject(categories));
	util.println("");

	FoodCategory snacksCategory = new FoodCategory("Snacks");
	FoodCategory drinksCategory = new FoodCategory("Drinks");

	FoodItem water = new FoodItem("Water", "I'ts wet.", "N/A",
		new BigDecimal(1.0), 500, 1);
	FoodItem juice = new FoodItem("Juice", "From an unknown fruit.",
		"Imported", new BigDecimal(5.0), 200, 5);
	FoodItem chips = new FoodItem("Chips", "Fried Potatoes.", "From Idaho",
		new BigDecimal(2.0), 100, 1);
	FoodItem salsa = new FoodItem("Salsa", "Spicy spicy salsa", "N/A",
		new BigDecimal(4.0), 60, 3);

	snacksCategory.add(chips);
	snacksCategory.add(salsa);
	drinksCategory.add(water);
	drinksCategory.add(juice);

	categories.add(snacksCategory);
	categories.add(drinksCategory);

	util.println("\n\n State after categories and foods have been added");
	util.println(util.reprObject(categories));

	FoodCategory targetCategory = categories.searchForCategory("Drinks");
	FoodItem targetFood = categories.searchForFood("Water").get(0);

	categories.delete(1);
	snacksCategory.getItems().delete(0);

	targetCategory.setName("Snack Foods");
	targetFood.setName("Low fat chips");

	util.println("\n\n State after categories and foods have been deleted and modified");
	util.println(util.reprObject(categories));
	
	categories.writeToBinaryFile("test.ser");
	categories.writeToJsonFile("test.ser");
    }
}
