package hw3;

public class DoubledLinkedNode<T> {
	private T data;
	private DoubledLinkedNode<T> previous;
	private DoubledLinkedNode<T> next;
	
	public DoubledLinkedNode(DoubledLinkedNode<T> previous, T data, DoubledLinkedNode<T> next) {
		this.previous = previous;
		this.data = data;
		this.next = next;
	}
	
	public DoubledLinkedNode(T data) {
		this(null, data, null);
	}
	
	public T getData() {
		return data;
	}
	
	public DoubledLinkedNode<T> getNext() {
		return next;
	}
	
	public void setNext(DoubledLinkedNode<T> next) {
		this.next = next;
	}
	
	public DoubledLinkedNode<T> getPrevious() {
		return previous;
	}
	
	public void setPrevious(DoubledLinkedNode<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public String toString() {
		return "Node containing: " + data;
	}
}
