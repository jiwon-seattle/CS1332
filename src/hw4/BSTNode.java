package hw4;

public class BSTNode<T extends Comparable<? super T>> {
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	
	public BSTNode(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public BSTNode<T> getLeft() {
		return left;
	}
	
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}
	
	public BSTNode<T> getRight() {
		return right;
	}
	
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
}
