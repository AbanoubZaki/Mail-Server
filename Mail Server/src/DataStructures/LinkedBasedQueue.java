package DataStructures;

import Interfaces.ILinkedBased;
import Interfaces.IQueue;

/**
 * @author Abanoub Ashraf
 * @author amr khaled
 * @author kiro
 */
public class LinkedBasedQueue implements IQueue, ILinkedBased {
	/**
	 * double list for elements.
	 */
	DoubleLinkedList q = new DoubleLinkedList();

	/**
	 * Inserts an item at the queue front.
	 * @param item
	 */
	@Override
	public void enqueue(final Object item) {
		// TODO Auto-generated method stub
		q.add(item);
	}

	/**
	 * Removes the object at the queue rear and returns it.
	 */
	@Override
	public Object dequeue() {
		// TODO Auto-generated method stub
		if (q.size() == 0) {
			throw new RuntimeException();
		}
		Object element = new Object();
		element = q.get(0);
		q.remove(0);
		return element;
	}

	/**
	 * Tests if this queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return q.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return q.size();
	}

}
