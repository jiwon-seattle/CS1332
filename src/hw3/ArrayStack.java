package hw3;

public class ArrayStack<T> implements StackInterface<T>{
	
	private T[] backingArray;
	private int size;
	
	public ArrayStack() {
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T pop() {
		if (size == 0) {
			throw new java.util.NoSuchElementException("Error, stake is empty");
		}
		size --;
		T temp = backingArray[size];
		backingArray[size] = null;
		return temp;
	}

	@Override
	public void push(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Error, data is null");
		}
		
		if (size >= backingArray.length) {
			T[] temp = (T[]) new Object[(int) (2 * backingArray.length)];
			for (int i = 0; i < backingArray.length; i++) {
				temp[i] = backingArray[i];
			}
			backingArray = temp;
		}
		backingArray[size] = data;
		size ++;
	}

	@Override
	public int size() {
		return size;
	}

	
}
