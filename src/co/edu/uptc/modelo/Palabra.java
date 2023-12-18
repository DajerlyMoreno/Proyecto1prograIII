package co.edu.uptc.modelo;

/**
 * esta clase almacena la palabra, su definicion y traduccion
 * @author DELL
 *
 */
public class Palabra {
	private String palabra;
	private String definicion;
	private String traduccion;
	
	/**
	 * constructor vacio de la clase
	 */
	public Palabra() {
		
	}

	/**
	 * constructor de la clase, se inicializan los atributos
	 * @param palabra
	 * @param definicion
	 * @param traduccion
	 */
	public Palabra(String palabra, String definicion, String traduccion) {
		super();
		this.palabra = palabra;
		this.definicion = definicion;
		this.traduccion = traduccion;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}

	public String getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}

	/**
	 * metodo que retona en forma de string la informacion de la clase
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "palabras: " + getPalabra() + "\tdefinicion: "+ getDefinicion() + "\ttraduccion: "+ getTraduccion();
	}


}
