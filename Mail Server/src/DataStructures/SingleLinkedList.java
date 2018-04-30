package DataStructures;

import Interfaces.ILinkedList;

/**
 * @author abanoub ashraf zaki
 * @author kiro
 */

public class SingleLinkedList implements ILinkedList {

	/**
	 * size of the list.
	 */
	public int size = 0;
	/**
	 * header of the list.
	 */
	public Node header = new Node();
	/**
	 * tailer of the list.
	 */
	public Node tailer = new Node();
	/**
	 * temporary node to traverse the list.
	 */
	public Node temp = new Node();

	/**
	 * constructor for the sentinel head & tail of the list.
	 */
	public SingleLinkedList() {
		tailer.sNode(null, null);
		header.sNode(null, tailer);
	}

	/**
	* Inserts a specified element at the specified position in the list.
	* @param index
	* @param element
	*/
	@Override
	public void add(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new RuntimeException("index is not found");
		}
		if (index == size) {
			add(element);
			return;
		}
		Node newNode = new Node();
		newNode.setElement(element);
		int i = 0;
		temp = header.getNext();
		if (index == 0) {
			newNode.setNext(temp);
			header.setNext(newNode);
		} else {

			for (i = 0; i < size - 1; i++) {
				if (i == index - 1) {
					newNode.setNext(temp.getNext());
					temp.setNext(newNode);
				} else {
					temp = temp.getNext();
				}
			}
		}
		size++;
	}

	/**
	* Inserts the specified element at the end of the list.
	* @param element
	*/
	@Override
	public void add(final Object element) {
		// TODO Auto-generated method stub
		int i = 0;
		temp = header.getNext();
		Node newNode = new Node();
		newNode.setElement(element);
		if (size == 0) {
			newNode.setNext(tailer);
			header.setNext(newNode);
			size++;
		} else {
			for (i = 0; i < size; i++) {
				if (i == size - 1) {
					newNode.setNext(tailer);
					temp.setNext(newNode);
					size++;
					break;
				} else {
					temp = temp.getNext();
				}
			}
		}
	}

	/**
	* @param index
	* @return the element at the specified position in this list.
	*/
	@Override
	public Object get(final int index) {
		// TODO Auto-generated method stub
		if (size == 0) {
			throw new RuntimeException("list is empty");
		}
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("index is not found");
		}
		int i = 0;
		temp = header.getNext();
		for (i = 0; i < size; i++) {
			if (i == index) {
				return temp.getElement();
			} else {
				temp = temp.getNext();
			}
		}
		return null;
	}

	/**
	* Replaces the element at the specified position in this list with the
	* specified element.
	* @param index
	* @param element
	*/
	@Override
	public void set(final int index, final Object element) {
		// TODO Auto-generated method stub
		if (size == 0) {
			throw new RuntimeException("list is empty");
		}
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("index is not found");
		}
		int i = 0;
		temp = header.getNext();
		for (i = 0; i < size; i++) {
			if (i == index) {
				temp.setElement(element);
				break;
			} else {
				temp = temp.getNext();
			}
		}
	}

	/**
	* Removes all of the elements from this list.
	*/
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		int i = 0;
		temp = header.getNext();
		for (i = 0; i < size; i++) {
			if (i == size - 1) {
				header.setNext(tailer);
				temp.setNext(null);
				size = 0;
			} else {
				temp = temp.getNext();
			}
		}
	}

	/**
	* @return true if this list contains no elements.
	*/
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	/**
	* Removes the element at the specified position in this list.
	* @param index
	*/
	@Override
	public void remove(final int index) {
		// TODO Auto-generated method stub
		if (size == 0) {
			throw new RuntimeException("list is empty");
		}
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("index is not found");
		}
		temp = header.getNext();
		if (index == 0) {
			if (size == 1) {
				temp.setNext(null);
				header.setNext(tailer);
				size = 0;
			} else {
				header.setNext(temp.getNext());
				temp.setNext(null);
				size--;
			}
		} else {
			for (int i = 0; i < size; i++) {
				Node del = new Node();
				del = temp.getNext();
				if (i == index - 1) {
					temp.setNext(del.getNext());
					del.setNext(null);
					size--;
					break;
				} else if (del.getElement() != null) {
					temp = temp.getNext();
				}
			}
		}
	}

	/**
	* @return the number of elements in this list.
	*/
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	* @param fromIndex
	* @param toIndex
	* @return a view of the portion of this list between the specified
	* fromIndex and toIndex, inclusively.
	*/
	@Override
	public ILinkedList sublist(final int fromIndex, final int toIndex) {
		// TODO Auto-generated method stub
		if (fromIndex > size - 1 || toIndex > size - 1) {
			throw new RuntimeException("index not found");
		}
		if (fromIndex < 0 || toIndex < 0) {
			throw new RuntimeException("index not found");
		}
		if (toIndex < fromIndex) {
			throw new RuntimeException();
		}
		ILinkedList sublist = new SingleLinkedList();
		temp = header.getNext();
		for (int i = 0; i < size; i++) {
			if (i == fromIndex) {
				int j = i;
				while (j >= fromIndex && j <= toIndex) {
					sublist.add(temp.getElement());
					temp = temp.getNext();
					j++;
				}
				return sublist;
			} else {
				temp = temp.getNext();
			}
		}
		return null;
	}
	/**
	* @param o
	* @return true if this list contains the element.
	*/
	@Override
	public boolean contains(final Object o) {
		// TODO Auto-generated method stub.
		int i = 0;
		temp = header.getNext();
		if (size == 0) {
			return false;
		}
		for (i = 0; i < size; i++) {
			if (temp.getElement().equals(o)) {
				return true;
			} else {
				temp = temp.getNext();
			}
		}
		return false;
	}

	/**
	 * returns a view of a list.
	 * @return list to be string
	 */
	public String toString() {
		String list = new String("[");
		Node temp01;
		int i = 0;
		temp01 = header.getNext();
		while (i != size) {
			list += temp01.getElement();
			temp01 = temp01.getNext();
			if (i < size - 1) {
				list += ", ";
			}
			i++;
		}
		list += "]";
		return list;
	}
}
