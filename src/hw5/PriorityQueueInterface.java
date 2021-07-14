package hw5;

public interface PriorityQueueInterface<T extends Comparable<? super T>> {
	public void enqueue(T item);
	
	public T dequeue();
	
	public boolean isEmpty();
	
	public int size();
	
	public void clear();
	
	public HeapInterface<T> getBackingHeap();
}
