package hw3;

public class ArrayDeque<T> {
	public static final int INITIAL_CAPACITY = 10;
	
	private T[] backingArray;
	private int front;
	private int back;
	private int size;
	
	public ArrayDeque() {
		this.front = 0;
		this.back = 0;
		this.size = 0;
		this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
	}
	
	public void addFirst(T data) {
		int length = backingArray.length;
		if (data == null) {
			throw new IllegalArgumentException("The data is null, null data cannot be added to the deque");
		} else if (size == 0) {
			size ++;
			front = length -1;
			backingArray[length -1] = data;
		} else if (size == length) {
			T[] newBacking = (T[]) new Object[2 * length];
			for (int i = 0; i < length; i ++) {
				newBacking[i + 1] = backingArray[mode(front + i, size)];
			}
			backingArray = newBacking;
			backingArray[0] = data;
			size ++;
			back = size;
			front = 0;
		} else {
			backingArray[mode(front -1, length)] = data;
			size++;
			front = mode(front -1, length);
		}
	}
	
	public void addLast(T data) {
		int length = backingArray.length;
		
		if(data == null) {
			throw new IllegalArgumentException("The data is null, null cannot be added to the deque");
		} else if (size == 0) {
			size ++;
			back++;
			backingArray[0] = data;
		} else if (size == length) {
			T[] newBacking = (T[]) new Object[2 * length];
			for (int i = 0; i < length; i++) {
				newBacking[i] = backingArray[mode(front + i, size)];
			}
			backingArray = newBacking;
			backingArray[length] = data;
			size ++;
			back = size;
			front = 0;
		} else {
			backingArray[back] = data;
			size ++;
			back = mode(1 + back, length);
		}
	}
	
	private static int mode(int index, int modulo) {
		if (modulo <= 0) {
			throw new IllegalArgumentException("The modulo must be positive");
		} else {
			int newIndex = index % modulo;
			return newIndex >= 0 ? newIndex : newIndex + modulo;
		}
	}
}
