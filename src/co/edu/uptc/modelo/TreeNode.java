package co.edu.uptc.modelo;
/**
 * clase de nodos para la estructura de arboles binarios
 * @author DELL
 *
 * @param <T>
 */
public class TreeNode <T>{
	
	private T info;
	private TreeNode<T> right;
	private TreeNode<T> left;
	
	/**
	 * constructor de clase
	 * @param info
	 */
	public TreeNode(T info) {
		this.info = info;
		right = null;
		left = null;
	}
	
	/**
	 * constructor vacio
	 */
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
