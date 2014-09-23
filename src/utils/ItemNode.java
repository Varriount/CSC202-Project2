package utils;

import java.io.Serializable;

/**
 * A node used in the implementation of the custom linked list `ItemList`
 * 
 * @author Clay
 *
 * @param <E>
 */
public class ItemNode<E> implements Serializable {
    private static final long serialVersionUID = 1660903712511375199L;
    private ItemNode<E> child;
    private E data;

    /**
     * Construct an ItemNode containing the given data.
     * 
     * @param data
     */
    public ItemNode(E data) {
	this.data = data;
    }

    // Getters and Setters
    
    /**
     * Retrieve the data held by the ItemNode.
     * 
     * @return The data held by the ItemNode.
     */
    public E getData() {
	return this.data;
    }

    /**
     * Set the data held by the ItemNode.
     * 
     * @param data
     *            The data to set.
     */
    public void setData(E data) {
	this.data = data;
    }

    /**
     * Retrieve the child node held by the ItemNode.
     * 
     * @return The child node held by the ItemNode
     */
    public ItemNode<E> getChild() {
	return child;
    }

    /**
     * Set the child node held by the ItemNode
     * 
     * @param child
     *            The child node the ItemNode should have.
     */
    public void setChild(ItemNode<E> child) {
	this.child = child;
    }
    
    /**
     * Return the string representation of the child node.
     */
    @Override
    public String toString() {
	return "ItemNode [child=" + child + ", data=" + data + "]";
    }
    
}
