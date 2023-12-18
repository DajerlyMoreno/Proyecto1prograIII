package co.edu.uptc.logica;

import java.util.ArrayList;

import co.edu.uptc.modelo.BinaryTree;
import co.edu.uptc.modelo.Palabra;
import co.edu.uptc.modelo.TreeNode;

/**
 * clase que gestiona y hace posible el crud 
 * @author DELL
 *
 */
public class Diccionario {
	
	private TreeNode<Palabra>[] letras = new TreeNode[26];
	private ArrayList<BinaryTree<Palabra>> arboles = new ArrayList<BinaryTree<Palabra>>();
	
	/**
	 * constructor de la clase, en el se inicializan los atributos
	 */
	public Diccionario() {
		for (int i = 0; i < letras.length; i++) {
		    letras[i] = null;
		    arboles.add(new BinaryTree<Palabra>((o1, o2) -> o1.getPalabra().compareTo(o2.getPalabra())));
		}
	}
	
	/**
	 * metodo para agregar una palabra al BinaryTree que le corresponde
	 * @param raiz TreeNode<Palabra> del arbol donde se va a agregar la palabra
	 * @param palabra Palabra palabra a agregar
	 */
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

    /**
     * metodo para agregar palabra al diccionario
     * @param newpalabra String palabra 
     * @param def String definicion
     * @param trad String traduccion
     */
    public void agregarPalabra(String newpalabra, String def, String trad) {
    	newpalabra = Character.toUpperCase(newpalabra.charAt(0)) + newpalabra.substring(1);
    	Palabra palabra = new Palabra(newpalabra, def, trad);
        agregarPalabra(letras[palabra.getPalabra().charAt(0) - 'A'], palabra);
    }
    
    /**
     * metodo para obtener todas las palabras del diccionario o las palabras que inician con una letra especifica, 
     * 
     * @param letra String letra para buscar palabras que inicien por ella
     * @return ArrayList<Palabra> retorna null si no encuentra nada
     */
    public ArrayList<Palabra> obtenerPalabras(String letra){
    	int index = letra.toUpperCase().charAt(0) - 'A';
    	if(index == -23) {
    		ArrayList<Palabra> todas = new ArrayList<Palabra>();
    		for(int i = 0; i < letras.length; i ++) {
    			todas.addAll(arboles.get(i).listInsort());
    		}
    		return todas;
    		
    	}else if(index >= 0 && index <=26){
    		return arboles.get(index).listInsort();
    	}
    	
    	return null;
    }
    
    /**
     * metodo para obtener una palabra especifica del diccionario, 
     * @param palabra String palabra buscada
     * @return ArrayList<Palabra> retorna null si no la encuentra 
     */
    public ArrayList<Palabra> obtenerPalabra(String palabra){
    	palabra = Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1);
    	ArrayList<Palabra> pal = new ArrayList<Palabra>();
    	int index = palabra.charAt(0) - 'A';
    	if(index >= 0 && index <=26){
    		if(arboles.get(index).findInfo(new Palabra(palabra, null, null)) != null){
    			pal.add(arboles.get(index).findInfo(new Palabra(palabra, null, null)));
        		return pal;
    		}
    	}
	
    	return null;
    }

    /**
     * metodo para modificar la informacion de una palabra, es posible cambiar su definicion y traduccion
     * @param palabra String palabra a modificar
     * @param definicion String definicion actual de la palabra
     * @param traduccion String traduccion actual de la palabra
     */
	public void modificarPalabra(String palabra, String definicion, String traduccion) {
		int index = palabra.charAt(0) - 'A';
		Palabra modPalabra = new Palabra(palabra, definicion, traduccion);
		arboles.get(index).modifyNode(arboles.get(index).findInfo(new Palabra(palabra, null, null)), modPalabra);
	}

	/**
	 * metodo para eliminar una palabra 
	 * @param palabra String palabra a eliminar
	 */
	public void eliminarPalabra(String palabra) {
		int index = palabra.charAt(0) - 'A';
		arboles.get(index).deleteNode(arboles.get(index).findNodo(new Palabra(palabra, null, null)));
		letras[index] = arboles.get(index).getRoot();
	}
    
    
}
