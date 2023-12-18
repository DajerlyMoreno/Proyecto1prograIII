package co.edu.uptc.modelo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * clase de arbles binarios, recibe un parametro T  
 * @author DELL
 *
 * @param <T>
 */
public class BinaryTree <T>{
	private TreeNode<T> root;
	private Comparator<T> comparator;
	private ArrayList<T> list;
	
	/**
	 * constructor de la clase, recibe un comparator para identificar como se ordenara la informacion
	 * @param comparator
	 */
	public BinaryTree(Comparator<T> comparator) {
		this.comparator = comparator;
		root = null;
		
	}
	
	/**
	 * metodo para verificar si el esbol esta vacio
	 * @return
	 */
	public boolean isEmpty() {
		return root == null ? true : false;
	}
	
	/**
	 * metodo para agregar un nodo al arbol 
	 * @param info
	 */
	public void addNode( T info ){
        TreeNode<T> newNode = new TreeNode<>( info );
        if( isEmpty( ) ){
            root = newNode;
        }else{
            TreeNode<T> aux = root;
            TreeNode<T> ant = null;
            while( aux != null ){
                ant = aux;
                aux = comparator.compare(info, aux.getInfo()) < 0 ? aux.getLeft() : aux.getRight();
            }
            if( comparator.compare(info, ant.getInfo()) < 0 ){
                ant.setLeft( newNode );
            }else{
                ant.setRight( newNode );
            }
        }
    }
	
	/**
	 * metodo que devuelve una lista del objeto T
	 * @return
	 */
	public ArrayList<T> listPresort(){
        list = new ArrayList<>();
        presort( root );

        return list;
    }
	
	/**
	 * metodo para crear la lista listPresort, utiliza recursividad
	 * @param node
	 */
    private void presort(TreeNode<T> node) {
        if( node != null ){
            list.add( node.getInfo());
            presort( node.getLeft());
            presort( node.getRight());
        }
    }
	
    /**
     * metodo que devuelve una lista de los elementos en orden desendente
     * @return
     */
    public ArrayList<T> listInsort(){
        list = new ArrayList<>();
        insort( root );

        return list;
    }

    /**
	 * metodo para crear la lista listInsort, utiliza recursividad
	 * @param node
	 */
    private void insort(TreeNode<T> node) {
        if( node != null ){
            insort( node.getLeft());
            list.add( node.getInfo());
            insort( node.getRight());
        }
    }
	
    /**
     * metodo que devuelve una lista de los elementos en orden desendente, 
     * @return
     */
    public ArrayList<T> listPosort(){
        list = new ArrayList<>();
        posort( root );

        return list;
    }

    /**
	 * metodo para crear la lista listPosort, utiliza recursividad
	 * @param node
	 */
    private void posort(TreeNode<T> node) {
        if( node != null ){
            posort( node.getLeft());
            posort( node.getRight());
            list.add( node.getInfo());
        }
    }
    
    /**
     * metodo para encontrar un nodo especifico a partir de la informacion T
     * @param info
     * @return TreeNode<T> o null si no encuentra 
     */
    public TreeNode<T> findNodo(T info) {
        TreeNode<T> aux = root;
        while (aux != null && comparator.compare(info, aux.getInfo()) != 0) {
            if (comparator.compare(info, aux.getInfo()) < 0) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }
        return (aux != null) ? aux : null;
    }

    /**
     * metodo para encontrar la informacion especifica de un nodo
     * @param info T
     * @return T o null si no encuentra nada
     */
    public T findInfo(T info) {
        TreeNode<T> aux = root;
        while (aux != null && comparator.compare(info, aux.getInfo()) != 0) {
            if (comparator.compare(info, aux.getInfo()) < 0) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }
        return (aux != null) ? aux.getInfo() : null;
    }

    /**
     * metodo para modificar la informacion de un nodo
     * @param info
     * @param newInfo
     */
    public void modifyNode(T info, T newInfo) {
        TreeNode<T> node = findNodo(info);
        if (node == null) {
            return;
        }
        node.setInfo(newInfo);
    }

    /**
     * metodo para encontrar el nodo padre de un nodo
     * @param info
     * @return TreeNode<T> o null si no encuentra 
     */
    public TreeNode<T> esPadre(T info) {
        TreeNode<T> aux = root;
        TreeNode<T> padre = null;
        if (root == null || root.getInfo().equals(info)) {
            return padre; 
        }
        while (comparator.compare(info, aux.getInfo()) != 0) {
            if (comparator.compare(info, aux.getInfo()) < 0) {
                padre = aux;
                aux = aux.getLeft();
            } else {
                padre = aux;
                aux = aux.getRight();
            }
        }
        return padre;
    }

    /**
     * metodo para conocer el grado del nodo: grado 2 tiene dos hijos, grado 1 tiene un hijo y grado 0 no tiene hijos
     * @param node
     * @return
     */
    public int gradeNode(TreeNode<T> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            return 2;
        } else if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * metodo para eliminar un nodo
     * @param node
     * @return
     */
    public T deleteNode(TreeNode<T> node) {
        T aux = node.getInfo();
        switch (gradeNode(node)) {
            case 0 -> deleteSheet(node);
            case 1 -> deleteNodeWithSon(node);
            default -> deleteNodeWithChild(node);
        }
        return aux;
    }

    /**
     * metodo para eliminar un nodo si es de grado 0
     * @param node
     */
    private void deleteSheet(TreeNode<T> node) {
        if (node == root) {
            root = null;
        } else {
            TreeNode<T> father = esPadre(node.getInfo());
            if (father.getLeft() == node) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
        }
    }

    /**
     * metodo para eliminar un nodo si es de grado 1
     * @param node
     */
    private void deleteNodeWithSon(TreeNode<T> node) {
        TreeNode<T> father = esPadre(node.getInfo());
        if (father == root) {
            root = father.getLeft() == node ? father.getLeft() : father.getRight();
        } else {
            if (father.getRight() == node) {
                father.setRight(node.getRight() != null ? node.getRight() : node.getLeft());
            } else {
                father.setLeft(node.getRight() != null ? node.getRight() : node.getLeft());
            }
        }
    }

    /**
     * metodo para eliminar un nodo si es de grado 2
     * @param node
     */
    private void deleteNodeWithChild(TreeNode<T> node) {
        TreeNode<T> substitute = node.getRight();
        TreeNode<T> fatherSubstitute = null;
        while (substitute.getLeft() != null) {
            fatherSubstitute = substitute;
            substitute = substitute.getLeft();
        }
        if (fatherSubstitute != null) {
            fatherSubstitute.setLeft(substitute.getRight());
            substitute.setRight(node.getRight());
        }
        substitute.setLeft(node.getLeft());
        TreeNode<T> father = esPadre(node.getInfo());
        
        if (father == null) {
            root = substitute;
        } else {
            if (father.getLeft() == node) {
                father.setLeft(substitute);
            } else {
                father.setRight(substitute);
            }
        }
    }

	public TreeNode<T> getRoot() {
		return root;
	}

}
