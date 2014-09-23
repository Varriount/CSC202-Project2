package food;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;

import utils.util;

/**
 * An object representing a kind of food ordered on a menu, invoice, etc.
 * 
 * @author Clay
 *
 */
public class FoodItem implements Serializable {
    private static final long serialVersionUID = 5261677399709730207L;
    private String name;
    private String description;
    private String specialOrder;
    private BigDecimal price;
    private int quantity;
    private int size;

    public FoodItem(String name, String description, String specialOrder,
	    BigDecimal price, int quantity, int size) {
	super();
	this.name = name;
	this.description = description;
	this.specialOrder = specialOrder;
	this.price = price;
	this.quantity = quantity;
	this.size = size;
    }

    public FoodItem() {
	name = "";
	description = "";
	specialOrder = "";
	price = new BigDecimal(0.00);
	quantity = 0;
	size = 0;
    }

    /**
     * @return The name of the food item.
     */
    public String getName() {
	return name;
    }

    @Override
    public String toString() {
	return "FoodItem [name=" + name + ", description=" + description
		+ ", specialOrder=" + specialOrder + ", price=" + price
		+ ", quantity=" + quantity + ", size=" + size + "]";
    }

    /**
     * @param name
     *            The new name for the food item.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return The food item's description.
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            The food item's new description.
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return Note's pertaining to any special orders relating to the food
     *         item.
     */
    public String getSpecialOrder() {
	return specialOrder;
    }

    /**
     * @param specialOrder
     *            The notes to assign to the special order.
     */
    public void setSpecialOrder(String specialOrder) {
	this.specialOrder = specialOrder;
    }

    /**
     * @return The price of the food item.
     */
    public BigDecimal getPrice() {
	return price;
    }

    /**
     * @param price
     *            The new price of the food item.
     */
    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    /**
     * @return Amount of the food item.
     */
    public int getQuantity() {
	return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    /**
     * @return the size
     */
    public int getSize() {
	return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
	this.size = size;
    }

    /**
     * Load the food object from a scanner object.
     * 
     * @param input
     *            The scanner object to retrieve input from.
     */
    public void loadFromScanner(Scanner input) {
	util.println("Loading food item");

	input.nextLine();
	name = input.nextLine();
	util.println(name);

	description = input.nextLine().replace("\\n", "\n");
	util.println(description);

	specialOrder = input.nextLine().replace("\\n", "\n");
	util.println(specialOrder);

	price = input.nextBigDecimal();
	util.println(price);

	input.nextLine();
	quantity = input.nextInt();
	util.println(quantity);

	input.nextLine();
	size = input.nextInt();
	util.println(size);
    }

    /**
     * Writes a string form of the food item that can be parsed by a scanner and
     * the `loadFromScanner` method.
     * 
     * @param output The writer to write output to.
     * @throws IOException
     */
    public void writeToStream(PrintWriter output) throws IOException {
	output.println(name);
	output.println(description.replace("\n", "\\n"));
	output.println(specialOrder.replace("\n", "\\n"));
	output.println(price.toString());
	output.println(quantity);
	output.println(size);
    }
}
