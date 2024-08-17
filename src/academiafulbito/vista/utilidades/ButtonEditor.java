/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Ronald J
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private String label;
    private boolean isPushed;
    private int selectedRow;

    public ButtonEditor(String label) {
        this.label = label;
        button = new JButton(label);
        button.setOpaque(false); // Para mantener la transparencia
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("accion al clic");
                isPushed = true;
                fireEditingStopped();// Indica que la edición de la celda terminó
            }

        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(label);
        isPushed = false;
        selectedRow = row; // Guarda la fila seleccionada
        System.out.println("selectedrow: "+selectedRow);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        System.out.println("entro al getCellEditorValue");
        if (isPushed) {
            // Usa selectedRow para acceder a la fila seleccionada
            JOptionPane.showMessageDialog(button, label + ": Fila " + selectedRow + " seleccionada.");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
