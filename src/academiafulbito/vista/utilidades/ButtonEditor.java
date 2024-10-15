/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.vista.utilidades;

import academiafulbito.vista.interfaces.jfPrincipal;
import academiafulbito.vista.interfaces.jifAlumnos;
import academiafulbito.vista.interfaces.jifCampeonatos;
import academiafulbito.vista.interfaces.jifCanchas;
import academiafulbito.vista.interfaces.jifCategorias;
import academiafulbito.vista.interfaces.jifHorario;
import academiafulbito.vista.interfaces.jifLugarEntrenamiento;
import academiafulbito.vista.interfaces.jifMatricula;
import academiafulbito.vista.interfaces.jifPadres;
import academiafulbito.vista.interfaces.jifProfesores;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        // Remover todos los ActionListeners previamente asignados para evitar duplicados
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (label.equals(LiteralesTexto.LITERAL_EDITAR)) {
                    try {
                        enviarFilaSeleccionada(); // Método para enviar la fila seleccionada
                    } catch (ParseException ex) {
                        Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (label.equals(LiteralesTexto.LITERAL_ELIMINAR)) {
                    try {
                        eliminarFilaSeleccionada();
                    } catch (ParseException ex) {
                        Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if(label.equals(LiteralesTexto.LITERAL_VER)){
                    mostrarFilaSeleccionada();
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

    private void enviarFilaSeleccionada() throws ParseException {
        if (jfPrincipal.menuCategorias instanceof jifCategorias) {
            jfPrincipal.menuCategorias.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuProfesores instanceof jifProfesores) {
            jfPrincipal.menuProfesores.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuPadres instanceof jifPadres) {
            jfPrincipal.menuPadres.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        }
        if(jfPrincipal.menuCampeonatos instanceof jifCampeonatos){
            jfPrincipal.menuCampeonatos.cargarDatosEnFormulario(selectedRow);
        }
        if (jfPrincipal.menuAlumnos instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.cargarDatosEnFormulario(selectedRow);
        }
        if (jfPrincipal.menuLugarEntrenamiento instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.cargarDatosEnFormulario(selectedRow);
        }
        if (jfPrincipal.menuHorario instanceof jifHorario) {
            jfPrincipal.menuHorario.editarHorarioSeleccionado(selectedRow);
        }
        if (jfPrincipal.menuCanchas instanceof jifCanchas) {
            jfPrincipal.menuCanchas.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        }

    }
    private void eliminarFilaSeleccionada() throws ParseException {
        if (jfPrincipal.menuCategorias instanceof jifCategorias) {
            jfPrincipal.menuCategorias.eliminarCategoriaSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuPadres instanceof jifPadres) {
            jfPrincipal.menuPadres.eliminarPadreSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        }
         if (jfPrincipal.menuProfesores instanceof jifProfesores) {
            jfPrincipal.menuProfesores.eliminarProfesorSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        }
        if(jfPrincipal.menuCampeonatos instanceof jifCampeonatos){
            jfPrincipal.menuCampeonatos.eliminarCampeonatoSeleccionada(selectedRow);
        }
        if (jfPrincipal.menuAlumnos instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.eliminarAlumnoSeleccionada(selectedRow);
        }
        if (jfPrincipal.menuLugarEntrenamiento instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.eliminarLugarEntrenamSeleccionado(selectedRow);
        }
        if (jfPrincipal.menuHorario instanceof jifHorario) {
            jfPrincipal.menuHorario.eliminarHorarioSeleccionado(selectedRow);
        }
        if (jfPrincipal.menuCanchas instanceof jifCanchas) {
            jfPrincipal.menuCanchas.eliminarCanchaSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        }

    }

    private void mostrarFilaSeleccionada(){
        if (jfPrincipal.menuCategorias instanceof jifCategorias) {
            jfPrincipal.menuCategorias.mostrarInformacionCategoria(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuPadres instanceof jifPadres) {
            jfPrincipal.menuPadres.mostrarInformacionPadre(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuProfesores instanceof jifProfesores) {
            jfPrincipal.menuProfesores.mostrarInformacionProfesor(selectedRow); // Llama al método en el JInternalFrame
        }
        if (jfPrincipal.menuCampeonatos instanceof jifCampeonatos) {
            jfPrincipal.menuCampeonatos.mostrarInformacionCampeonato(selectedRow);
        }
        if (jfPrincipal.menuAlumnos instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.mostrarInformacionAlumno(selectedRow);
        }
        if (jfPrincipal.menuLugarEntrenamiento instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.mostrarInformacionLugarE(selectedRow);
        }
        if (jfPrincipal.menuHorario instanceof jifHorario) {
            jfPrincipal.menuHorario.mostrarInformacionHorario(selectedRow);
        }
        if (jfPrincipal.menuCanchas instanceof jifCanchas) {
            jfPrincipal.menuCanchas.mostrarInformacionCancha(selectedRow); // Llama al método en el JInternalFrame
        }

        if (jfPrincipal.menuMatricula instanceof jifMatricula) {
            jfPrincipal.menuMatricula.mostrarInformacionMatricula(selectedRow); // Llama al método en el JInternalFrame
        }
    }
}
