package DataStructures;

import Interfaces.IStack;

/**
 * @author Abanoub Ashraf
 */
public class Stacks implements IStack {

	/**
	 * implementing the stack using a double linked list.
	 */
	DoubleLinkedList stack = new DoubleLinkedList();
	/**
	 * stack size.
	 */
	int siz = new Integer(0);
	/**
	 * saves the pushed or peeked element.
	 */
	Object element1 = new Object();

	/**
	 * Removes the element at the top of stack and returns that element.
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		if (siz == 0) {
			throw new RuntimeException("stack is empty");
		}
		Node newlast = new Node();
		Node poped = new Node();
		poped = stack.tailer.getPrev();
		newlast = stack.tailer.getPrev().getPrev();
		element1 = poped.getElement();
		newlast.setNext(stack.tailer);
		stack.tailer.setPrev(newlast);
		poped.setNext(null);
		poped.setPrev(null);
		siz--;
		return element1;
	}

	/**
	 * Get the element at the top of stack without removing it from stack.
	 * @return top of stack element, or through exception if empty
	 */
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if (stack.size() == 0) {
			throw new RuntimeException("stack is empty");
		}
		element1 = (stack.tailer.getPrev()).getElement();
		return element1;
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * @param object to insert
	 */
	@Override
	public void push(final Object element) {
		// TODO Auto-generated method stub
		stack.add(element);
		siz++;
	}

	/**
	 * Tests if this stack is empty.
	 * @return true if stack empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (siz == 0);
	}

	/**
	 * Returns the number of elements in the stack.
	 * @return number of elements in the stack
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return siz;
	}
}
