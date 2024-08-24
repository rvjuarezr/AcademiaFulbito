/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifPadres.java
 *
 * Created on 02/08/2024, 02:42:40 PM
 */
package academiafulbito.vista.interfaces;

import academiafulbito.controlador.beans.PadreFacade;
import academiafulbito.modelo.entidades.Padre;
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
public class jifPadres extends javax.swing.JInternalFrame {

    JDesktopPane jdp;
    public static PadreFacade padreFacade;
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

    private int idSeleccionada; // Variable para almacenar la ID del padre seleccionado
    private int paginaActual = 1;
    private int tamanioPagina = 5;//para el paginado de tabla
    private int totalPaginas;

    /** Creates new form jifPadress */
    public jifPadres(JDesktopPane jdpModAF) {
        initComponents();
        jdp = jdpModAF;
        Utils.cargarComboEstado(jcbEstado);

        accionBotones(false, false);
        padreFacade = new PadreFacade();
        listarPadres(paginaActual, tamanioPagina);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tphPadres = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jspPadres = new javax.swing.JScrollPane();
        tblPadres = new javax.swing.JTable();
        btnNuevoPadre = new org.edisoncor.gui.button.ButtonRound();
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
        setTitle("MANTENIMIENTO PADRES");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tphPadres.setFont(new java.awt.Font("Bookman Old Style", 1, 24));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspPadres.setBackground(new java.awt.Color(255, 255, 255));
        jspPadres.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jspPadres.setOpaque(false);

        tblPadres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPadres.setOpaque(false);
        jspPadres.setViewportView(tblPadres);


        jPanel1.add(jspPadres, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 250));


        btnNuevoPadre.setBackground(new java.awt.Color(156, 156, 247));
        btnNuevoPadre.setText("+ PADRES");
        btnNuevoPadre.setFont(new java.awt.Font("Arial", 1, 18));
        btnNuevoPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPadreActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 50));

        btnAnterior.setBackground(new java.awt.Color(204, 204, 204));
        btnAnterior.setForeground(new java.awt.Color(51, 51, 51));
        btnAnterior.setText("<<");
        btnAnterior.setFont(new java.awt.Font("Arial", 1, 24));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        jPanel1.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, -1, 50));


        lblPaginaActual.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        lblPaginaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaginaActual.setText("10");

        jPanel1.add(lblPaginaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 220, 50));


        btnSiguiente.setBackground(new java.awt.Color(204, 204, 204));
        btnSiguiente.setForeground(new java.awt.Color(51, 51, 51));
        btnSiguiente.setText(">>");
        btnSiguiente.setFont(new java.awt.Font("Arial", 1, 24));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, -1, 50));


        tphPadres.addTab("LISTADO", jPanel1);

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
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 265, 170, 70));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVO PADRE");
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
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 220, 70));

        jcbEstado.setEnabled(false);
        jcbEstado.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(jcbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 220, 40));

        tphPadres.addTab("REGISTRO", jPanel2);

        getContentPane().add(tphPadres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPadreActionPerformed
        // TODO add your handling code here:
        indicador = 0;//para poder guardar
        tphPadres.setSelectedIndex(1);
        limpiarCampos();
        habilitarCampos(true);
        accionBotones(true, true);
    }//GEN-LAST:event_btnNuevoPadreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cadenaMensaje = 0 == indicador ? LiteralesTexto.ESTA_SEGURO_GUARDAR_NUEVO_REGISTRO : LiteralesTexto.ESTA_SEGURO_MODIFICAR_REGISTRO;
        if (Utils.mensajeConfirmacion(cadenaMensaje) == JOptionPane.YES_OPTION) {
            Padre padre;
            switch (indicador) {
                case 0://registrar padre

                    padre = new Padre();
                    padreFacade.guardarPadre(getDatosPadre(padre));
                    Utils.mensajeInformacion(LiteralesTexto.REGISTRO_GUARDADO_CORRECTAMENTE);
                    break;
                case 1://actualizar padre
                    padre = padreFacade.findPadreById(idSeleccionada);
                    padreFacade.actualizarPadre(getDatosPadre(padre));
                    Utils.mensajeInformacion(LiteralesTexto.REGISTRO_ACTUALIZADO_CORRECTAMENTE);
                    break;
            }

            listarPadres(paginaActual, tamanioPagina);
            limpiarCampos();
            habilitarCampos(false);
            accionBotones(false, false);
            btnGuardar.setText("Añadir");
            indicador = 0;
            tphPadres.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(false);
        tphPadres.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (paginaActual > 1) {
            paginaActual--;
            listarPadres(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (paginaActual < totalPaginas) {
            paginaActual++;
            listarPadres(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnSiguienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRound btnAnterior;
    private javax.swing.JButton btnCancelar;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private org.edisoncor.gui.button.ButtonRound btnNuevoPadre;
    private org.edisoncor.gui.button.ButtonRound btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;

    private org.edisoncor.gui.comboBox.ComboBoxRound jcbEstado;
    private javax.swing.JScrollPane jspPadres;

    private javax.swing.JLabel lblPaginaActual;
    private javax.swing.JTable tblPadres;
    private javax.swing.JTabbedPane tphPadres;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtApellido;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void listarPadres(List<Padre> listaPadres) {
         // Selecciona el primer tab en un JTabbedPane
        tphPadres.setSelectedIndex(0);

        modelo = Utils.generarModeloTabla(nombreColumnas);

        // Asignar el modelo a la tabla
        tblPadres.setModel(modelo);

        int[] anchoColumnas = {15, 60, 20, 20, 20, 15, 25, 25}; // Anchos específicos para cada columna
        Utils.setAnchoColumnas(tblPadres, anchoColumnas);
        Utils.ocultarColumnas(tblPadres, 0);//ocultar la primera columna
        Utils.ocultarColumnas(tblPadres, 4);//ocultar columna estado

        // limpia los datos existentes en la tabla.
        Utils.limpiarModeloTabla(modelo, tblPadres);

        // Verificar si la lista tiene elementos
        if (listaPadres.size() > 0) {
            // Iterar sobre la lista y agregar cada objeto a la tabla
            for (Padre padre : listaPadres) {

                // Crea un array de objetos con los datos del objeto para agregar a la tabla.
                Object[] fila = new Object[]{
                    padre.getIdPadre(),
                    padre.getNombrePadre(),
                    padre.getApellidoPadre(),
                    padre.getTelefono(),
                    padre.getEstado(),
                    LiteralesTexto.LITERAL_VER,
                    LiteralesTexto.LITERAL_EDITAR,
                    LiteralesTexto.LITERAL_ELIMINAR
                };
                modelo.addRow(fila); // Agregar la fila al modelo de la tabla
            }
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblPadres.setDefaultRenderer(Object.class, new Utils(18));


            Utils.configurarEstiloTabla(tblPadres, jspPadres);
            Utils.configurarBotonesAccion(tblPadres);

        }
    }

    private Padre getDatosPadre(Padre padre) {

        padre.setNombrePadre(txtNombre.getText());
        padre.setApellidoPadre(txtApellido.getText());
        padre.setTelefono(txtTelefono.getText());
        padre.setEstado((Estado) jcbEstado.getSelectedItem());

        return padre;

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
            idSeleccionada = Integer.parseInt(tblPadres.getValueAt(row, 0).toString()); // Supone que la ID está en la primera columna

            // Obtener los datos de la fila seleccionada
            String nombre = (String) tblPadres.getValueAt(row, 1);
            String apellido = (String) tblPadres.getValueAt(row, 2);
            String telefono = (String) tblPadres.getValueAt(row, 3);
            Estado estado = (Estado) tblPadres.getValueAt(row, 4);

            // Asignar los datos a los JTextField en el segundo panel
            txtNombre.setText(nombre);
            txtApellido.setText(apellido);
            txtTelefono.setText(telefono);

            // Seleccionar el estado en el JComboBox
            jcbEstado.setSelectedItem(estado);

            // Cambiar al segundo panel donde están los JTextField
            tphPadres.setSelectedIndex(1);
            btnGuardar.setText("Modificar");
            indicador = 1;
            accionBotones(true, true);
            habilitarCampos(true);
        } else {
            //colocar alguna alerta
        }
    }

    private void listarPadres(int paginaActual, int tamanioPagina) {
        totalPaginas = padreFacade.obtenerTotalPaginas(tamanioPagina);

        List<Padre> listapadres = padreFacade.listarEntidadesPaginadas(paginaActual, tamanioPagina);

        // Actualizar el JLabel con la página actual
        lblPaginaActual.setText("Página " + paginaActual + " de " + totalPaginas);

        // Mostrar las categorías en la tabla
        listarPadres(listapadres);
        actualizarEstadoBotones();// Actualizar el estado de los botones
    }

    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }
}
