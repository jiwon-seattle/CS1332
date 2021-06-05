package hw2;

public class DoublyLinkedList<T> implements LinkedListInterface<Object> {
	
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	private int size;
	@Override
	public void addAtIndex(int index, Object data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		} 
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Error, index is negative or larget than size");
		}
		
		LinkedListNode<T> temp = new LinkedListNode(data);
		
		if (index == 0) {
			if (head == null) {
				head = temp;
				tail= temp;
			} else {
				temp.setNext(head);
				temp.setPrevious(null);
				head.setPrevious(temp);
				head = temp;
			}
		} else if (index == size) {
			if (tail == null) {
				head = temp;
				tail = temp;
			} else {
				temp.setNext(null);
				temp.setPrevious(tail);
				tail.setNext(temp);
				tail = temp;
			}
		} else {
			LinkedListNode<T> current = head;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			
			temp.setNext(current);
			temp.setPrevious(current.getPrevious());
			(current.getPrevious()).setNext(temp);
			current.setPrevious(temp);
		}
		size ++;
	}
	@Override
	public void addToFront(Object data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		LinkedListNode<T> temp = new LinkedListNode(data);
		
		if (head == null) {
			head = temp;
			tail= temp;
		} else {
			temp.setNext(head);
			temp.setPrevious(null);
			head.setPrevious(temp);
			head = temp;
		}
		size ++;
	}
	@Override
	public void addToBack(Object data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		LinkedListNode<T> temp = new LinkedListNode(data);
		
		if (tail == null) {
			head = temp;
			tail = temp;
		} else {
			temp.setNext(null);
			temp.setPrevious(temp);
			tail.setNext(temp);
			tail = temp;
		}
		size ++;
	}
	@Override
	public Object removeAtIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Error, index is negative or larger than size");
		}
		
		if (index == 0) {
			LinkedListNode<T> current = head;
			
			if (size == 1) {
				head = null;
				tail = null;
			} else {
				head.getNext().setPrevious(null);
				head = head.getNext();
			}
			size --;
			return current.getData();
		} else if (index == size -1) {
			LinkedListNode<T> current = tail;
			
			if (size == 1) {
				head = null;
				tail = null;
			} else {
				tail.getPrevious().setNext(null);
				tail = tail.getPrevious();
			}
			size --;
			return current.getData();
		} else {
			LinkedListNode<T> current = head;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			
			current.getNext().setPrevious(current.getPrevious());
			current.getPrevious().setNext(current.getNext());
			size --;
			return current.getData();
		}
	}
	@Override
	public Object removeFromFront() {
		if (size == 0) {
			return null;
		}
		
		LinkedListNode<T> current = head;
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			head.getNext().setPrevious(null);
			head = head.getNext();
		}
		
		size--;
		return current.getData();
	}
	@Override
	public Object removeFromBack() {
		if (size == 0) {
			return null;
		}
		LinkedListNode<T> current = tail;
		
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
		}
		size --;
		return current.getData();
	}
	@Override
	public boolean removeAllOccurences(Object data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		int check = 0;
		
		if (size == 0) {
			return false;
		}
		LinkedListNode<T> current = head;
		
		for (int i = 0; i < size; i++) {
			if (current.getData().equals(data)) {
				check++;
				if (current.getNext() != null) {
					if (current.getPrevious() == null) {
						current.getNext().setPrevious(null);
						head = current.getNext();
					}
				} else {
					if (current.getPrevious()== null) {
						head = null;
						tail = null;
					} else {
						current.getPrevious().setNext(null);
						tail = current.getPrevious();
					}
				}
			}
			current = current.getNext();
		}
		size = size - check;
		return !(check == 0);
	}
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Error, index is negative or larger than size");
		}
		LinkedListNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}
	@Override
	public Object[] toArray() {
		LinkedListNode<T> current = head;
		
		Object[] backingArray = (Object[]) new Object[size];
		for (int i = 0; i < size; i ++) {
			backingArray[i] = current.getData();
			current = current.getNext();
		}
		return backingArray;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	@Override
	public LinkedListNode getHead() {
		return head;
	}
	@Override
	public LinkedListNode getTail() {
		return tail;
	}

}
