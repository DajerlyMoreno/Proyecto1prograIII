package co.edu.uptc.logica;

import co.edu.uptc.modelo.BinaryTree;
import co.edu.uptc.modelo.Palabra;
import co.edu.uptc.modelo.TreeNode;

public class GestionPalabras {
	
	private TreeNode<Palabra>[] letras = new TreeNode[26];
	private BinaryTree<TreeNode<Palabra>> a, b, c ,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
	
	
	public GestionPalabras() {
		for (int i = 0; i < letras.length; i++) {
		    letras[i] = null;
		}
	}
	
	
	// Método para insertar una palabra en el BST correspondiente a la letra inicial
    private void agregarPalabra(TreeNode raiz, Palabra palabra) {
        if (raiz == null) {
        	int index = palabra.getPalabra().charAt(0) - 'A';
            letras[index] = new TreeNode(palabra);
            if(index == 0) {
            	a = new BinaryTree<TreeNode<Palabra>>((o1, o2) -> o1.getInfo().getPalabra().compareTo(o2.getInfo().getPalabra()));
            }else if(index == 1) {
            	b = new BinaryTree<TreeNode<Palabra>>((o1, o2) -> o1.getInfo().getPalabra().compareTo(o2.getInfo().getPalabra()));
            }
            return;
        }else {
        	
        }
        Palabra pal = (Palabra)raiz.getInfo();
        if (palabra.getPalabra().compareTo(pal.getPalabra()) < 0) {
            if (raiz.getLeft() == null) {
                raiz.setLeft(new TreeNode(palabra));
            } else {
                agregarPalabra(raiz.getLeft(), palabra);
            }
        } else if (palabra.palabra.compareTo(raiz.data.palabra) > 0) {
            if (raiz.right == null) {
                raiz.right = new TreeNode(palabra);
            } else {
                agregarPalabra(raiz.right, palabra);
            }
        }
    }

    // Método para insertar una palabra en el BST correspondiente a la letra inicial
    public void agregarPalabra(Palabra palabra) {
        agregarPalabra(letras[palabra.palabra.charAt(0) - 'A'], palabra);
    }

    // Método para realizar una búsqueda en el BST correspondiente a la letra inicial
    private boolean buscarPalabra(TreeNode raiz, String palabra) {
        if (raiz == null) {
            return false;
        }

        if (palabra.equals(raiz.data.palabra)) {
            return true;
        } else if (palabra.compareTo(raiz.data.palabra) < 0) {
            return buscarPalabra(raiz.left, palabra);
        } else {
            return buscarPalabra(raiz.right, palabra);
        }
    }

    // Método para realizar una búsqueda en el BST correspondiente a la letra inicial
    public boolean buscarPalabra(String palabra) {
        return buscarPalabra(letras[palabra.charAt(0) - 'A'], palabra);
    }
	
	

}
