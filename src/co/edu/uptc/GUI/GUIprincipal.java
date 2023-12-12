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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import co.edu.uptc.logica.GestionPalabras;
import co.edu.uptc.modelo.Palabra;

public class GUIprincipal extends JFrame implements ActionListener, MouseListener{
	private GestionPalabras gp;
	private JPanel title, tabla, palabra;
	private JLabel titulo;
	private JTextField buscarpalabra;
	private JComboBox<String> letras;
	private JTable tablaPalabras;
	private JScrollPane tablaScrool;
	private JButton buscar, agregar;
	private DefaultTableCellRenderer centerRenderer;
	
	public GUIprincipal() {
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
		gp = new GestionPalabras();
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(670, 700);
		setLocationRelativeTo(null);
		setTitle("Diccionario JAZ");
		setResizable(true);
		initComponents();
	}

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
		buscar.setIcon(modificarTamañoImagen("Recursos/buscar.jpg", 35, 35));
		buscar.addActionListener(this);
		agregar.setBounds(580, 10, 40, 40);
		agregar.setIcon(modificarTamañoImagen("Recursos/agregar.png", 35, 35));
		agregar.addActionListener(this);
		palabra.setLayout(null);
		palabra.setBounds(10, 120, 700, 50);
		palabra.add(buscarpalabra);
		palabra.add(buscar);
		palabra.add(agregar);
		
		List<String> alphabet = getAlphabet();
		letras.addItem("Todas las letras");
        for (String letter : alphabet) {
            letras.addItem(letter);
        }
        letras.setBounds(10, 10, 610, 40);
        letras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        tablaScrool.setViewportView(tablaPalabras);
        
		tablaPalabras.setModel(modelTable());
		tablaPalabras.setRowHeight(60);
		tablaPalabras.addMouseListener(this);

		JTableHeader header = tablaPalabras.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(246, 108, 9));
		header.setForeground(Color.black);

		tablaPalabras.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tablaPalabras.getColumnModel().getColumn(5).setCellRenderer(new JLabelTableCellRender());

		TableColumnModel columnModel = tablaPalabras.getColumnModel();
		columnModel.getColumn(5).setResizable(false);
        
		tabla.setLayout(null);
        tabla.add(letras);
        tabla.setBounds(10, 190, 650, 500);

		add(title);
		add(palabra);
		add(tabla);
		
		setVisible(true);
	}
	
	private TableModel modelTable() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {// Este metodo es para evitar que el usuario edite las
																// celdas de la tabla
				return false;
			}
		};

		String[] encabezados = { "Palabra", "Opcion" };
		modelo.setColumnIdentifiers(encabezados);

		Object[] fila;
		for (Palabra palabra : gp.) {
			fila = new Object[6];
			fila[0] = imageEscale(new ImageIcon("Resourses\\Icons\\" + pro.getId() + ".png"), 50, 50);
			fila[1] = pro.getNameProduct();
			fila[2] = pro.getDescription();
			fila[3] = pro.getPrice();
			fila[4] = pro.getStock();
			fila[5] = imageEscale(new ImageIcon("Resourses\\Icons\\opciones.jfif"), 70, 30);

			modelo.addRow(fila);
		}
		return modelo;
	}

	private ImageIcon modificarTamañoImagen(String imagePath, int w, int h) {
		ImageIcon originalIcon = new ImageIcon(imagePath);

        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        return resizedIcon;
	}
	
	private static List<String> getAlphabet() {
        List<String> alphabet = new ArrayList();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            alphabet.add(String.valueOf(letter));
        }
        return alphabet;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == buscar) {
			GUIverPalabra vp = new GUIverPalabra();
			setVisible(false);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
