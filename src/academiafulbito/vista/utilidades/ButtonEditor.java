/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import academiafulbito.vista.interfaces.jfPrincipal;
import academiafulbito.vista.interfaces.jifCategorias;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


/**
 *
 * @author Ronald J
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private String label;
    private int selectedRow = -1;

    public ButtonEditor(final String label) {
        this.label = label;
        button = new JButton(label);
        button.setOpaque(false); // Para mantener la transparencia
    }

    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(label);
        selectedRow = row; // Guarda la fila seleccionada
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (label.equals(LiteralesTexto.LITERAL_EDITAR)) {
                    enviarFilaSeleccionada(); // Método para enviar la fila seleccionada
                }
                fireEditingStopped();// Indica que la edición de la celda terminó
            }
        });
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    private void enviarFilaSeleccionada() {        
        if (jfPrincipal.menuCategorias instanceof jifCategorias) {
            jfPrincipal.menuCategorias.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        }
    }
}
