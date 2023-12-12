package co.edu.uptc.modelo;

public class TreeNode <T>{
	
	private T info;
	private TreeNode<T> right;
	private TreeNode<T> left;
	
	public TreeNode(T info) {
		this.info = info;
		right = null;
		left = null;
	}

	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

}
