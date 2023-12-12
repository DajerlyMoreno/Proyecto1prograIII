package co.edu.uptc.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIverPalabra extends JFrame implements ActionListener {
	
	private JPanel palabra;
	private JLabel titulo, etiquetaDefinicion, etiquetaTraduccion, definicion, traduccion;
	private JButton modificar, eliminar;
	
	public GUIverPalabra() {
		palabra = new JPanel();
		titulo = new JLabel();
		etiquetaDefinicion = new JLabel("Definicion");
		definicion = new JLabel();
		etiquetaTraduccion = new JLabel("Traduccion");
		traduccion = new JLabel();
		modificar = new JButton();
		eliminar = new JButton();
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setTitle("Diccionario JAZ");
		setResizable(true);
		
		initComponents();
	}
	
	private void initComponents() {
		palabra.setLayout(null);
		
		
		
		add(palabra);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
