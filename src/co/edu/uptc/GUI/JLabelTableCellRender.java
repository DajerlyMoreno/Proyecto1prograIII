package co.edu.uptc.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
/**
 * clase para personalizar los jlabel de la tabla y los jareatextfiel de la misma 
 * @author DELL
 *
 */
public class JLabelTableCellRender extends DefaultTableCellRenderer {
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	// Si estamos en la columna 4, y el valor es un ImageIcon
        if (value instanceof ImageIcon) {
            // Crear un JLabel y establecer el ImageIcon en �l
            JLabel label = new JLabel((ImageIcon) value);

            // Centrar el ImageIcon dentro de la celda
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            // Devolver el JLabel
            return label;
            
        } else if(value instanceof String){
            // Si el componente no es un JLabel, crear un nuevo JTextArea
            JTextArea textArea = new JTextArea(value.toString());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            // Devolver el JTextArea
            return textArea;
        }
        return rendererComponent;
        
    }

}
