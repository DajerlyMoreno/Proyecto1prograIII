package co.edu.uptc.modelo;

public class Palabra implements Comparable<Palabra>{
	private String palabra;
	private String definicion;
	private String traduccion;
	
	public Palabra() {
		
	}

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "palabras: " + getPalabra() + "\tdefinicion: "+ getDefinicion() + "\ttraduccion: "+ getTraduccion();
	}

	@Override
	public int compareTo(Palabra otraPalabra) {
        return this.palabra.compareTo(otraPalabra.getPalabra());
	}
}
