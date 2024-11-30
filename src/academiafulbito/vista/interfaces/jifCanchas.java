/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifCanchas.java
 *
 * Created on 02/08/2024, 02:42:40 PM
 */

package academiafulbito.vista.interfaces;

import academiafulbito.controlador.beans.CanchaFacade;
import academiafulbito.controlador.beans.LugarEntrenamientoFacade;
import academiafulbito.modelo.entidades.Cancha;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.vista.reportes.Reportes;
import academiafulbito.vista.utilidades.DialogUtils;
import academiafulbito.vista.utilidades.LiteralesTexto;
import academiafulbito.vista.utilidades.Utils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald J
 */
public class jifCanchas extends javax.swing.JInternalFrame {

    JDesktopPane jDesktopPane;

    int indicador;//para saber si estamos en modo de edicion
    private int idSeleccionada; // Variable para almacenar la ID de la cancha seleccionada
    private int paginaActual = 1;
    private int tamanioPagina = 5;//para el paginado de tabla
    private int totalPaginas;
    jifLugarEntrenamiento menuLugarE;
    public static CanchaFacade canchaFacade;
    public static LugarEntrenamientoFacade lugarEFacade;
    DefaultTableModel modelo;
    String[] nombreColumnas = {
        LiteralesTexto.LITERAL_ID,
        LiteralesTexto.LITERAL_NOMBRE_CANCHA,
        LiteralesTexto.LITERAL_ID,
        LiteralesTexto.LITERAL_NOMBRE_LUGAR,
        LiteralesTexto.LITERAL_ESTADO,
        LiteralesTexto.LITERAL_VER,
        LiteralesTexto.LITERAL_EDITAR,
        LiteralesTexto.LITERAL_ELIMINAR
    };
    public static int permiteSelFila = -1;//este valor no permite seleccionar la fila en la tabla
    /** Creates new form jifCanchas */
    public jifCanchas(JDesktopPane jdpModAF){
        initComponents();
        jDesktopPane = jdpModAF;
        Utils.cargarComboEstado(jcbEstado);
        accionBotones(false, false);
        canchaFacade = new CanchaFacade();
        lugarEFacade = new LugarEntrenamientoFacade();
        listarCanchas(paginaActual, tamanioPagina);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tphCancha = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jspCanchas = new javax.swing.JScrollPane();
        tblCancha = new javax.swing.JTable();
        btnNuevoHorario = new org.edisoncor.gui.button.ButtonRound();
        lblPaginaActual = new javax.swing.JLabel();
        btnAnterior = new org.edisoncor.gui.button.ButtonRound();
        btnSiguiente = new org.edisoncor.gui.button.ButtonRound();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreLugarE = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtIdLugarE = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        btnBucarLugarE = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        txtNombreCancha = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        jcbEstado = new org.edisoncor.gui.comboBox.ComboBoxRound();
        txtIdCancha = new org.edisoncor.gui.textField.TextField();
        btnReporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setClosable(true);
        setTitle("MANTENIMIENTO CANCHAS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tphCancha.setFont(new java.awt.Font("Bookman Old Style", 1, 24));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspCanchas.setBackground(new java.awt.Color(255, 255, 255));
        jspCanchas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jspCanchas.setOpaque(false);

        tblCancha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCancha.setOpaque(false);
        tblCancha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCanchaMouseClicked(evt);
            }
        });
        jspCanchas.setViewportView(tblCancha);

        jPanel1.add(jspCanchas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 250));

        btnNuevoHorario.setBackground(new java.awt.Color(156, 156, 247));
        btnNuevoHorario.setText("+ CANCHA");
        btnNuevoHorario.setFont(new java.awt.Font("Arial", 1, 18));
        btnNuevoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoHorarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 50));

        lblPaginaActual.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        lblPaginaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaginaActual.setText("10");
        jPanel1.add(lblPaginaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 220, 50));

        btnAnterior.setBackground(new java.awt.Color(204, 204, 204));
        btnAnterior.setForeground(new java.awt.Color(51, 51, 51));
        btnAnterior.setText("<<");
        btnAnterior.setFont(new java.awt.Font("Arial", 1, 24));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, -1, 50));

        btnSiguiente.setBackground(new java.awt.Color(204, 204, 204));
        btnSiguiente.setForeground(new java.awt.Color(51, 51, 51));
        btnSiguiente.setText(">>");
        btnSiguiente.setFont(new java.awt.Font("Arial", 1, 24));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, -1, 50));

        tphCancha.addTab("LISTADO", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        jLabel1.setForeground(new java.awt.Color(103, 98, 98));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CANCHA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 360, 20));

        txtNombreLugarE.setEditable(false);
        txtNombreLugarE.setDescripcion("Nombre Lugar Entrenamiento*");
        txtNombreLugarE.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtNombreLugarE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreLugarEKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombreLugarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 450, 50));

        txtIdLugarE.setEditable(false);
        txtIdLugarE.setDescripcion("Id*");
        txtIdLugarE.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtIdLugarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 110, 50));

        btnBucarLugarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/lupa.png"))); // NOI18N
        btnBucarLugarE.setBorderPainted(false);
        btnBucarLugarE.setContentAreaFilled(false);
        btnBucarLugarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBucarLugarEActionPerformed(evt);
            }
        });
        jPanel2.add(btnBucarLugarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 80, 60));

        btnCancelar.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/volver.png"))); // NOI18N
        btnCancelar.setText("VOLVER");
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 220, 70));

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
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 170, 70));

        txtNombreCancha.setEditable(false);
        txtNombreCancha.setDescripcion("Nombre*");
        txtNombreCancha.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtNombreCancha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 570, 50));

        jcbEstado.setEnabled(false);
        jcbEstado.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(jcbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 220, 40));
        jPanel2.add(txtIdCancha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, -1));
        txtIdCancha.setVisible(false);

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/reportes.png"))); // NOI18N
        btnReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        
        jPanel2.add(txtIdCancha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, -1));
        txtIdCancha.setVisible(false);

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/reportes.png"))); // NOI18N
        btnReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        tphCancha.addTab("REGISTRO", jPanel2);

        getContentPane().add(tphCancha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoHorarioActionPerformed
        // TODO add your handling code here:
        indicador = 0;//para poder guardar
        tphCancha.setSelectedIndex(1);
        limpiarCampos();
        habilitarCampos(true);
        accionBotones(true, true);
}//GEN-LAST:event_btnNuevoHorarioActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (paginaActual > 1) {
            paginaActual--;
            listarCanchas(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (paginaActual < totalPaginas) {
            paginaActual++;
            listarCanchas(paginaActual, tamanioPagina);
        }
}//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtNombreLugarEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreLugarEKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        evt.setKeyChar(Character.toUpperCase(c)); // Convertir a mayúsculas
}//GEN-LAST:event_txtNombreLugarEKeyTyped

    private void btnBucarLugarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBucarLugarEActionPerformed
        // TODO add your handling code here:
        if(jfPrincipal.menuLugarEntrenamiento == null || jfPrincipal.menuLugarEntrenamiento.isClosed()){
            jfPrincipal.menuLugarEntrenamiento = new jifLugarEntrenamiento(jDesktopPane);
            Utils.visualizarInternalFrame(jfPrincipal.menuLugarEntrenamiento, jDesktopPane);
        }
        jfPrincipal.menuLugarEntrenamiento.permiteSelFila=0;//este valor permite seleccionar con un clic en la fila de la tabla de lugar E
        jfPrincipal.menuLugarEntrenamiento.toFront();
}//GEN-LAST:event_btnBucarLugarEActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(false);
        tphCancha.setSelectedIndex(0);
        accionBotones(false, false);
}//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            //if (validarDatosCancha()) {
            String cadenaMensaje = 0 == indicador ? LiteralesTexto.ESTA_SEGURO_GUARDAR_NUEVO_REGISTRO : LiteralesTexto.ESTA_SEGURO_MODIFICAR_REGISTRO;
            if (Utils.mensajeConfirmacion(cadenaMensaje) == JOptionPane.YES_OPTION) {
                Cancha cancha;
                switch (indicador) {
                    case 0://registrar Cancha
                        cancha = new Cancha();
                        canchaFacade.guardarCancha(getDatosCancha(cancha));
                        Utils.mensajeInformacion(LiteralesTexto.REGISTRO_GUARDADO_CORRECTAMENTE);
                        break;
                    case 1://actualizar Cancha
                        cancha = canchaFacade.findCanchaById(idSeleccionada);
                        canchaFacade.actualizarCancha(getDatosCancha(cancha));
                        Utils.mensajeInformacion(LiteralesTexto.REGISTRO_ACTUALIZADO_CORRECTAMENTE);
                        break;
                }

                listarCanchas(paginaActual, tamanioPagina);
                limpiarCampos();
                habilitarCampos(false);
                accionBotones(false, false);
                btnGuardar.setText("Añadir");
                indicador = 0;
                tphCancha.setSelectedIndex(0);
            }
            //}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_btnGuardarActionPerformed

    private void tblCanchaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCanchaMouseClicked
        // TODO add your handling code here:
        switch(permiteSelFila){
            case 0://permite llamarlo desde una ventana externa
                int nroFila = tblCancha.getSelectedRow();
                if(nroFila != -1){
                    jfPrincipal.menuHorario.txtIdCancha.setText(tblCancha.getValueAt(nroFila, 0).toString());
                    jfPrincipal.menuHorario.txtNombreCancha.setText(tblCancha.getValueAt(nroFila, 1).toString());

                }
                try{
                    setClosed(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
        }
    }//GEN-LAST:event_tblCanchaMouseClicked


    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        Map parametros = new HashMap();
        parametros.put("idCancha", txtIdCancha.getText()); // Ejemplo de parámetro para el reporte
        // Llamar al método para generar y mostrar el reporte
        Reportes.imprimirReporte(parametros, "rp_canchas.jasper");

    }//GEN-LAST:event_btnReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRound btnAnterior;
    private javax.swing.JButton btnBucarLugarE;
    private javax.swing.JButton btnCancelar;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private org.edisoncor.gui.button.ButtonRound btnNuevoHorario;
    private javax.swing.JButton btnReporte;
    private org.edisoncor.gui.button.ButtonRound btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.comboBox.ComboBoxRound jcbEstado;
    private javax.swing.JScrollPane jspCanchas;
    private javax.swing.JLabel lblPaginaActual;
    private javax.swing.JTable tblCancha;
    private javax.swing.JTabbedPane tphCancha;
    private org.edisoncor.gui.textField.TextField txtIdCancha;
    public static org.edisoncor.gui.textField.TextFieldRoundBackground txtIdLugarE;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombreCancha;
    public static org.edisoncor.gui.textField.TextFieldRoundBackground txtNombreLugarE;
    // End of variables declaration//GEN-END:variables

    private void listarCanchas(List<Cancha> listaCanchas){
        // Selecciona el primer tab en un JTabbedPane
        tphCancha.setSelectedIndex(0);

        modelo = Utils.generarModeloTabla(nombreColumnas);

        // Asignar el modelo a la tabla
        tblCancha.setModel(modelo);

        int[] anchoColumnas = {15, 50, 30, 50, 20, 15, 25, 25}; // Anchos específicos para cada columna
        Utils.setAnchoColumnas(tblCancha, anchoColumnas);
        Utils.ocultarColumnas(tblCancha, 0);//ocultar la primera columna
        Utils.ocultarColumnas(tblCancha, 2);//ocultar la primera columna
        Utils.ocultarColumnas(tblCancha, 4);//ocultar columna estado

        // limpia los datos existentes en la tabla.
        Utils.limpiarModeloTabla(modelo, tblCancha);

        // Verificar si la lista de socios tiene elementos
        if (listaCanchas.size() > 0) {

            // Iterar sobre la lista de canchas y agregar cada cancha a la tabla
            for (Cancha cancha : listaCanchas) {

                // Crea un array de objetos con los datos de la cancha para agregar a la tabla.
                Object[] fila = new Object[]{
                    cancha.getIdCancha(),
                    cancha.getNombre(),
                    cancha.getId_lugar().getIdLugar(),
                    cancha.getId_lugar().getNombre(),
                    cancha.getEstado(),
                    LiteralesTexto.LITERAL_VER,
                    LiteralesTexto.LITERAL_EDITAR,
                    LiteralesTexto.LITERAL_ELIMINAR
                };
                modelo.addRow(fila); // Agregar la fila al modelo de la tabla
            }
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblCancha.setDefaultRenderer(Object.class, new Utils(14));

            // Establece el modo de selección de filas para permitir solo una selección a la vez.
            tblCancha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            Utils.configurarEstiloTabla(tblCancha, jspCanchas);
            Utils.configurarBotonesAccion(tblCancha);
        }
    }

    private void accionBotones(boolean d, boolean e) {
        btnCancelar.setEnabled(d);
        btnGuardar.setEnabled(e);
    }

    private void listarCanchas(int paginaActual, int tamanioPagina) {
        totalPaginas = canchaFacade.obtenerTotalPaginas(tamanioPagina);

        List<Cancha> listaCanchas = canchaFacade.listarEntidadesPaginadas(paginaActual, tamanioPagina);

        // Actualizar el JLabel con la página actual
        lblPaginaActual.setText("Página " + paginaActual + " de " + totalPaginas);

        // Mostrar las canchas en la tabla
        listarCanchas(listaCanchas);
        actualizarEstadoBotones();// Actualizar el estado de los botones

    }

    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }

    private void limpiarCampos() {
        txtNombreCancha.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtIdLugarE.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombreLugarE.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
    }

    private void habilitarCampos(boolean band) {
        txtNombreCancha.setEditable(band);
        txtIdLugarE.setEditable(band);
        txtNombreLugarE.setEditable(band);
        if (indicador == 0) {
            jcbEstado.setSelectedIndex(0);
            jcbEstado.setEnabled(!band);
        } else {
            jcbEstado.setEnabled(band);
        }
    }

    private Cancha getDatosCancha(Cancha cancha){
        cancha.setEstado((Estado) jcbEstado.getSelectedItem());
        cancha.setId_lugar(lugarEFacade.findLugarEntrenamientoById(Integer.parseInt(txtIdLugarE.getText())));
        cancha.setNombre(txtNombreCancha.getText());

        return cancha;
    }
    public void mostrarInformacionCancha(int filaSeleccionada) {
        tphCancha.setSelectedIndex(1);
        txtIdCancha.setText(tblCancha.getValueAt(filaSeleccionada, 0).toString());
        //datos del alumno
        txtNombreCancha.setText(tblCancha.getValueAt(filaSeleccionada, 1).toString());
        txtIdLugarE.setText(tblCancha.getValueAt(filaSeleccionada, 2).toString());
        txtNombreLugarE.setText(tblCancha.getValueAt(filaSeleccionada, 3).toString());
    }
    public void eliminarCanchaSeleccionada(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            // Capturar la ID de la fila seleccionada
            idSeleccionada = Integer.parseInt(tblCancha.getValueAt(filaSeleccionada, 0).toString()); // Supone que la ID está en la primera columna
            if (Utils.mensajeConfirmacion(LiteralesTexto.ESTA_SEGURO_ELIMINAR_REGISTRO) == JOptionPane.YES_OPTION) {
                Cancha canchaAEliminar = canchaFacade.findCanchaById(idSeleccionada);
                if(canchaAEliminar != null){
                    try {
                        // Llamar al método para eliminar
                        canchaFacade.eliminarCancha(canchaAEliminar);
                        JOptionPane.showMessageDialog(this, LiteralesTexto.REGISTRO_ELIMINADO_CORRECTAMENTE);

                        // Actualizar la tabla después de eliminar
                        totalPaginas = canchaFacade.obtenerTotalPaginas(tamanioPagina);

                        // Verificar si la página actual es mayor que el total de páginas después de la eliminación
                        if (paginaActual > totalPaginas) {
                            paginaActual = totalPaginas; // Ajustar la página actual a la última disponible
                        }

                        // Actualizar la tabla después de eliminar
                        listarCanchas(paginaActual, tamanioPagina); // Volver a listar las categorías después de la eliminación

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, LiteralesTexto.ERROR_AL_ELIMINAR_EL_REGISTRO+ " : " + e.getMessage(), LiteralesTexto.LITERAL_ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, LiteralesTexto.REGISTRO_NO_ENCONTRADO_EN_LA_BBDD, LiteralesTexto.LITERAL_ERROR, JOptionPane.ERROR_MESSAGE);
                }

            }
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, LiteralesTexto.POR_FAVOR_SELECCIONE_UNA_REGISTRO_PARA_ELIMINAR);
        }
    }
    public void cargarDatosEnFormulario(int row) {
        if (row != -1) {
            // Capturar la ID de la fila seleccionada
            idSeleccionada = Integer.parseInt(tblCancha.getValueAt(row, 0).toString()); // Supone que la ID está en la primera columna

            // Obtener los datos de la fila seleccionada
            String nombreCancha = (String) tblCancha.getValueAt(row, 1);
            String idLugar = (String) tblCancha.getValueAt(row, 2).toString();
            String nombreLugar = (String) tblCancha.getValueAt(row, 3);
            Estado estado = (Estado)tblCancha.getValueAt(row, 4);

            // Asignar los datos a los JTextField en el segundo panel
            txtNombreCancha.setText(nombreCancha);
            txtIdLugarE.setText(idLugar);
            txtNombreLugarE.setText(nombreLugar);

            // Seleccionar el estado en el JComboBox
            jcbEstado.setSelectedItem(estado);

            // Cambiar al segundo panel donde están los JTextField
            tphCancha.setSelectedIndex(1);
            btnGuardar.setText("Modificar");
            indicador = 1;
            accionBotones(true, true);
            habilitarCampos(true);
        } else{
            //colocar alguna alerta
        }
    }


}