package hw5;

public interface HeapInterface<T extends Comparable<? super T>> {
	public static final int STARTING_SIZE = 10;
	
	public void add(T item);
	
	public T remove();
	
	public boolean isEmpty();
	
	public int size();
	
	public void clear();
	
	public Comparable[] getBackingArray();
}
