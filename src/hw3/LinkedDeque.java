package hw3;

import java.util.NoSuchElementException;

public class LinkedDeque<T> {
	private DoubledLinkedNode<T> head;
	private DoubledLinkedNode<T> tail;
	private int size;
	
	public void addFirst(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data is null, cannot add to deque");
		} else if (size == 0) {
			DoubledLinkedNode<T> newNode = new DoubledLinkedNode(data);
			head = newNode;
			tail = newNode;
			size ++;
		} else {
			DoubledLinkedNode<T> newNode = new DoubledLinkedNode(data);
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
			size ++;
		}
	}
	public void addLast(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data is null, cannot add to deque");
		} else if (size == 0) {
			DoubledLinkedNode<T> newNode = new DoubledLinkedNode(data);
			head = newNode;
			tail = newNode;
			size ++;
		} else {
			DoubledLinkedNode<T> newNode = new DoubledLinkedNode(data);
			newNode.setPrevious(newNode);
			tail.setNext(newNode);
			tail = newNode;
			size ++;
		}
	}
	
	public T removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException("The deque is empty, cannot remove elements when empty");
		} else if (size == 1) {
			T dataRemoved = head.getData();
			head = null;
			tail = null;
			size --;
			return dataRemoved;
		} else {
			T dataRemoved = head.getData();
			DoubledLinkedNode<T> temp = head.getNext();
			temp.setPrevious(null);
			head = temp;
			size --;
			return dataRemoved;
		}
	}
	
	public T removeLast() {
		if (size == 0) {
			throw new NoSuchElementException("The deque is empty, cannot remove elements when empty");
		} else if (size == 1) {
			T dataRemoved = tail.getData();
			head = null;
			tail = null;
			size --;
			return dataRemoved;
		} else {
			T dataRemoved = tail.getData();
			DoubledLinkedNode<T> temp = tail.getPrevious();
			temp.setNext(null);
			tail = temp;
			size --;
			return dataRemoved;
		}
	}
}
