package DataStructures;

/**
 * @author abanoub ashraf zaki
 * @author kiro
 */

public class Node {
	/**
	 * element stored in this node.
	 */
	public Object element;
	/**
	 * referenceTo the next node in the list.
	 */
	public Node next;
	/**
	 * referenceTo the previous node in the list.
	 */
	public Node prev;

	/**
	 * single node.
	 *
	 * @param s an object
	 * @param n a node
	 */
	public void sNode(final Object s, final Node n) {
		element = s;
		next = n;
	}

	/**
	 * double node.
	 *
	 * @param s an object
	 * @param n a node
	 * @param p a node
	 */
	public void dNode(final Object s, final Node n, final Node p) {
		element = s;
		next = n;
		prev = p;
	}

	/**
	 * setter for next node.
	 *
	 * @param n a node
	 */
	public void setNext(final Node n) {
		next = n;
	}

	/**
	 * setter for previous node.
	 *
	 * @param p a node
	 */
	public void setPrev(final Node p) {
		prev = p;
	}

	/**
	 * getter for next node.
	 *
	 * @return the next node
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * getter for previous node.
	 *
	 * @return the previous node
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * setter for the element.
	 *
	 * @param e to set the node
	 */
	public void setElement(final Object e) {
		element = e;
	}

	/**
	 * getter for the element.
	 *
	 * @return the element of the node
	 */
	public Object getElement() {
		return element;
	}

}
