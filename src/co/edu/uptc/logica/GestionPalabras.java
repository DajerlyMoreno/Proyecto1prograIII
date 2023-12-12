package co.edu.uptc.logica;

import java.util.ArrayList;

import co.edu.uptc.modelo.BinaryTree;
import co.edu.uptc.modelo.Palabra;
import co.edu.uptc.modelo.TreeNode;

public class GestionPalabras {
	
	private TreeNode<Palabra>[] letras = new TreeNode[26];
	private ArrayList<BinaryTree<Palabra>> arboles = new ArrayList<BinaryTree<Palabra>>();
	
	
	public GestionPalabras() {
		for (int i = 0; i < letras.length; i++) {
		    letras[i] = null;
		    arboles.add(new BinaryTree<Palabra>((o1, o2) -> o1.getPalabra().compareTo(o2.getPalabra())));
		}
	}
	
	
	// Método para insertar una palabra en el BST correspondiente a la letra inicial
    private void agregarPalabra(TreeNode<Palabra> raiz, Palabra palabra) {
    	int index = palabra.getPalabra().toUpperCase().charAt(0) - 'A';
        if (raiz == null) {
            arboles.get(index).addNode(palabra);
            letras[index] = arboles.get(index).getRoot();
            return;
        }else {
        	arboles.get(index).addNode(palabra);
        }
    }

    public void agregarPalabra(Palabra palabra) {
        agregarPalabra(letras[palabra.getPalabra().charAt(0) - 'A'], palabra);
    }
    
    public ArrayList<Palabra> obtenerPalabras(String letra){
    	
    	int index = letra.charAt(0) - 'A';
    	if(index == -23) {
    		
    	}else {
    		//return arboles.get(index).listPosort();
    	}
    	
    	return arboles.get(index).listPosort();
    }

}
