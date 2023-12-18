package co.edu.uptc.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.logica.Diccionario;

/**
 * clase para agregar una palabra al diccionario
 * @author DELL
 *
 */
public class GUIagregarPalabra extends JDialog implements ActionListener {
	
	private JPanel palabra;
	private JLabel titulo, etiquetaPalabra, etiquetaDefinicion, etiquetaTraduccion;
	private JTextField newpalabra, definicion, traduccion;
	private JButton agregar, cancelar;
	private Diccionario gp;
	
	
	/**
	 * constructor de la clase, recibe un objeto de tipo GestionPalabras e inicializa los atributos dela clase
	 * @param gp
	 */
	public GUIagregarPalabra(Diccionario gp) {
		palabra = new JPanel();
		titulo = new JLabel("Agregar Palabra");
		etiquetaPalabra = new JLabel("Nueva Palabra");
		newpalabra = new JTextField();
		etiquetaDefinicion = new JLabel("Definicion");
		definicion = new JTextField();
		etiquetaTraduccion = new JLabel("Traduccion");
		traduccion = new JTextField();
		agregar = new JButton("Agregar");
		cancelar = new JButton("Cancelar");
		this.gp = gp;
		
		setLayout(null);
		setModalityType(ModalityType.APPLICATION_MODAL); // Hace que la ventana sea modal
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setTitle("Agregar palabra");
		setResizable(true);
		
		initComponents();
	}
	
	/**
	 * configura los atributos 
	 */
	private void initComponents() {
		
		 	palabra.setLayout(null);

	        Font font = new Font("Arial", Font.BOLD, 40); 
	        Color color = new Color(104, 104, 219);
	        titulo.setFont(font);
	        titulo.setForeground(color);
	        titulo.setBounds(40, 10, 350, 50);
	        
	        font = new Font("Arial", Font.BOLD, 20);
	        
	        etiquetaPalabra.setBounds(20, 90, 200, 30);
	        etiquetaPalabra.setFont(font);
	        newpalabra.setBounds(10, 120, 360, 30);

	        etiquetaDefinicion.setBounds(20, 150, 200, 30);
	        etiquetaDefinicion.setFont(font);
	        definicion.setBounds(10, 190, 360, 30);

	        etiquetaTraduccion.setBounds(20, 220, 200, 30);
	        etiquetaTraduccion.setFont(font);
	        traduccion.setBounds(10, 250, 360, 30);

	        color = new Color(121, 219, 86);
	        agregar.setBounds(70, 300, 100, 40);
	        agregar.setBackground(color);
	        agregar.addActionListener(this);
	        
	        color = new Color(219, 105, 83);
	        cancelar.setBounds(200, 300, 100, 40);
	        cancelar.setBackground(color);
	        cancelar.addActionListener(this);

	        palabra.add(titulo);
	        palabra.add(etiquetaPalabra);
	        palabra.add(newpalabra);
	        palabra.add(etiquetaDefinicion);
	        palabra.add(definicion);
	        palabra.add(etiquetaTraduccion);
	        palabra.add(traduccion);
	        palabra.add(agregar);
	        palabra.add(cancelar);

	        palabra.setBounds(0, 0, 400, 400);
	        
	        add(palabra);
	        setVisible(true);
	    }
			
		/**
		 * metoda para comprobar la entrada, Devuelve true si la entrada no tiene numeros o carecteres especiales, de lo contrario, false
		 * @param input
		 * @return
		 */
		public static boolean validarString(String input) {
	        String regex = "^[\\p{L}\\s]+$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        return matcher.matches();
	    }
	
		/**
		 * eventos que van a suceder cuando el usuario haga click sobre los botones
		 */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == agregar) {
	        	if(newpalabra.getText().isEmpty() && definicion.getText().isEmpty() && traduccion.getText().isEmpty()) {
	        		JOptionPane.showMessageDialog(null, "Ingrese toda la informacion para guardar la palabra");
	        	}
	        	else if(!validarString(newpalabra.getText()) || !validarString(definicion.getText()) || !validarString(traduccion.getText())) {
	        		JOptionPane.showMessageDialog(null, "Ingrese argumentos validos, sin numeros y/o caracteres especiales");
	        	}else {
	        		gp.agregarPalabra(newpalabra.getText(), definicion.getText(), traduccion.getText());
	        		JOptionPane.showMessageDialog(null, "Palabra agregada con exito");
		            dispose();
	        	}
	          
	            
	        } else if (e.getSource() == cancelar) {
	            dispose();
	        }
	    }

	    
}
