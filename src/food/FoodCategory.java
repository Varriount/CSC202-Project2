package food;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import utils.ItemList;
import utils.util;

/**
 * A category of food.
 * @author Clay
 *
 */
public class FoodCategory implements Serializable {
    private static final long serialVersionUID = -928411561883957471L;
    ItemList<FoodItem> items;
    String name;

    public FoodCategory(ItemList<FoodItem> items, String name) {
	super();
	this.items = items;
	this.name = name;
    }

    public FoodCategory(String string) {
	super();
	this.items = new ItemList<>();
	this.name = string;
    }
    
    public FoodCategory() {
	super();
	this.items = new ItemList<>();
	this.name = "";
    }

    /**
     * Retrieves the food items that the category holds.
     * @return The food items the category holds.
     */
    public ItemList<FoodItem> getItems() {
	return items;
    }

    /**
     * Sets the items the category holds.
     * @param items
     */
    public void setItems(ItemList<FoodItem> items) {
	this.items = items;
    }

    /**
     * Gets the name of the food category.
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name of the food category.
     * @param name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Load the food category from a scanner object.
     * 
     * @param input
     *            The scanner object to retrieve input from.
     */
    public void loadFromScanner(Scanner input) {
	name = input.nextLine();
	util.println(name);
	
	int numberOfFoods = input.nextInt();
	util.println(numberOfFoods);
	
	for (int i = 0; i < numberOfFoods; i++) {
	    FoodItem item = new FoodItem();
	    item.loadFromScanner(input);
	    items.add(item);
	}
    }
    
    /**
     * Writes a string form of the food category that can be parsed by a scanner and
     * the `loadFromScanner` method.
     * 
     * @param output The writer to write output to.
     * @throws IOException
     */
    public void writeToStream(PrintWriter output) throws IOException {
	output.println(name);
	output.println(items.size());
	for (FoodItem element : items) {
	    element.writeToStream(output);
	}
    }

    /**
     * Add a food item to the category.
     * @param element
     */
    public void add(FoodItem element) {
	items.add(element);
    }

}
