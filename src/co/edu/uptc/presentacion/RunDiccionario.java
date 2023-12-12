package co.edu.uptc.presentacion;

import co.edu.uptc.GUI.GUIprincipal;
import co.edu.uptc.logica.GestionPalabras;
import co.edu.uptc.modelo.BinaryTree;
import co.edu.uptc.modelo.Palabra;

public class RunDiccionario {

	public static void main(String[] args) {
//  	GUIprincipal p = new GUIprincipal();
		//BinaryTree<Palabra> bt= new BinaryTree<Palabra>((o1, o2) -> o1.getPalabra().compareTo(o2.getPalabra()));
		GestionPalabras gp = new GestionPalabras();
		
		gp.agregarPalabra(new Palabra("Ana", "def", "trad"));
		gp.agregarPalabra(new Palabra("Alicia", "def", "trad"));
		gp.agregarPalabra(new Palabra("Andres", "def", "trad"));
		gp.agregarPalabra(new Palabra("A", "def", "trad"));
		gp.agregarPalabra(new Palabra("Armando", "def", "trad"));
		gp.agregarPalabra(new Palabra("Arias", "def", "trad"));
		gp.agregarPalabra(new Palabra("B", "def", "trad"));
//		bt.addNode(18);
//		bt.addNode(46);
//		bt.addNode(63);
//		bt.addNode(120);
//		bt.addNode(100);
		
		// ana-alicia-a-abc-andres-armando-arias
		//bt.listPresort().forEach(System.out::println);
		gp.obtenerPalabras("Z").forEach(System.out::println);
		
	}

}
