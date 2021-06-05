package hw2;

public class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> previous;
	private LinkedListNode<T> next;
	
	public LinkedListNode(T data, LinkedListNode<T> previous, LinkedListNode<T> next) {
		this.data = data;
		this.previous = previous;
		this.next = next;
	}
	
	public LinkedListNode(T data) {
		this(data, null, null);
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public LinkedListNode<T> getNext() {
		return next;
	}
	
	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
	
	public LinkedListNode<T> getPrevious() {
		return previous;
	}
	
	public void setPrevious(LinkedListNode<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public String toString() {
		return "Node containing: " + data;
	}
}
