package co.edu.uptc.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import co.edu.uptc.logica.Diccionario;
import co.edu.uptc.modelo.Palabra;

/**
 * clase donde semuestra las palabras del diccionario, que da accesibilidad para hacer el CRUD
 * @author DELL
 *
 */

public class GUIprincipal extends JFrame implements MouseListener, ActionListener{
	private Diccionario gp;
	private JPanel title, tabla, palabra;
	private JLabel titulo;
	private JTextField buscarpalabra;
	private JComboBox<String> letras;
	private JTable tablaPalabras;
	private JScrollPane tablaScrool;
	private JButton buscar, agregar;
	private DefaultTableModel modeloTabla;
	private DefaultTableCellRenderer centerRenderer;
	
	/**
	 * constructor de la clase, se inicializan los atributos y se configura la ventana
	 * recibe un parametro de tipo GestionPalabras para que tenga palabras por defecto
	 * @param gp
	 */
	public GUIprincipal(Diccionario gp) {
		title = new JPanel();
		tabla = new JPanel();
		palabra = new JPanel();
		titulo = new JLabel("DICCIONARIO JAZ");
		buscarpalabra = new JTextField("Buscar Palabra");
		letras = new JComboBox<String>();
		tablaPalabras = new JTable();
		tablaScrool = new JScrollPane();
		buscar = new JButton();
		agregar = new JButton();
		centerRenderer = new DefaultTableCellRenderer();
		this.gp = gp;
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(670, 700);
		setLocationRelativeTo(null);
		setTitle("Diccionario JAZ");
		setIconImage(modificarTamImagen("Recursos/diccionario.png", 35, 35).getImage());
		setResizable(false);
		initComponents();
	}

	/**
	 * metodo para configurar y agregar los componentes a la ventana
	 */
	private void initComponents() {
		
		Font font = new Font("Arial", Font.BOLD, 70);
		Color color = new Color(85, 85, 85);
		titulo.setFont(font);
		titulo.setForeground(color);
		title.add(titulo);
		title.setBounds(0, 10, 650, 90);
		
		//setBounds(x, y, WIDTH, HEIGHT);
		buscarpalabra.setBounds(10, 10, 500, 40);
		buscarpalabra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (buscarpalabra.getText().equals("Buscar Palabra")) {
                    buscarpalabra.setText("");
                }
            }
        });

		buscar.setBounds(520, 10, 40, 40);
		buscar.setIcon(modificarTamImagen("Recursos/buscar.jpg", 35, 35));
		buscar.addActionListener(this);
		agregar.setBounds(580, 10, 40, 40);
		agregar.setIcon(modificarTamImagen("Recursos/agregar.png", 35, 35));
		agregar.addActionListener(this);
		palabra.setLayout(null);
		palabra.setBounds(10, 120, 700, 50);
		palabra.add(buscarpalabra);
		palabra.add(buscar);
		palabra.add(agregar);
		
		List<String> alphabet = getAlphabet();
		letras.addItem("*Todas las palabras");
        for (String letter : alphabet) {
            letras.addItem(letter);
        }
        letras.setBounds(10, 10, 610, 40);
        letras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Palabra> palabras = gp.obtenerPalabras((String)letras.getSelectedItem());
				if(palabras.size() == 0) {
					JOptionPane.showMessageDialog(null, "No hay palabras que inicien por esa letra");
					letras.setSelectedItem(letras.getItemAt(0));
					confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
				}else {
					confiTabla(palabras);
				}
			}
		});
       
		confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
		
		tabla.setLayout(null);
		tabla.setBounds(10, 190, 650, 500);
        tabla.add(letras);
        tabla.add(tablaScrool);

		add(title);
		add(palabra);
		add(tabla);
		
		setVisible(true);
	}
	
	
	/**
	 * metodo que configura lo relacionado con la tabla, recibe un ArrayList<Palabras> como parametro 
	 * para actualizar el modelo de la tabla de acuerdo a la contidad de elementos del array 
	 * @param p
	 */
	private void confiTabla(ArrayList<Palabra> p) {
		tablaScrool.setViewportView(tablaPalabras);
		tablaScrool.setBounds(10, 60, 610, 380);
	        
		tablaPalabras.setModel(modelTable(p)); 
		tablaPalabras.setRowHeight(40);
		tablaPalabras.addMouseListener(this);

		JTableHeader header = tablaPalabras.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(Color.gray);
		header.setForeground(Color.black);

		tablaPalabras.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaPalabras.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaPalabras.getColumnModel().getColumn(2).setCellRenderer(new JLabelTableCellRender());
		tablaPalabras.getColumnModel().getColumn(3).setCellRenderer(new JLabelTableCellRender());
		
		TableColumn column1 = tablaPalabras.getColumnModel().getColumn(0);
        column1.setPreferredWidth(100);

        // Establecer el ancho de la segunda columna a 150
        TableColumn column2 = tablaPalabras.getColumnModel().getColumn(1);
        column2.setPreferredWidth(100);

        // Establecer el ancho de la tercera columna a 200
        TableColumn column3 = tablaPalabras.getColumnModel().getColumn(2);
        column3.setPreferredWidth(200);

		TableColumnModel columnModel = tablaPalabras.getColumnModel();
		columnModel.getColumn(3).setResizable(false);
		
	}
	
	/**
	 * metodo que da el modelo de la tabla, recibe un ArrayList<Palabra> para actualizar la tabla
	 * y retorna el modelo con los elementos presentes en el array
	 * @param p
	 * @return TablaModel
	 */
	private TableModel modelTable(ArrayList<Palabra> p) {
	    modeloTabla = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    String[] encabezados = { "Palabra","Traduccion", "Definicion", "Opcion" };
	    modeloTabla.setColumnIdentifiers(encabezados);

	    Object[] fila;
	    for (Palabra palabra : p) {
	        fila = new Object[4];
	        fila[0] = palabra.getPalabra();
	        fila[1] = palabra.getTraduccion();
	        fila[2] = palabra.getDefinicion();
	        fila[3] = modificarTamImagen("Recursos/opciones.jpg", 70, 30);

	        modeloTabla.addRow(fila);
	    }
	    return modeloTabla;
	}

	/**
	 * metodo para dar tamaño especifico a una imagen, recibe la direccion donde se encuentra alojada la imagen,
	 * el tamaño y la altura y retorna un elemento de tipo ImageIcon
	 * @param imagePath
	 * @param w
	 * @param h
	 * @return ImageIcon
	 */
	private ImageIcon modificarTamImagen(String imagePath, int w, int h) {
		ImageIcon originalIcon = new ImageIcon(imagePath);

        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        return resizedIcon;
	}
	
	/**
	 * metodo que crea una lista con las letras del abecedario
	 * @return List<String>
	 */
	private static List<String> getAlphabet() {
        List<String> alphabet = new ArrayList();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            alphabet.add(String.valueOf(letter));
        }
        return alphabet;
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
	 * eventos que van a ocurri cuando el usuario haga click sobre los otones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == buscar) {
			if(!buscarpalabra.getText().isEmpty() && buscarpalabra.getText() != null) {
				if(!validarString(buscarpalabra.getText())) {
					JOptionPane.showMessageDialog(null, "Ingrese una palabra valida, sin numeros y/o caracteres especiales");
				}else if(gp.obtenerPalabra(buscarpalabra.getText()) == null ) {
					JOptionPane.showMessageDialog(null, "Palabra no encontrada");
					letras.setSelectedItem(letras.getItemAt(0));
					confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
				}else {
					confiTabla(gp.obtenerPalabra(buscarpalabra.getText()));
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Ingrese una palabra para buscarla");
			}
			buscarpalabra.setText("");
			
		}else if((JButton)e.getSource() == agregar) {
			new GUIagregarPalabra(gp);
			confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
		}
	}
	
	/**
	 * evenos que van a ocurrir cuando elusuario haga click sobre la tabla
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int columna = tablaPalabras.getColumnModel().getColumnIndexAtX(e.getX()); 
	    int fila = e.getY() / tablaPalabras.getRowHeight();
	   
	    if (columna == 3 && fila < tablaPalabras.getRowCount()) { 
			if(e.getX() > 507 && e.getX() < 534){
				if(gp.obtenerPalabra(tablaPalabras.getValueAt(fila, 0).toString()) != null) {
					new GUImodificarPalabra(tablaPalabras.getValueAt(fila, 0).toString(), tablaPalabras.getValueAt(fila, 1).toString(),
							tablaPalabras.getValueAt(fila, 2).toString(), gp);
					confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
				}
			}else if(e.getX() > 545 && e.getX() < 571) {
				gp.eliminarPalabra(tablaPalabras.getValueAt(fila, 0).toString());
				confiTabla(gp.obtenerPalabras((String)letras.getSelectedItem()));
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
