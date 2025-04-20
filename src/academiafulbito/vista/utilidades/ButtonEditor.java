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
import academiafulbito.vista.interfaces.jifProductoServicios;
import academiafulbito.vista.interfaces.jifCategoriaProducto;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Ronald J
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private String label;

    public ButtonEditor(final String label) {
        this.label = label;
        button = new JButton(label);
        button.setOpaque(false); // Para mantener la transparencia
    }

    @Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(label);

        final JInternalFrame ventanaActual = obtenerJInternalFrame(table); // Detecta la ventana

        // Remover todos los ActionListeners previamente asignados para evitar duplicados
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Obtener la fila en tiempo real
                if (selectedRow >= 0) {
                    if (label.equals(LiteralesTexto.LITERAL_EDITAR)) {
                        try {
                            try {
                                editarFilaSeleccionada(selectedRow, ventanaActual); // Método para enviar la fila seleccionada
                            } catch (groovyjarjarcommonscli.ParseException ex) {
                                Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (label.equals(LiteralesTexto.LITERAL_ELIMINAR)) {
                        try {
                            try {
                                eliminarFilaSeleccionada(selectedRow, ventanaActual);
                            } catch (groovyjarjarcommonscli.ParseException ex) {
                                Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if(label.equals(LiteralesTexto.LITERAL_VER)){
                        try {
                            mostrarFilaSeleccionada(selectedRow, ventanaActual);
                        } catch (groovyjarjarcommonscli.ParseException ex) {
                            Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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

    private void editarFilaSeleccionada(int selectedRow, JInternalFrame ventana) throws ParseException, groovyjarjarcommonscli.ParseException {
        if (ventana instanceof jifCategorias) {
            jfPrincipal.menuCategorias.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProfesores) {
            jfPrincipal.menuProfesores.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifPadres) {
            jfPrincipal.menuPadres.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if(ventana instanceof jifCampeonatos){
            jfPrincipal.menuCampeonatos.cargarDatosEnFormulario(selectedRow);
        } else if (ventana instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.cargarDatosEnFormulario(selectedRow);
        } else if (ventana instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.cargarDatosEnFormulario(selectedRow);
        } else if (ventana instanceof jifHorario) {
            jfPrincipal.menuHorario.editarHorarioSeleccionado(selectedRow);
        } else if (ventana instanceof jifCanchas) {
            jfPrincipal.menuCanchas.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProductoServicios) {
            jfPrincipal.menuProductoServicios.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifCategoriaProducto ) {
            jfPrincipal.menuCategoriaProducto.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else {
            System.out.println("Ventana no reconocida.");
        }

    }
    private void eliminarFilaSeleccionada(int selectedRow, JInternalFrame ventana) throws ParseException, groovyjarjarcommonscli.ParseException {
        if (ventana instanceof jifCategorias) {
            jfPrincipal.menuCategorias.eliminarCategoriaSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifPadres) {
            jfPrincipal.menuPadres.eliminarPadreSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProfesores) {
            jfPrincipal.menuProfesores.eliminarProfesorSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else if(ventana instanceof jifCampeonatos){
            jfPrincipal.menuCampeonatos.eliminarCampeonatoSeleccionada(selectedRow);
        } else if (ventana instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.eliminarAlumnoSeleccionada(selectedRow);
        } else if (ventana instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.eliminarLugarEntrenamSeleccionado(selectedRow);
        } else if (ventana instanceof jifHorario) {
            jfPrincipal.menuHorario.eliminarHorarioSeleccionado(selectedRow);
        } else if (ventana instanceof jifCanchas) {
            jfPrincipal.menuCanchas.eliminarCanchaSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProductoServicios) {
            jfPrincipal.menuProductoServicios.eliminarProductoServicioSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifCategoriaProducto) {
            jfPrincipal.menuCategoriaProducto.eliminarCategoriaSeleccionada(selectedRow); // Llama al método en el JInternalFrame
        } else {
            System.out.println("Ventana no reconocida.");
        }

    }

    private void mostrarFilaSeleccionada(int selectedRow, JInternalFrame ventana) throws groovyjarjarcommonscli.ParseException{
        if (ventana instanceof jifCategorias) {
            jfPrincipal.menuCategorias.mostrarInformacionCategoria(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifPadres) {
            jfPrincipal.menuPadres.mostrarInformacionPadre(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProfesores) {
            jfPrincipal.menuProfesores.mostrarInformacionProfesor(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifCampeonatos) {
            jfPrincipal.menuCampeonatos.mostrarInformacionCampeonato(selectedRow);
        } else if (ventana instanceof jifAlumnos) {
            jfPrincipal.menuAlumnos.mostrarInformacionAlumno(selectedRow);
        } else if (ventana instanceof jifLugarEntrenamiento) {
            jfPrincipal.menuLugarEntrenamiento.mostrarInformacionLugarE(selectedRow);
        } else if (ventana instanceof jifHorario) {
            jfPrincipal.menuHorario.mostrarInformacionHorario(selectedRow);
        } else if (ventana instanceof jifCanchas) {
            jfPrincipal.menuCanchas.mostrarInformacionCancha(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifMatricula) {
            jfPrincipal.menuMatricula.mostrarInformacionMatricula(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifProductoServicios) {
            jfPrincipal.menuProductoServicios.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else if (ventana instanceof jifCategoriaProducto) {
            jfPrincipal.menuCategoriaProducto.cargarDatosEnFormulario(selectedRow); // Llama al método en el JInternalFrame
        } else {
            System.out.println("Ventana no reconocida.");
        }
    }

    private JInternalFrame obtenerJInternalFrame(Component component) {
        while (component != null) {
            if (component instanceof JInternalFrame) {
                return (JInternalFrame) component;
            }
            component = component.getParent();
        }
        return null; // No se encontró un JInternalFrame
    }

}
