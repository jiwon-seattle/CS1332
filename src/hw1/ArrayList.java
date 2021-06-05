package hw1;

//@author Jiwon Han

public class ArrayList<T> {
	private T[] backingArray;
	private int size;
	
	public static final int INITIAL_CAPACITY = 9;
	
	public ArrayList() {
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	public void addAtIndex(int index, T data) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("This index should be in the interval between 0 and size");
		} else if (data == null) {
			throw new IllegalArgumentException("Can't add data with null value");
		} else {
			if ( size + 1 > backingArray.length) {
				T[] tempArray = backingArray;
				backingArray = (T[]) new Object[backingArray.length * 2];
				for (int i = 0; i < tempArray.length; i++) {
					backingArray[i] = tempArray[i];
				}
			}
			if (index == size) {
				backingArray[size] = data;
			} else {
				for (int i = size; i > index; i --) {
					backingArray[i] = backingArray[i - 1];
				}
				backingArray[index] = data;
			} 
			size ++;
		}
	}
	public void addToFront(T data) {
		addAtIndex(0, data);
	}
	public void addToBack(T data) {
		addAtIndex (size, data);
	}
	public T removeAtIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The index should be in the interval between 0 and size");
		} else {
			if (index == size -1) {
				T temp = backingArray[--size];
				backingArray[size] = null;
				return temp;
			} else {
				T temp = backingArray[index];
				for (int i = index; i < size - 1; i++) {
					backingArray[index] = backingArray[index + 1];
				}
				backingArray[--size] = null;
				return temp;
			}
		}
	}
	
	public T removeFromFront() {
		return removeAtIndex(0);
	}
	
	public T removeFromBack() {
		return removeAtIndex(size - 1);
	}
	
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("The index should be in the interval between 0 and size");
		} else {
			return backingArray[index];
		}
	}
	
	public int lastIndexOf(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Can't add data with null value");
		} else {
			for (int i = size -1; i >= 0; i--) {
				if (backingArray[i].equals(data)) {
					return i;
				}
			}
			return -1;
		}
	}
	
	public void clear() {
		backingArray = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	public int size() {
		return size;
	}
	
	public Object[] getBackingArray() {
		return backingArray;
	}
}
