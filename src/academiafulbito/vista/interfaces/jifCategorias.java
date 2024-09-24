/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifCategorias.java
 *
 * Created on 02/08/2024, 02:42:40 PM
 */

package academiafulbito.vista.interfaces;

import academiafulbito.controlador.beans.CategoriaFacade;
import academiafulbito.modelo.entidades.Categoria;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.vista.utilidades.DialogUtils;
import academiafulbito.vista.utilidades.LiteralesTexto;
import academiafulbito.vista.utilidades.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald J
 */
public class jifCategorias extends javax.swing.JInternalFrame {

    JDesktopPane jdp;
    public static CategoriaFacade categoriaFacade;
    public static int permiteSelFila = -1;//este valor no permite seleccionar la fila en la tabla
    DefaultTableModel modelo;
    String[] nombreColumnas = {
        LiteralesTexto.LITERAL_ID,
        LiteralesTexto.LITERAL_NOMBRE,
        LiteralesTexto.LITERAL_EDAD_MIN,
        LiteralesTexto.LITERAL_EDAD_MAX,
        LiteralesTexto.LITERAL_ESTADO,
        LiteralesTexto.LITERAL_VER,
        LiteralesTexto.LITERAL_EDITAR,
        LiteralesTexto.LITERAL_ELIMINAR
    };
    int indicador;//para saber si estamos en modo de edicion
    private int idSeleccionada; // Variable para almacenar la ID de la categoría seleccionada
    private int paginaActual = 1;
    private int tamanioPagina = 5;//para el paginado de tabla
    private int totalPaginas;
    /** Creates new form jifCategorias */
    public jifCategorias(JDesktopPane jdpModAF){
        initComponents();
        jdp=jdpModAF;
        Utils.cargarComboEstado(jcbEstado);
        accionBotones(false, false);
        categoriaFacade = new CategoriaFacade();
        listarCategorias(paginaActual, tamanioPagina);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tphCategorias = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jspCategorias = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        btnNuevaCategoria = new org.edisoncor.gui.button.ButtonRound();
        lblPaginaActual = new javax.swing.JLabel();
        btnAnterior = new org.edisoncor.gui.button.ButtonRound();
        btnSiguiente = new org.edisoncor.gui.button.ButtonRound();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtEdadMin = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtEdadMax = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jcbEstado = new org.edisoncor.gui.comboBox.ComboBoxRound();

        setBackground(new java.awt.Color(135, 135, 246));
        setClosable(true);
        setTitle("MANTENIMIENTO CATEGORIAS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tphCategorias.setFont(new java.awt.Font("Bookman Old Style", 1, 24));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jspCategorias.setBackground(new java.awt.Color(255, 255, 255));
        jspCategorias.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jspCategorias.setOpaque(false);

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCategorias.setOpaque(false);
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jspCategorias.setViewportView(tblCategorias);

        jPanel1.add(jspCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 850, 250));

        btnNuevaCategoria.setBackground(new java.awt.Color(156, 156, 247));
        btnNuevaCategoria.setText("+ CATEGORIA");
        btnNuevaCategoria.setFont(new java.awt.Font("Arial", 1, 18));
        btnNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevaCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 50));

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

        tphCategorias.addTab("LISTADO", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setEditable(false);
        txtNombre.setDescripcion("Nombre*");
        txtNombre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 720, 40));

        txtEdadMin.setEditable(false);
        txtEdadMin.setDescripcion("Edad Mínima*");
        txtEdadMin.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtEdadMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadMinKeyTyped(evt);
            }
        });
        jPanel2.add(txtEdadMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 720, 40));

        txtEdadMax.setEditable(false);
        txtEdadMax.setDescripcion("Edad Máxima*");
        txtEdadMax.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtEdadMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadMaxKeyTyped(evt);
            }
        });
        jPanel2.add(txtEdadMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 720, 40));

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
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 170, 70));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        jLabel1.setForeground(new java.awt.Color(103, 98, 98));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CATEGORIA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 290, 20));

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

        tphCategorias.addTab("REGISTRO", jPanel2);

        getContentPane().add(tphCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCategoriaActionPerformed
        // TODO add your handling code here:
        indicador = 0;//para poder guardar
        tphCategorias.setSelectedIndex(1);
        limpiarCampos();
        habilitarCampos(true);
        accionBotones(true, true);
    }//GEN-LAST:event_btnNuevaCategoriaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            if (validarDatosCategoria()) {
                String cadenaMensaje = 0 == indicador ? LiteralesTexto.ESTA_SEGURO_GUARDAR_NUEVO_REGISTRO : LiteralesTexto.ESTA_SEGURO_MODIFICAR_REGISTRO;
                if (Utils.mensajeConfirmacion(cadenaMensaje) == JOptionPane.YES_OPTION) {
                    Categoria categoria;
                    switch (indicador) {
                        case 0://registrar categoria
                            categoria = new Categoria();
                            categoriaFacade.guardarCategoria(getDatosCategoria(categoria));
                            Utils.mensajeInformacion(LiteralesTexto.REGISTRO_GUARDADO_CORRECTAMENTE);
                            break;
                        case 1://actualizar categoria
                            categoria = categoriaFacade.findCategoriaById(idSeleccionada);
                            categoriaFacade.actualizarCategoria(getDatosCategoria(categoria));
                            Utils.mensajeInformacion(LiteralesTexto.REGISTRO_ACTUALIZADO_CORRECTAMENTE);
                            break;
                    }

                    listarCategorias(paginaActual, tamanioPagina);
                    limpiarCampos();
                    habilitarCampos(false);
                    accionBotones(false, false);
                    btnGuardar.setText("Añadir");
                    indicador = 0;
                    tphCategorias.setSelectedIndex(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(false);
        tphCategorias.setSelectedIndex(0);
        accionBotones(false, false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (paginaActual < totalPaginas) {
            paginaActual++;
            listarCategorias(paginaActual, tamanioPagina);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (paginaActual > 1) {
            paginaActual--;
            //listarCategorias(Utils.cargarPaginado(paginaActual, tamanioPagina, lblPaginaActual, jfPrincipal.menuCategorias));
            listarCategorias(paginaActual, tamanioPagina);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void txtEdadMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadMinKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        // Permitir solo dígitos y hasta 2 caracteres
        if (!Character.isDigit(c) || txtEdadMin.getText().length() >= 2) {
            evt.consume(); // Ignorar el evento de tecla
        }
    }//GEN-LAST:event_txtEdadMinKeyTyped

    private void txtEdadMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadMaxKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        // Permitir solo dígitos y hasta 2 caracteres
        if (!Character.isDigit(c) || txtEdadMax.getText().length() >= 2) {
            evt.consume(); // Ignorar el evento de tecla
        }
    }//GEN-LAST:event_txtEdadMaxKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        evt.setKeyChar(Character.toUpperCase(c)); // Convertir a mayúsculas
    }//GEN-LAST:event_txtNombreKeyTyped

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        // TODO add your handling code here:
        int nroFila = tblCategorias.getSelectedRow();
        if (nroFila != -1) {
            switch (permiteSelFila) {
                case 0://permite llamarlo desde una ventana externa
                    jfPrincipal.menuAlumnos.txtIdCategoria.setText(tblCategorias.getValueAt(nroFila, 0).toString());
                    jfPrincipal.menuAlumnos.txtNombreCategoria.setText(tblCategorias.getValueAt(nroFila, 1).toString() + " DE " + tblCategorias.getValueAt(nroFila, 2).toString() + " A " + tblCategorias.getValueAt(nroFila, 3).toString());
                    break;
                case 1://permite llamarlo desde una ventana externa
                    jfPrincipal.menuHorario.txtIdCategoria.setText(tblCategorias.getValueAt(nroFila, 0).toString());
                    jfPrincipal.menuHorario.txtNombreCategoria.setText(tblCategorias.getValueAt(nroFila, 1).toString());
                    break;
            }
            //cerrar ventana
            try {
                setClosed(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_tblCategoriasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRound btnAnterior;
    private javax.swing.JButton btnCancelar;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private org.edisoncor.gui.button.ButtonRound btnNuevaCategoria;
    private org.edisoncor.gui.button.ButtonRound btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.edisoncor.gui.comboBox.ComboBoxRound jcbEstado;
    private javax.swing.JScrollPane jspCategorias;
    private javax.swing.JLabel lblPaginaActual;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTabbedPane tphCategorias;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtEdadMax;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtEdadMin;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre;
    // End of variables declaration//GEN-END:variables

    private void listarCategorias(List<Categoria> listaCategorias){
        // Selecciona el primer tab en un JTabbedPane
        tphCategorias.setSelectedIndex(0);
        
        modelo = Utils.generarModeloTabla(nombreColumnas);

        // Asignar el modelo a la tabla
        tblCategorias.setModel(modelo);

        int[] anchoColumnas = {15, 60, 20, 20, 20, 15, 25, 25}; // Anchos específicos para cada columna
        Utils.setAnchoColumnas(tblCategorias, anchoColumnas);
        Utils.ocultarColumnas(tblCategorias, 0);//ocultar la primera columna
        Utils.ocultarColumnas(tblCategorias, 4);//ocultar columna estado
        
        // limpia los datos existentes en la tabla.
        Utils.limpiarModeloTabla(modelo, tblCategorias);

        // Verificar si la lista tiene elementos
        if (listaCategorias.size() > 0) {
            // Iterar sobre la lista y agregar cada objeto a la tabla
            for (Categoria categoria : listaCategorias) {

                // Crea un array de objetos con los datos del objeto para agregar a la tabla.
                Object[] fila = new Object[]{
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getEdadMin(),
                    categoria.getEdadMax(),
                    categoria.getEstado(),
                    LiteralesTexto.LITERAL_VER,
                    LiteralesTexto.LITERAL_EDITAR,
                    LiteralesTexto.LITERAL_ELIMINAR
                };
                modelo.addRow(fila); // Agregar la fila al modelo de la tabla
            }
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblCategorias.setDefaultRenderer(Object.class, new Utils(18));
            
            Utils.configurarEstiloTabla(tblCategorias, jspCategorias);
            Utils.configurarBotonesAccion(tblCategorias);
        }
    }

    private Categoria getDatosCategoria(Categoria categoria){
        categoria.setNombre(txtNombre.getText());
        categoria.setEdadMax(Integer.parseInt(txtEdadMax.getText()));
        categoria.setEdadMin(Integer.parseInt(txtEdadMin.getText()));
        categoria.setEstado((Estado)jcbEstado.getSelectedItem());

        return categoria;
    }

    private void limpiarCampos() {
        txtNombre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtEdadMin.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtEdadMax.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
    }

    private void habilitarCampos(boolean band){
        txtNombre.setEditable(band);
        txtEdadMin.setEditable(band);
        txtEdadMax.setEditable(band);
        if(indicador == 0){
            jcbEstado.setSelectedIndex(0);
            jcbEstado.setEnabled(!band);
        } else{
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
            idSeleccionada = Integer.parseInt(tblCategorias.getValueAt(row, 0).toString()); // Supone que la ID está en la primera columna

            // Obtener los datos de la fila seleccionada
            String nombre = (String) tblCategorias.getValueAt(row, 1);
            int edadMin = Integer.parseInt(tblCategorias.getValueAt(row, 2).toString());
            int edadMax = Integer.parseInt(tblCategorias.getValueAt(row, 3).toString());
            Estado estado = (Estado)tblCategorias.getValueAt(row, 4);

            // Asignar los datos a los JTextField en el segundo panel
            txtNombre.setText(nombre);
            txtEdadMin.setText(String.valueOf(edadMin));
            txtEdadMax.setText(String.valueOf(edadMax));

            // Seleccionar el estado en el JComboBox
            jcbEstado.setSelectedItem(estado);

            // Cambiar al segundo panel donde están los JTextField
            tphCategorias.setSelectedIndex(1);            
            btnGuardar.setText("Modificar");
            indicador = 1;
            accionBotones(true, true);
            habilitarCampos(true);
        } else{
            //colocar alguna alerta
        }
    }

    private void listarCategorias(int paginaActual, int tamanioPagina) {
        totalPaginas = categoriaFacade.obtenerTotalPaginas(tamanioPagina);

        List<Categoria> listaCategorias = categoriaFacade.listarEntidadesPaginadas(paginaActual, tamanioPagina);

        // Actualizar el JLabel con la página actual
        lblPaginaActual.setText("Página " + paginaActual + " de " + totalPaginas);

        // Mostrar las categorías en la tabla
        listarCategorias(listaCategorias);
        actualizarEstadoBotones();// Actualizar el estado de los botones
    }

    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }

    public void eliminarCategoriaSeleccionada(int filaSeleccionada) {
        if (filaSeleccionada != -1) {
            // Capturar la ID de la fila seleccionada
            idSeleccionada = Integer.parseInt(tblCategorias.getValueAt(filaSeleccionada, 0).toString()); // Supone que la ID está en la primera columna
            if (Utils.mensajeConfirmacion(LiteralesTexto.ESTA_SEGURO_ELIMINAR_REGISTRO) == JOptionPane.YES_OPTION) {
                Categoria categoriaAEliminar = categoriaFacade.findCategoriaById(idSeleccionada);
                if(categoriaAEliminar != null){
                    try {
                        // Llamar al método para eliminar
                        categoriaFacade.eliminarCategoria(categoriaAEliminar);
                        JOptionPane.showMessageDialog(this, LiteralesTexto.REGISTRO_ELIMINADO_CORRECTAMENTE);

                        // Actualizar la tabla después de eliminar
                        totalPaginas = categoriaFacade.obtenerTotalPaginas(tamanioPagina);

                        // Verificar si la página actual es mayor que el total de páginas después de la eliminación
                        if (paginaActual > totalPaginas) {
                            paginaActual = totalPaginas; // Ajustar la página actual a la última disponible
                        }

                        // Actualizar la tabla después de eliminar
                        listarCategorias(paginaActual, tamanioPagina); // Volver a listar las categorías después de la eliminación

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

    public void mostrarInformacionCategoria(int filaSeleccionada) {

        // Supongamos que tienes un modelo de tabla que almacena los datos.
        String nombreCategoria = (String) tblCategorias.getValueAt(filaSeleccionada, 1); // Ajusta el índice de columna según tu tabla
        int edadMin = Integer.parseInt(tblCategorias.getValueAt(filaSeleccionada, 2).toString());
        int edadMax = Integer.parseInt(tblCategorias.getValueAt(filaSeleccionada, 3).toString());
        Estado estado = (Estado)tblCategorias.getValueAt(filaSeleccionada, 4);

        // Crear un mapa con los datos a mostrar
        Map<String, String> datos = new HashMap<String, String>(5);
        datos.put("Categoría:", nombreCategoria);
        datos.put("Edad Mínima:", String.valueOf(edadMin));
        datos.put("Edad Máxima:", String.valueOf(edadMax));
        datos.put("Estado:", estado.toString());

        // Llamar al método genérico para mostrar la información
        //primer parametro: nombre de tu boton, cuarto parametro: tamaño letra y ultimo parametro es la longitud de la cadena
        DialogUtils.mostrarInformacion("Aceptar","INFORMACIÓN DE LA CATEGORÍA", datos, 18, 20);
    }

    private boolean validarDatosCategoria(){
        if(!validarCampo(txtNombre.getText(), LiteralesTexto.ERROR_NOMBRE_CAMPO_VACIO)){
            return false;
        }

        if (!validarCampo(txtEdadMin.getText(), LiteralesTexto.ERROR_VALOR_EDAD_VACIA)) {
            return false;
        }

        if (!validarCampo(txtEdadMax.getText(), LiteralesTexto.ERROR_VALOR_EDAD_VACIA)) {
            return false;
        }
        return true;
    }

    private boolean validarCampo(String valor, String mensajeError) {
        if (!Utils.validarCadena(valor)) {
            Utils.mensajeError(mensajeError);
            return false;
        }
        return true;
    }
}
