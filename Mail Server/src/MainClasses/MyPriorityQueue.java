package MainClasses;

import DataStructures.DoubleLinkedList;
import DataStructures.Node;
import Interfaces.IPriorityQueue;
/**
 * object carries value and key.
 */
class pQNode {
	/**
	 * key.
	 */
	int key;
	/**
	 * value.
	 */
	Object value;

	/**
	 * node.
	 * 
	 * @param k ...
	 * @param v ...
	 */
	pQNode(final int k, final Object v) {
		key = k;
		value = v;
	}
}

/**
 * PQ.
 */
public class MyPriorityQueue implements IPriorityQueue {

	/**
	 * thePriorityQueue.
	 */
	DoubleLinkedList pQueue = new DoubleLinkedList();

	/**
	 * Inserts an item with priority key.
	 *  key "1" is the highest priority.
	 */
	@Override
	public void insert(final Object item, final int key) {
		if (key <= 0 || item == null) {
			throw new RuntimeException();
		}
		pQNode newNode = new pQNode(key, item);
		if (pQueue.size() == 0) {
			pQueue.add(newNode);
			return;
		}
		Node temp1 = pQueue.header.getNext();
		Node temp2 = pQueue.tailer.getPrev();
		boolean added = false;
		int i = 0;
		int j = pQueue.size() - 1;
		while (true) {
			if (((pQNode) temp1.getElement()).key
				> (int) newNode.key) {
				added = true;
				pQueue.add(i, newNode);
				break;
			} else {
				temp1 = temp1.getNext();
			}
			if (((pQNode) temp2.getElement()).key
				<= (int) newNode.key) {
				added = true;
				pQueue.add(j + 1, newNode);
				break;
			} else {
				temp2 = temp2.getPrev();
			}
			i++;
			j--;
		}
		if (!added) {
			pQueue.add(pQueue.size(), newNode);
		}
	}

	/**
	 * Removes the object with the highest priority.
	 */
	@Override
	public Object removeMin() {
		if (pQueue.size() == 0) {
			throw new RuntimeException();
		}
		Object min = ((pQNode) pQueue.get(0)).value;
		pQueue.remove(0);
		return min;
	}

	/**
	 * Return the object with the highest priority.
	 */
	@Override
	public Object min() {
		if (pQueue.isEmpty()) {
			throw new RuntimeException();
		}
		return ((pQNode) pQueue.get(0)).value;
	}

	/**
	 * Tests if this queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		return pQueue.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 */
	@Override
	public int size() {
		return pQueue.size();
	}
}
