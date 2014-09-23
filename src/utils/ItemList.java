/**
 * 
 */
package utils;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A custom linked-list implementation
 * 
 * @author Clay
 * @param <E>
 *
 */
public class ItemList<E> implements Iterable<E>, Serializable {
    private class ItemListIterator implements Iterator<E> {
	private ItemNode<E> currentNode;
	private boolean firstTime = true;

	public ItemListIterator() {
	    this.currentNode = getHeadNode();
	}

	@SuppressWarnings("unused")
	public ItemListIterator(ItemList<E> data) {
	    this.currentNode = data.getHeadNode();
	}

	@Override
	public boolean hasNext() {
	    return (currentNode != null);
	}

	@Override
	public E next() {
	    ItemNode<E> result = currentNode;
	    currentNode = currentNode.getChild();
	    return (E) result.getData();
	}

    }

    private static final long serialVersionUID = 4596425909318389460L;

    private ItemNode<E> head;

    /**
     * Add the given item to the ItemList
     * 
     * @param element
     *            The item to add
     * @return Whether the addition was successful
     */
    public void add(E element) {
	if (head != null) {
	    ItemNode<E> lastNode = getLastNode();
	    lastNode.setChild(new ItemNode<E>(element));
	} else {
	    head = new ItemNode<E>(element);
	}

    }

    /**
     * Delete an element from the linked list.
     * 
     * @param index
     *            The 0-based index to delete the element from.
     */
    @SuppressWarnings("unchecked")
    public E delete(int index) {
	ItemNode<E> currentNode = head;
	ItemNode<E> previousNode = head;
	int i = -1;

	while (i != index) {
	    i++;
	    previousNode = currentNode;
	    currentNode = previousNode.getChild();
	    if (currentNode == null) {
		throw new IndexOutOfBoundsException();
	    }
	}
	previousNode.setChild(currentNode.getChild());
	return (E) currentNode;
    }

    /**
     * Retrieves an element from the linked list.
     * 
     * @param index
     *            The 0-based index to retrieve the element from.
     * @return The requested index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
	ItemNode<E> currentNode = head;
	ItemNode<E> previousNode = head;

	while (currentNode != null) {
	    previousNode = currentNode;
	    currentNode = previousNode.getChild();
	    if (currentNode == null) {
		throw new IndexOutOfBoundsException();
	    }
	}
	return (E) previousNode;
    }

    /**
     * Retrieves the first *node* in the linked list.
     * 
     * @return The head node.
     */
    protected ItemNode<E> getHeadNode() {
	return head;
    }

    /**
     * Retrieves the last *node* in the linked list.
     * 
     * @return The tail node.
     */
    protected ItemNode<E> getLastNode() {
	ItemNode<E> currentNode = head;
	ItemNode<E> previousNode = head;

	while (currentNode != null) {
	    previousNode = currentNode;
	    currentNode = previousNode.getChild();
	}
	return previousNode;
    }

    /**
     * Determines whether the list is empty or not.
     * 
     * @return Whether the list is empty.
     */
    public boolean isEmpty() {
	return (head == null);
    }

    /**
     * Returns a new iterator object to iterator over.
     */
    public Iterator<E> iterator() {
	return new ItemListIterator();
    }

    /**
     * Searches the linked list for an element whose `equals` method returns
     * true. Returns -1 if the element is not found.
     * 
     * @param element
     *            The element to search for.
     * @return The indice of the found element.
     */
    public int search(E element) {
	int index = -1;
	for (E thisElement : this) {
	    index++;
	    if (thisElement.equals(element)) {
		return index;
	    }
	}
	return -1;
    }

    /**
     * Sets the head node of the linked list.
     * 
     * @param head
     *            The head node to set.
     */
    protected void setHeadNode(ItemNode<E> head) {
	this.head = head;
    }

    /**
     * Returns the size of the linked list.
     * 
     * @return The linked list's size.
     */
    public int size() {
	int i = 0;
	for (Iterator<E> iterator = this.iterator(); iterator.hasNext();) {
	    iterator.next();
	    i++;
	}
	return i;
    }

    /**
     * Returns a string representation of the linked list.
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	for (Iterator<E> iterator = this.iterator(); iterator.hasNext();) {
	    E node = (E) iterator.next();
	    builder.append(node.toString());
	}
	return builder.toString();
    }

}
