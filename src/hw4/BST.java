package hw4;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BST<T extends Comparable<? super T>> {
	private BSTNode<T> root;
	private int size;
	
	public BST() {
		
	}
	
	public BST(Collection<T> data) {
		if (data == null || data.contains(null)) {
			throw new IllegalArgumentException("Data can't be null");
		}
		
		size = 0;
		for (T tmp : data) {
			add(tmp);
		}
	}
	
	public void add(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		if (root == null) {
			root = new BSTNode<>(data);
			size++;
		} else {
			addHelper(data, root);
		}
	}
	
	private void addHelper(T data, BSTNode<T> node) {
		if (data.compareTo(node.getData()) > 0) {
			if (node.getRight() == null) {
				node.setRight(new BSTNode<>(data));
			} else {
				addHelper(data, node.getRight());
			}
		} else if (data.compareTo(node.getData()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode<>(data));
				size++;
			} else {
				addHelper(data, node.getLeft());
			}
		}
	}
	
	public T remove(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		BSTNode<T> removed = new BSTNode<>(null);
		root = removeHelper(data, root, removed);
		return removed.getData();
	}
	
	private BSTNode<T> removeHelper(T data, BSTNode<T> node, BSTNode<T> removed) {
		if (node == null) {
			throw new NoSuchElementException("Data is not found");
		} else {
			int value = data.compareTo(node.getData());
			if (value > 0) {
				node.setRight(removeHelper(data, node.getRight(), removed));
			} else if (value < 0) {
				node.setLeft(removeHelper(data, node.getLeft(), removed));
			} else {
				removed.setData(node.getData());
				size--;
				if (node.getRight() == null) {
					return node.getLeft();
				} else if (node.getLeft() == null) {
					return node.getRight();
				} else {
					BSTNode<T> child = new BSTNode<>(null);
					node.setLeft(predecessorHelper(node.getLeft(), child));
					node.setData(child.getData());
				}
			}
		}
		return node;
	}
	
	private BSTNode<T> predecessorHelper(BSTNode<T> node, BSTNode<T> child) {
		if (node.getRight() == null) {
			child.setData(node.getData());
			return node.getLeft();
		}
		
		node.setRight(predecessorHelper(node.getRight(), child));
		return node;
	}
	
	public T get(T data) {
		if (data == null) {
			throw new IllegalArgumentException("Data can't be null");
		}
		return getHelper(data, root);
	}
	
	private T getHelper(T data, BSTNode<T> node) {
		if (node == null) {
			throw new NoSuchElementException("Data is not found");
		}
		int value = data.compareTo(node.getData());
		if (data.equals(node.getData())) {
			return node.getData();
		} else if (value < 0) {
			return getHelper(data, node.getLeft());
		} else if (value > 0) {
			return getHelper(data, node.getRight());
		}
		return null;
	}
	
	public boolean contains(T data) {
		try {
			get(data);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	public List<T> preorder() {
		List<T> data = new ArrayList<>();
		preorderHelper(data, root);
		return data;
	}
	
	private void preorderHelper(List<T> list, BSTNode<T> node) {
		if (node == null) {
			return;
		} else {
			list.add(node.getData());
			preorderHelper(list, node.getLeft());
			preorderHelper(list, node.getRight());
		}
	}
	
	public List<T> inorder() {
		List<T> data = new ArrayList<>();
		inorderHelper(data, root);
		return data;
	}
	
	private void inorderHelper(List<T> list, BSTNode<T> node) {
		if (node == null) {
			return;
		} else {
			inorderHelper(list, node.getLeft());
			list.add(node.getData());
			inorderHelper(list, node.getRight());
		}
	}
	
	public List<T> postorder() {
		List<T> data = new ArrayList<>();
		postorderHelper(data, root);
		return data;
	}
	
	private void postorderHelper(List<T> list, BSTNode<T> node) {
		if (node == null) {
			return;
		} else {
			postorderHelper(list, node.getLeft());
			postorderHelper(list, node.getRight());
			list.add(node.getData());
		}
	}
	
	public List<T> levelorder() {
		Queue<BSTNode<T>> queue = new LinkedList<>();
		List<T> data = new ArrayList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			BSTNode<T> tmp = queue.poll();
			data.add(tmp.getData());
			if (tmp.getLeft() != null) {
				queue.add(tmp.getLeft());
			}
			if (tmp.getRight() != null) {
				queue.add(tmp.getRight());
			}
		}
		return data;
	}
	
	public static <T extends Comparable<? super T>> boolean isBST(BSTNode<T> treeRoot) {
		return isBSTHelper(treeRoot);
	}
	
	private static <T extends Comparable<? super T>> boolean isBSTHelper(BSTNode<T> node) {
		if (node != null) {
			if (node.getLeft() != null) {
				if (node.getData().compareTo(node.getLeft().getData()) < 0) {
					return false;
				} else {
					return isBSTHelper(node.getLeft());
				}
			}
			if (node.getRight() != null) {
				if (node.getData().compareTo(node.getRight().getData()) > 0) {
					return false;
				} else {
					return isBSTHelper(node.getRight());
				}
			}
		}
		return true;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	public int height() {
		return heightHelper(root);
	}
	
	private int heightHelper(BSTNode<T> node) {
		return node == null ? -1
				: Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())) + 1;
	}
	
	public int size() {
		return size;
	}
	
	public BSTNode<T> getRoot() {
		return root;
	}
}
