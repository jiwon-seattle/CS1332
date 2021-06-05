package hw3;

public interface StackInterface<T> {
	public static final int INITIAL_CAPACITY = 10;
	
	boolean isEmpty();
	
	T pop();
	
	void push(T data);
	
	int size();
}
