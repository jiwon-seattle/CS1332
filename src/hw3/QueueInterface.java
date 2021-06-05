package hw3;

public interface QueueInterface<T> {
	public static final int INITIAL_CAPACITY = 10;
	
	T dequeue();
	
	void enqueue(T data);
	
	boolean isEmpty();
	
	int size();
}
