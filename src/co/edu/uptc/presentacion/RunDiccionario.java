package co.edu.uptc.presentacion;

import co.edu.uptc.GUI.GUIagregarPalabra;
import co.edu.uptc.GUI.GUIprincipal;
import co.edu.uptc.logica.Diccionario;
import co.edu.uptc.modelo.BinaryTree;
import co.edu.uptc.modelo.Palabra;

public class RunDiccionario {

	public static void main(String[] args) {
		
		Diccionario gp = new Diccionario();
		
		gp.agregarPalabra("Anillo", "Un objeto circular, generalmente usado como adorno en el dedo.", "Ring");
		gp.agregarPalabra("Arcoiris", "Un fen�meno �ptico y meteorol�gico que resulta en una serie de colores en el cielo", "Rainbow");
		gp.agregarPalabra("Aroma", "Una fragancia agradable o olor.", "Aroma ");
		gp.agregarPalabra("Alcohol", "Un compuesto qu�mico org�nico que contiene el grupo funcional -OH.", "Alcohol ");
		gp.agregarPalabra("Alambre", "Un delgado hilo met�lico utilizado para diversos prop�sitos", "Wire");
		gp.agregarPalabra("Abecedario", "El conjunto completo de letras de un idioma dispuestas en un orden espec�fico.", "Alphabet");
		gp.agregarPalabra("Azul", "Un color que se encuentra entre el verde y el a�il en el espectro visible.", "Blue");
		gp.agregarPalabra("Amar", "Experimentar afecto profundo y cari�o hacia alguien o algo.", "To love");
		gp.agregarPalabra("Aplaudir", "Golpear las palmas de las manos como expresi�n de aprobaci�n o reconocimiento.", "To applaud");
		gp.agregarPalabra("Amistad", "Relaci�n afectuosa entre amigos, basada en la lealtad, el apoyo y el afecto mutuo.", "Friendship");
		gp.agregarPalabra("Antojo", "Un deseo fuerte y repentino de algo", "Craving");
		gp.agregarPalabra("banco", "Un establecimiento financiero que se dedica a realizar operaciones financieras", "Bank");
		
		GUIprincipal p = new GUIprincipal(gp);
		
		//gp.obtenerPalabras("a").forEach(System.out::println);
	}

}
