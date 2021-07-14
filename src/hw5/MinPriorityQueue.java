package hw5;

public class MinPriorityQueue <T extends Comparable<? super T>> implements PriorityQueueInterface<T>{
	private HeapInterface<T> backingHeap;
	
	public MinPriorityQueue() {
		backingHeap = new MinHeap<T>();
	}
	
	@Override
	public void enqueue(T item) {
		if (item == null) {
			throw new IllegalArgumentException("Error, item is null");
		}
		backingHeap.add(item);
	}
	
	@Override
	public T dequeue() {
		if (backingHeap.isEmpty()) {
			throw new java.util.NoSuchElementException("Error, Q is not empty");
		}
		return backingHeap.remove();
	}
	
	@Override
	public boolean isEmpty() {
		return backingHeap.isEmpty();
	}
	
	@Override
	public int size() {
		return backingHeap.size();
	}
	
	@Override
	public void clear() {
		backingHeap.clear();
	}
	
	@Override
	public HeapInterface<T> getBackingHeap() {
		return backingHeap;
	}
}
