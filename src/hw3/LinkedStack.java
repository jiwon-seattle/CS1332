package hw3;

public class LinkedStack<T> implements StackInterface<T> {
	
	private LinkedNode<T> head;
	private int size;
	
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T pop() {
		if (size == 0) {
			throw new java.util.NoSuchElementException("Error, queue is empty");
		}
		
		LinkedNode<T> temp = head;
		if (head.getNext() != null) {
			head = head.getNext();
		} else {
			head = null;
		}
		size --;
		return temp.getData();
	}

	@Override
	public void push(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		LinkedNode<T> temp = new LinkedNode(data);
		temp.setNext(head);
		head = temp;
		size ++;
	}

	@Override
	public int size() {
		return size;
	}
	
	
}
