/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifPagos.java
 *
 * Created on 11/12/2024, 08:27:55 PM
 */

package academiafulbito.vista.interfaces;

import javax.swing.JDesktopPane;

/**
 *
 * @author Ronald J
 */
public class jifPagos extends javax.swing.JInternalFrame {

    /** Creates new form jifPagos */
    JDesktopPane jDesktopPane;

    public jifPagos(JDesktopPane jdpModAF) {
        initComponents();
        jDesktopPane = jdpModAF;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jcbTipoConsulta = new javax.swing.JComboBox();
        txtDatoBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblFotoAlumno = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtApellidosAlumno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombresAlumno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombresApellidosPadre = new javax.swing.JTextField();
        txtDniPadre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalPago = new javax.swing.JTextField();
        btnBuscarConcepto = new javax.swing.JButton();
        txtConceptoPago = new javax.swing.JTextField();
        btnAgregarConcepto = new javax.swing.JButton();
        btnQuitarConcepto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jdchFechaPago = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jcbTipoCpbte = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtCorrelativo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPagoEfectivo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPagoYape = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPagoPlin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jspTblItemsConceptos = new javax.swing.JScrollPane();
        tblItemsConceptos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodConceptoPago = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        txtMontoAcumulado = new javax.swing.JTextField();
        btnVerPagos = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("PAGOS DIVERSOS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Consultar Datos"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcbTipoConsulta.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jcbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI ALUMNO", "DNI PADRE" }));
        jPanel4.add(jcbTipoConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 19, 150, 50));

        txtDatoBusqueda.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel4.add(txtDatoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, 50));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBuscar.setContentAreaFilled(false);
        jPanel4.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 90, 50));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 510, 90));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Foto Alumno", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 0, 12))); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(lblFotoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, 160));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 190, 190));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel1.setText("APELLIDOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 30));

        txtApellidosAlumno.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtApellidosAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 820, 30));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel2.setText("NOMBRES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 120, 30));

        txtNombresAlumno.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtNombresAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 820, 30));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel3.setText("APODERADO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 120, 30));

        txtNombresApellidosPadre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtNombresApellidosPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 570, 30));

        txtDniPadre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtDniPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 180, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("CONCEPTO DE PAGO");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 310, 20));

        txtTotalPago.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        txtTotalPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPago.setText("0.00");
        jPanel2.add(txtTotalPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 330, 150, 40));

        btnBuscarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/buscar.png"))); // NOI18N
        btnBuscarConcepto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBuscarConcepto.setBorderPainted(false);
        btnBuscarConcepto.setContentAreaFilled(false);
        jPanel2.add(btnBuscarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 60, 50));

        txtConceptoPago.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtConceptoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 890, 50));

        btnAgregarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/agregar.png"))); // NOI18N
        btnAgregarConcepto.setBorderPainted(false);
        btnAgregarConcepto.setContentAreaFilled(false);
        jPanel2.add(btnAgregarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 60, 50));

        btnQuitarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/quitar.png"))); // NOI18N
        btnQuitarConcepto.setBorderPainted(false);
        btnQuitarConcepto.setContentAreaFilled(false);
        jPanel2.add(btnQuitarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 60, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jdchFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 30));

        jLabel6.setText("CORRELATIVO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 130, 20));

        jLabel8.setText("FECHA DEL PAGO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        jcbTipoCpbte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jcbTipoCpbte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 140, 30));

        jLabel9.setText("TIPO CPBTE.");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 20));

        txtCorrelativo.setBackground(new java.awt.Color(204, 255, 255));
        txtCorrelativo.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtCorrelativo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCorrelativo.setText("000-000");
        jPanel1.add(txtCorrelativo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 150, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 204));
        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("FORMAS DE PAGO");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 290, 20));

        txtPagoEfectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoEfectivo.setText("0.00");
        jPanel1.add(txtPagoEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, 30));

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel12.setText("YAPE  ( S/.)");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 140, 30));

        txtPagoYape.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoYape.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoYape.setText("0.00");
        jPanel1.add(txtPagoYape, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 150, 30));

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel13.setText("PLIN ( S/.)");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, 30));

        txtPagoPlin.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoPlin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoPlin.setText("0.00");
        jPanel1.add(txtPagoPlin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 150, 30));

        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel14.setText("EFECTIVO  ( S/.)");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 310, 230));

        tblItemsConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspTblItemsConceptos.setViewportView(tblItemsConceptos);

        jPanel2.add(jspTblItemsConceptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 850, 230));

        jLabel7.setText("CODIGO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel11.setText("TOTAL A PAGAR");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 140, 40));

        txtCodConceptoPago.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtCodConceptoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 50));

        btnPagar.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        btnPagar.setForeground(new java.awt.Color(0, 0, 255));
        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/guardar-datos.png"))); // NOI18N
        btnPagar.setText("PAGAR");
        btnPagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnPagar.setContentAreaFilled(false);
        jPanel2.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 250, 70));

        txtMontoAcumulado.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        txtMontoAcumulado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoAcumulado.setText("0.00");
        jPanel2.add(txtMontoAcumulado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 210, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1190, 410));

        btnVerPagos.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        btnVerPagos.setText("VER PAGOS");
        btnVerPagos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnVerPagos.setContentAreaFilled(false);
        btnVerPagos.setEnabled(false);
        btnVerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPagosActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 190, 70));

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel10.setText("DNI");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 40, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPagosActionPerformed
        // TODO add your handling code here:
        
}//GEN-LAST:event_btnVerPagosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarConcepto;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarConcepto;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnQuitarConcepto;
    private javax.swing.JButton btnVerPagos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox jcbTipoConsulta;
    private javax.swing.JComboBox jcbTipoCpbte;
    private com.toedter.calendar.JDateChooser jdchFechaPago;
    private javax.swing.JScrollPane jspTblItemsConceptos;
    private javax.swing.JLabel lblFotoAlumno;
    private javax.swing.JTable tblItemsConceptos;
    private javax.swing.JTextField txtApellidosAlumno;
    private javax.swing.JTextField txtCodConceptoPago;
    private javax.swing.JTextField txtConceptoPago;
    private javax.swing.JTextField txtCorrelativo;
    private javax.swing.JTextField txtDatoBusqueda;
    private javax.swing.JTextField txtDniPadre;
    private javax.swing.JTextField txtMontoAcumulado;
    private javax.swing.JTextField txtNombresAlumno;
    private javax.swing.JTextField txtNombresApellidosPadre;
    private javax.swing.JTextField txtPagoEfectivo;
    private javax.swing.JTextField txtPagoPlin;
    private javax.swing.JTextField txtPagoYape;
    private javax.swing.JTextField txtTotalPago;
    // End of variables declaration//GEN-END:variables

}
