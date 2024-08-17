/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ronald J
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{

    public ButtonRenderer(String label) {
        setText(label);
        setOpaque(false); // Para mantener la transparencia
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(240, 240, 240)); // Ajusta el color si es necesario
        setHorizontalAlignment(SwingConstants.CENTER);
        // Configura el borde inferior para cada fila
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Solo borde inferior
        return this;
    }
}
