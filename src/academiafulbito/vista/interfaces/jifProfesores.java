/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifProfesores.java
 *
 * Created on 02/08/2024, 02:42:40 PM
 */
package academiafulbito.vista.interfaces;

import academiafulbito.controlador.beans.ProfesorFacade;
import academiafulbito.modelo.entidades.Profesor;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.vista.utilidades.LiteralesTexto;
import academiafulbito.vista.utilidades.Utils;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Walter Jair
 */
public class jifProfesores extends javax.swing.JInternalFrame {

    JDesktopPane jdp;
    public static ProfesorFacade profesorFacade;
    DefaultTableModel modelo;
    String[] nombreColumnas = {
        LiteralesTexto.LITERAL_ID,
        LiteralesTexto.LITERAL_NOMBRE,
        LiteralesTexto.LITERAL_APELLIDO,
        LiteralesTexto.LITERAL_TELEFONO,
        LiteralesTexto.LITERAL_ESTADO,
        LiteralesTexto.LITERAL_VER,
        LiteralesTexto.LITERAL_EDITAR,
        LiteralesTexto.LITERAL_ELIMINAR
    };
    int indicador;//para saber si estamos en modo de edicion
    private int idSeleccionada; // Variable para almacenar la ID del profesor seleccionado
    private int paginaActual = 1;
    private int tamanioPagina = 5;//para el paginado de tabla
    private int totalPaginas;

    /** Creates new form jifProfesores */
    public jifProfesores(JDesktopPane jdpModAF) {
        initComponents();
        jdp = jdpModAF;
        Utils.cargarComboEstado(jcbEstado);
        accionBotones(false, false);
        profesorFacade = new ProfesorFacade();
        listarProfesores(paginaActual, tamanioPagina);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tphProfesores = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jspProfesores = new javax.swing.JScrollPane();
        tblProfesores = new javax.swing.JTable();
        btnNuevoProfesor = new org.edisoncor.gui.button.ButtonRound();
        btnAnterior = new org.edisoncor.gui.button.ButtonRound();
        lblPaginaActual = new javax.swing.JLabel();
        btnSiguiente = new org.edisoncor.gui.button.ButtonRound();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtApellido = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtTelefono = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jcbEstado = new org.edisoncor.gui.comboBox.ComboBoxRound();

        setBackground(new java.awt.Color(135, 135, 246));
        setClosable(true);
        setTitle("MANTENIMIENTO PROFESORES");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tphProfesores.setFont(new java.awt.Font("Bookman Old Style", 1, 24));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspProfesores.setBackground(new java.awt.Color(255, 255, 255));
        jspProfesores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jspProfesores.setOpaque(false);

        tblProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProfesores.setOpaque(false);
        jspProfesores.setViewportView(tblProfesores);

        jPanel1.add(jspProfesores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 270));

        btnNuevoProfesor.setBackground(new java.awt.Color(156, 156, 247));
        btnNuevoProfesor.setText("+ PROFESOR");
        btnNuevoProfesor.setFont(new java.awt.Font("Arial", 1, 18));
        btnNuevoProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProfesorActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 50));

        btnAnterior.setBackground(new java.awt.Color(204, 204, 204));
        btnAnterior.setForeground(new java.awt.Color(51, 51, 51));
        btnAnterior.setText("<<");
        btnAnterior.setFont(new java.awt.Font("Arial", 1, 24));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, -1, 50));

        lblPaginaActual.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        lblPaginaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaginaActual.setText("10");
        jPanel1.add(lblPaginaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 220, 50));

        btnSiguiente.setBackground(new java.awt.Color(204, 204, 204));
        btnSiguiente.setForeground(new java.awt.Color(51, 51, 51));
        btnSiguiente.setText(">>");
        btnSiguiente.setFont(new java.awt.Font("Arial", 1, 24));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, -1, 50));

        tphProfesores.addTab("LISTADO", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setEditable(false);
        txtNombre.setDescripcion("Nombre*");
        txtNombre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 720, 40));

        txtApellido.setEditable(false);
        txtApellido.setDescripcion("Apellidos*");
        txtApellido.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 720, 40));

        txtTelefono.setEditable(false);
        txtTelefono.setDescripcion("Telefono*");
        txtTelefono.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 720, 40));

        btnGuardar.setBackground(new java.awt.Color(156, 156, 247));
        btnGuardar.setBorder(null);
        btnGuardar.setText("AÑADIR");
        btnGuardar.setBorderPainted(true);
        btnGuardar.setContentAreaFilled(true);
        btnGuardar.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 170, 70));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVO PROFESOR");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 250, 20));

        btnCancelar.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/volver.png"))); // NOI18N
        btnCancelar.setText("VOLVER");
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 220, 70));

        jcbEstado.setEnabled(false);
        jcbEstado.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(jcbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 220, 40));

        tphProfesores.addTab("REGISTRO", jPanel2);

        getContentPane().add(tphProfesores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProfesorActionPerformed
        // TODO add your handling code here:
        indicador = 0;//para poder guardar
        tphProfesores.setSelectedIndex(1);
        limpiarCampos();
        habilitarCampos(true);
        accionBotones(true, true);
    }//GEN-LAST:event_btnNuevoProfesorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cadenaMensaje = 0 == indicador ? LiteralesTexto.ESTA_SEGURO_GUARDAR_NUEVO_REGISTRO : LiteralesTexto.ESTA_SEGURO_MODIFICAR_REGISTRO;
        if (Utils.mensajeConfirmacion(cadenaMensaje) == JOptionPane.YES_OPTION) {
            Profesor profesor;
            switch (indicador) {
                case 0://registrar profesor
                    profesor = new Profesor();
                    profesorFacade.guardarProfesor(getDatosProfesor(profesor));
                    Utils.mensajeInformacion(LiteralesTexto.REGISTRO_GUARDADO_CORRECTAMENTE);
                    break;
                case 1://actualizar profesor
                    profesor = profesorFacade.findProfesorById(idSeleccionada);
                    profesorFacade.actualizarProfesor(getDatosProfesor(profesor));
                    Utils.mensajeInformacion(LiteralesTexto.REGISTRO_ACTUALIZADO_CORRECTAMENTE);
                    break;
            }

            listarProfesores(paginaActual, tamanioPagina);
            limpiarCampos();
            habilitarCampos(false);
            accionBotones(false, false);
            btnGuardar.setText("Añadir");
            indicador = 0;
            tphProfesores.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(false);
        tphProfesores.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (paginaActual > 1) {
            paginaActual--;
            listarProfesores(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (paginaActual < totalPaginas) {
            paginaActual++;
            listarProfesores(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnSiguienteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRound btnAnterior;
    private javax.swing.JButton btnCancelar;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private org.edisoncor.gui.button.ButtonRound btnNuevoProfesor;
    private org.edisoncor.gui.button.ButtonRound btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.comboBox.ComboBoxRound jcbEstado;
    private javax.swing.JScrollPane jspProfesores;
    private javax.swing.JLabel lblPaginaActual;
    private javax.swing.JTable tblProfesores;
    private javax.swing.JTabbedPane tphProfesores;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtApellido;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void listarProfesores(List<Profesor> listaProfesores) {
        // Selecciona el primer tab en un JTabbedPane
        tphProfesores.setSelectedIndex(0);

        modelo = Utils.generarModeloTabla(nombreColumnas);

        // Asignar el modelo a la tabla
        tblProfesores.setModel(modelo);

        int[] anchoColumnas = {15, 60, 20, 20, 20, 15, 25, 25}; // Anchos específicos para cada columna
        Utils.setAnchoColumnas(tblProfesores, anchoColumnas);
        Utils.ocultarColumnas(tblProfesores, 0);//ocultar la primera columna
        Utils.ocultarColumnas(tblProfesores, 4);//ocultar columna estado

        // limpia los datos existentes en la tabla.
        Utils.limpiarModeloTabla(modelo, tblProfesores);

        // Verificar si la lista tiene elementos
        if (listaProfesores.size() > 0) {
            // Iterar sobre la lista y agregar cada objeto a la tabla
            for (Profesor profesor : listaProfesores) {

                // Crea un array de objetos con los datos del objeto para agregar a la tabla.
                Object[] fila = new Object[]{
                    profesor.getIdProfesor(),
                    profesor.getNombreProfesor(),
                    profesor.getApellidoProfesor(),
                    profesor.getTelefono(),
                    profesor.getEstado(),
                    LiteralesTexto.LITERAL_VER,
                    LiteralesTexto.LITERAL_EDITAR,
                    LiteralesTexto.LITERAL_ELIMINAR
                };
                modelo.addRow(fila); // Agregar la fila al modelo de la tabla
            }
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblProfesores.setDefaultRenderer(Object.class, new Utils(18));

            Utils.configurarEstiloTabla(tblProfesores, jspProfesores);
            Utils.configurarBotonesAccion(tblProfesores);
        }
    }

    private Profesor getDatosProfesor(Profesor profesor) {
        profesor.setNombreProfesor(txtNombre.getText());
        profesor.setApellidoProfesor(txtApellido.getText());
        profesor.setTelefono(txtTelefono.getText());
        profesor.setEstado((Estado) jcbEstado.getSelectedItem());

        return profesor;
    }

    private void limpiarCampos() {
        txtNombre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtApellido.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtTelefono.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
    }

    private void habilitarCampos(boolean band) {
        txtNombre.setEditable(band);
        txtApellido.setEditable(band);
        txtTelefono.setEditable(band);
        if (indicador == 0) {
            jcbEstado.setSelectedIndex(0);
            jcbEstado.setEnabled(!band);
        } else {
            jcbEstado.setEnabled(band);
        }
    }

    private void accionBotones(boolean d, boolean e) {
        btnCancelar.setEnabled(d);
        btnGuardar.setEnabled(e);
    }

    public void cargarDatosEnFormulario(int row) {
        if (row != -1) {
            // Capturar la ID de la fila seleccionada
            idSeleccionada = Integer.parseInt(tblProfesores.getValueAt(row, 0).toString()); // Supone que la ID está en la primera columna

            // Obtener los datos de la fila seleccionada
            String nombre = (String) tblProfesores.getValueAt(row, 1);
            String apellido = (String) tblProfesores.getValueAt(row, 2);
            String telefono = (String) tblProfesores.getValueAt(row, 3);
            Estado estado = (Estado) tblProfesores.getValueAt(row, 4);

            // Asignar los datos a los JTextField en el segundo panel
            txtNombre.setText(nombre);
            txtApellido.setText(apellido);
            txtTelefono.setText(telefono);

            // Seleccionar el estado en el JComboBox
            jcbEstado.setSelectedItem(estado);

            // Cambiar al segundo panel donde están los JTextField
            tphProfesores.setSelectedIndex(1);
            btnGuardar.setText("Modificar");
            indicador = 1;
            accionBotones(true, true);
            habilitarCampos(true);
        } else {
            //colocar alguna alerta
        }
    }

    private void listarProfesores(int paginaActual, int tamanioPagina) {
        totalPaginas = profesorFacade.obtenerTotalPaginas(tamanioPagina);

        List<Profesor> listaprofesores = profesorFacade.listarEntidadesPaginadas(paginaActual, tamanioPagina);

        // Actualizar el JLabel con la página actual
        lblPaginaActual.setText("Página " + paginaActual + " de " + totalPaginas);

        // Mostrar las categorías en la tabla
        listarProfesores(listaprofesores);
        actualizarEstadoBotones();// Actualizar el estado de los botones
    }

    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }
}
