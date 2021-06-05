package hw3;

public class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] backingArray;
	private int front;
	private int back;
	private int size;
	
	public ArrayQueue() {
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
		front = 0;
		back = 0;
	}
	@Override
	public T dequeue() {
		if (size == 0) {
			throw new java.util.NoSuchElementException("Error, queue is empty");
		}
		int temp = front;
		front = (front + 1) % (backingArray.length);
		size --;
		T tempq = backingArray[temp];
		backingArray[temp] = null;
		return tempq;
	}

	@Override
	public void enqueue(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		
		if (size >= backingArray.length) {
			T[] temp = (T[]) new Object[(int) (2 * backingArray.length)];
			int index = front;
			for (int i = 0; i < backingArray.length; i++) {
				temp[i] = backingArray[index];
				index ++;
				if (index >= size) {
					index = index - size;
				}
			}
			backingArray = temp;
			front = 0;
		}
		back = (front + size) % (backingArray.length);
		backingArray[back] = data;
		size ++;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	

}
