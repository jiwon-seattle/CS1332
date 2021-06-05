package hw2;

public interface LinkedListInterface<T> {
	public void addAtIndex(int index, T data);
	
	public void addToFront(T data);
	
	public void addToBack(T data);
	
	public T removeAtIndex(int index);
	
	public T removeFromFront();
	
	public T removeFromBack();
	
	public boolean removeAllOccurences(T data);
	
	public T get(int index);
	
	public Object[] toArray();
	
	public boolean isEmpty();
	
	public int size();
	
	public void clear();
	
	public LinkedListNode<T> getHead();
	
	public LinkedListNode<T> getTail();
	
}
