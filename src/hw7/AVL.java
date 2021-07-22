package hw7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class AVL<T extends Comparable<? super T>> {
	private AVLNode<T> root;
	private int size;
	
	public AVL() {
	}
	
	public AVL(Collection<T> data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		size = 0;
		data.forEach(this::add);
	}
	
	public void add(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		root = addHelper(data, root);
	}
	
	private AVLNode<T> addHelper(T data, AVLNode<T> node) {
		if (node == null) {
			size++;
			return new AVLNode<>(data);
		}
		int temp = data.compareTo(node.getData());
		if (temp < 0) {
			node.setLeft(addHelper(data, node.getLeft()));
		} else if (temp > 0) {
			node.setRight(addHelper(data, node.getRight()));
		} else {
			return node;
		}
		calculate(node);
		return balance(node);
	}
	
	private AVLNode<T> balance(AVLNode<T> node) {
		if(node.getBalanceFactor() < -1) {
			if (node.getRight().getBalanceFactor() > 0) {
				node.setRight(rotateRight(node.getRight()));
			}
			node = rotateLeft(node);
		} else if (node.getBalanceFactor() > 1) {
			if (node.getLeft().getBalanceFactor() < 0) {
				node.setLeft(rotateLeft(node.getLeft()));
			}
			node = rotateRight(node);
		}
		return node;
	}
	
	private AVLNode<T> rotateRight(AVLNode<T> node) {
		AVLNode<T> temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);
		calculate(node);
		calculate(temp);
		return temp;
	}
	
	private AVLNode<T> rotateLeft(AVLNode<T> node) {
		AVLNode<T> temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		calculate(node);
		calculate(temp);
		return temp;
	}
	
	private void calculate(AVLNode<T> node) {
		int lHeight = height(node.getLeft());
		int rHeight = height(node.getRight());
		node.setHeight(Math.max(lHeight, rHeight) +1);
		node.setBalanceFactor(lHeight - rHeight);
	}
	
	public T remove(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		AVLNode<T> removed = new AVLNode<>(null);
		root = removeHelper(data, root, removed);
		return removed.getData();
	}
	
	private AVLNode<T> removeHelper(T data, AVLNode<T> node, AVLNode<T> removed) {
		if (node == null) {
			throw new NoSuchElementException("Data can't be found");
		}
		int temp = data.compareTo(node.getData());
		if (temp < 0) {
			node.setLeft(removeHelper(data, node.getLeft(), removed));
		} else if (temp > 0) {
			node.setRight(removeHelper(data, node.getRight(), removed));
		} else {
			size --;
			removed.setData(node.getData());
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				AVLNode<T> child = new AVLNode<>(null);
				node.setRight(successorHelper(node.getRight(), child));
				node.setData(child.getData());
			}
		}
		calculate(node);
		return balance(node);
	}
	
	private AVLNode<T> successorHelper(AVLNode<T> node, AVLNode<T> child) {
		if (node.getLeft() == null) {
			child.setData(node.getData());
			return node.getRight();
		}
		node.setLeft(successorHelper(node.getLeft(), child));
		calculate(node);
		return balance(node);
	}
	
	public T get(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		return getHelper(data, root);
	}
	
	private T getHelper(T data, AVLNode<T> node) {
		if (node == null) {
			throw new NoSuchElementException("Data is not found");
		}
		int temp = data.compareTo(node.getData());
		if(temp > 0) {
			return getHelper(data, node.getRight());
		} else if (temp < 0) {
			return getHelper(data, node.getLeft());
		} else {
			return node.getData();
		}
	}
	
	public boolean contains(T data) {
		try {
			get(data);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	private int height(AVLNode<T> node) {
		return node != null ? node.getHeight() : -1;
	}
}
