package co.edu.uptc.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
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
 * clase para mostrar la ventaa donde el usuario puede cambiar la informacion de la palabra
 * @author DELL
 *
 */
public class GUImodificarPalabra extends JDialog implements ActionListener{
	
	private JPanel palabra;
	private JLabel titulo,etiquetaDefinicion, etiquetaTraduccion;
	private JTextField definicion, traduccion;
	private JButton modificar, cancelar;
	private Diccionario gp;
	private String pal, trad, def;

	/**
	 * constructor de la clase
	 * @param pal
	 * @param trad
	 * @param def
	 * @param gp
	 */
	public GUImodificarPalabra(String pal, String trad, String def, Diccionario gp) {
		palabra = new JPanel();
		titulo = new JLabel(pal);
		etiquetaDefinicion = new JLabel("Definicion");
		definicion = new JTextField(def);
		etiquetaTraduccion = new JLabel("Traduccion");
		traduccion = new JTextField(trad);
		modificar = new JButton("Modificar");
		cancelar = new JButton("Cancelar");
		this.gp = gp;
		this.pal = pal;
		this.trad = trad;
		this.def = def;
		
		setLayout(null);
		setModalityType(ModalityType.APPLICATION_MODAL); // Hace que la ventana sea modal
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 360);
		setLocationRelativeTo(null);
		setTitle("Modificar palabra");
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
	        
	        etiquetaDefinicion.setBounds(20, 90, 200, 30);
	        etiquetaDefinicion.setFont(font);
	        definicion.setBounds(10, 120, 360, 30);

	        etiquetaTraduccion.setBounds(20, 150, 200, 30);
	        etiquetaTraduccion.setFont(font);
	        traduccion.setBounds(10, 190, 360, 30);

	        color = new Color(121, 219, 86);
	        modificar.setBounds(70, 260, 100, 40);
	        modificar.setBackground(color);
	        modificar.addActionListener(this);
	        
	        color = new Color(219, 105, 83);
	        cancelar.setBounds(200, 260, 100, 40);
	        cancelar.setBackground(color);
	        cancelar.addActionListener(this);

	        palabra.add(titulo);
	        palabra.add(etiquetaDefinicion);
	        palabra.add(definicion);
	        palabra.add(etiquetaTraduccion);
	        palabra.add(traduccion);
	        palabra.add(modificar);
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
	 * acciones a realizar cuando el usuario haga click sobre los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == modificar) {
			if(!validarString(definicion.getText()) || !validarString(traduccion.getText())) {
				JOptionPane.showMessageDialog(null, "Ingrese argumentos validos, sin numeros y/o caracteres especiales");
				definicion.setText(def);
				traduccion.setText(trad);
			}else if(!definicion.getText().isEmpty() && !traduccion.getText().isEmpty()) {
        		gp.modificarPalabra(pal, definicion.getText(), traduccion.getText());
        		JOptionPane.showMessageDialog(null, "Palabra modificada con exito");
	            dispose();
        	}else {
        		JOptionPane.showMessageDialog(null, "Ingrese toda la informacion para modificar la palabra");
        	}
		}else if (e.getSource() == cancelar) {
            dispose();
        }
		
	}

}
