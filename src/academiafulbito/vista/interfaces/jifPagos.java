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

import academiafulbito.modelo.entidades.ProductoServicio;
import academiafulbito.controlador.beans.AlumnoFacade;
import academiafulbito.controlador.beans.CategoriaProductoFacade;
import academiafulbito.controlador.beans.PadreFacade;
import academiafulbito.controlador.beans.PagoFacade;
import academiafulbito.controlador.beans.ProductoServicioFacade;
import academiafulbito.controlador.beans.SerieFacade;
import academiafulbito.controlador.beans.TiposComprobanteFacade;
import academiafulbito.modelo.dto.ItemPagoDTO;
import academiafulbito.modelo.entidades.Alumno;
import academiafulbito.modelo.entidades.CategoriaProducto;
import academiafulbito.modelo.entidades.Matricula;
import academiafulbito.modelo.entidades.Padre;
import academiafulbito.modelo.entidades.Pago;
import academiafulbito.modelo.entidades.Serie;
import academiafulbito.modelo.entidades.TiposComprobante;
import academiafulbito.modelo.entidades.Usuario;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.TipoPago;
import academiafulbito.vista.logueo.JFLogin;
import academiafulbito.vista.utilidades.Imagen;
import academiafulbito.vista.utilidades.LiteralesTexto;
import academiafulbito.vista.utilidades.Utils;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald J
 */
public class jifPagos extends javax.swing.JInternalFrame {

    /** Creates new form jifPagos */
    
    public static ProductoServicio productoServicio;
    JDesktopPane jdp;
    private PadreFacade padreFacade;
    private AlumnoFacade alumnoFacade;
    private BigDecimal totalAPagar = BigDecimal.ZERO;
    private int paginaActual = 1;
    private int tamanioPagina = 5;
    private TiposComprobanteFacade tiposComprobanteFacade;
    private SerieFacade serieFacade ;
    private CategoriaProductoFacade categoriaProductoFacade;
    private PagoFacade pagoFacade;
    private ProductoServicioFacade productoServicioFacade; // *** NUEVA INSTANCIA DEL FACADE ***
    jifProductoServicios menuProductoServicios;    
    DefaultTableModel tableModel;
    private DecimalFormat decimalFormat;

    // Variables para almacenar los objetos seleccionados en los combos
    private TiposComprobante tipoComprobanteSeleccionado; // Objeto completo
    private Serie serieSeleccionada; // Objeto completo
    private CategoriaProducto categoriaProductoSeleccionada; // Objeto completo

    //almacenar la Matricula, Alumno y Padre actuales
    private Alumno alumnoActual; // Objeto completo
    private Padre padreActual; // Objeto completo
    public static Matricula matriculaActual; // Objeto completo <-- Para el caso de pagos de matricula
    // *** NUEVA LISTA PARALELA para almacenar los objetos ProductoServicio ***
    private List<ProductoServicio> itemsProductosList = new ArrayList<ProductoServicio>();

    // La variable de instancia para el usuario logueado en jifPagos.
    // Ya la habiamos declarado asi cuando conceptualizamos pasarla por constructor.
    // Ahora, en lugar de recibirla por constructor, la obtendremos de JFLogin.usuario
    private Usuario usuarioLogueadoAplicacion; // Variable de instancia para guardar el usuario en esta ventana

    public jifPagos(JDesktopPane jdpModAF) {
        initComponents();
        jdp = jdpModAF;

        // Es CRUCIAL que para este punto, el login ya se haya completado exitosamente
        // y JFLogin.usuario haya sido llenado con el objeto Usuario autenticado.
        this.usuarioLogueadoAplicacion = JFLogin.usuario; // <<<--- ACCEDEMOS A LA VARIABLE ESTATICA
        // *** Validar que el usuario se obtuvo correctamente ***
        // Si JFLogin solo crea jfPrincipal y luego jifPagos *despues* de un login exitoso,
        // entonces `JFLogin.usuario` NO deberia ser null aqui.
        if (this.usuarioLogueadoAplicacion == null) {
             // Esto indica un problema en el flujo de inicio o que el login fallo/cancelo
             // y esta ventana se abrio de todas formas.
             JOptionPane.showMessageDialog(this, "Error interno: No se pudo obtener la información del usuario logueado al abrir la ventana de Pagos.", "Error Fatal", JOptionPane.ERROR);
             // Decide si lanzar una excepcion o cerrar la ventana inmediatamente.
             // dispose(); // Cerrar la ventana de pagos
             // throw new IllegalStateException("Usuario logueado no puede ser null al iniciar jifPagos"); // Lanzar excepcion
             System.err.println("Usuario logueado es NULL al iniciar jifPagos. Verificar flujo de login."); // Log del error
             // Para evitar NullPointerException mas adelante, podrias deshabilitar botones clave:
             btnPagar.setEnabled(false);
             // Y salir de la inicializacion del constructor o de la ventana.
        } else {
             // Usuario obtenido con exito. Puedes usar this.usuarioLogueadoAplicacion.
             System.out.println("jifPagos iniciado para usuario: " + this.usuarioLogueadoAplicacion.getNombreUsuario()); // Log o depuracion
        }

        padreFacade = new PadreFacade();
        alumnoFacade = new AlumnoFacade();
        tiposComprobanteFacade = new TiposComprobanteFacade();
        serieFacade=new SerieFacade();
        categoriaProductoFacade = new CategoriaProductoFacade();
        pagoFacade = new PagoFacade();
        productoServicioFacade = new ProductoServicioFacade(); // *** Inicializa el nuevo facade ***

        // *** Inicializar decimalFormat AQUI, con simbolos personalizados ***
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(','); // <<<--- Usar la COMA como separador decimal
        symbols.setGroupingSeparator('.'); // <<<--- Usar el PUNTO como separador de miles

        // Crea el DecimalFormat usando un patron que incluya separador de miles y decimal,
        // y los simbolos personalizados.
        // "#,##0.00" es un patron común: muestra separador de miles cada 3 digitos (con el GroupingSeparator),
        // muestra al menos un digito antes del decimal (con 0), y muestra exactamente dos decimales (con .00 usando DecimalSeparator).
        decimalFormat = new DecimalFormat("#,##0.00", symbols); // <<<--- Patrón y Símbolos

        jpMatricula.setVisible(false);
        tableModel = (DefaultTableModel) tblItemsConceptos.getModel();
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int filaActualizada = e.getFirstRow();
                int columnaAfectada = e.getColumn();
                if(columnaAfectada == 3 || columnaAfectada == 4){
                    actualizarColumnaTotal(filaActualizada);
                }
                actualizarTotalAPagar();
            }
        });
        cargarInformacionEnCombos();
        jdchFechaPago.setDate(new Date()); // Establecer la fecha actual por defecto

        // ... (Habilitacion/deshabilitacion inicial de botones y campos de pago) ...
        // Asegurate que btnPagar este deshabilitado si usuarioLogueadoAplicacion es null
        if (this.usuarioLogueadoAplicacion != null) { // Solo habilitar si hay usuario
             btnPagar.setEnabled(false); // Se habilitara cuando haya items y (si es mensualidad) matricula
        } else {
             btnPagar.setEnabled(false); // Deshabilitado por defecto o si no hay usuario
        }

        // Deshabilitar botones inicialmente si no hay items o matricula seleccionada (para Mensualidad)
        btnPagar.setEnabled(false);
        btnQuitarConcepto.setEnabled(false);
        btnAgregarConcepto.setEnabled(false); // Deshabilitar agregar hasta que se seleccione un concepto O una matricula (si aplica)
        btnBuscarConcepto.setEnabled(false); // Deshabilitar buscar hasta que se seleccione categoria Y (si es mensualidad) matricula

        
        txtPagoEfectivo.setEditable(true);
        txtPagoYape.setEditable(true);
        txtPagoPlin.setEditable(true);
        txtTotalPago.setEditable(false); // El total es solo para mostrar
        
        lblCambio.setText(decimalFormat.format(BigDecimal.ZERO));

        // Asegurarse de que los campos de pago inicien en 0.00 y sean editables (si quieres que el usuario ingrese montos)
        reiniciaPrecioFormaPago();
        // Añadir listeners para actualizar el label de cambio y quizas validar input
        initPaymentFieldsListeners();
        //reiniciar los valor de UI
        reiniciarPreciosUI();
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
        btnBuscarConcepto = new javax.swing.JButton();
        txtConceptoPago = new javax.swing.JTextField();
        btnAgregarConcepto = new javax.swing.JButton();
        btnQuitarConcepto = new javax.swing.JButton();
        jspTblItemsConceptos = new javax.swing.JScrollPane();
        tblItemsConceptos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCodConceptoPago = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnVerPagos = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        jpMatricula = new javax.swing.JPanel();
        txtIdMatricula = new javax.swing.JTextField();
        btnBuscarMatricula = new javax.swing.JButton();
        txtDetallesMatricula = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDniAlumno = new javax.swing.JTextField();
        jcbCategoriaProducto = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jdchFechaPago = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jcbTipoCpbte = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtPagoEfectivo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPagoYape = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPagoPlin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotalPago = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jcbSerie = new javax.swing.JComboBox();
        lblCambio = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblOperacionGravada = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblMontoIgv = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblOperacionInafecta = new javax.swing.JLabel();
        lblOperacionExonerada = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblOperacionGratuita = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("PAGOS DIVERSOS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Consultar Datos"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcbTipoConsulta.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jcbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI ALUMNO" }));
        jcbTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoConsultaActionPerformed(evt);
            }
        });
        jPanel4.add(jcbTipoConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 19, 150, 50));

        txtDatoBusqueda.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtDatoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatoBusquedaKeyTyped(evt);
            }
        });
        jPanel4.add(txtDatoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, 50));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
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

        txtApellidosAlumno.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        getContentPane().add(txtApellidosAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 820, 30));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel2.setText("NOMBRES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 120, 30));

        txtNombresAlumno.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtNombresAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 570, 30));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel3.setText("APODERADO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 120, 30));

        txtNombresApellidosPadre.setBackground(new java.awt.Color(255, 255, 153));
        txtNombresApellidosPadre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtNombresApellidosPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 570, 30));

        txtDniPadre.setBackground(new java.awt.Color(255, 255, 153));
        txtDniPadre.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtDniPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 180, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("PRECIO");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 180, 20));

        btnBuscarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/buscar.png"))); // NOI18N
        btnBuscarConcepto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBuscarConcepto.setBorderPainted(false);
        btnBuscarConcepto.setContentAreaFilled(false);
        btnBuscarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarConceptoActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 60, 50));

        txtConceptoPago.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtConceptoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 410, 50));

        btnAgregarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/agregar.png"))); // NOI18N
        btnAgregarConcepto.setBorderPainted(false);
        btnAgregarConcepto.setContentAreaFilled(false);
        btnAgregarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarConceptoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 60, 50));

        btnQuitarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/quitar.png"))); // NOI18N
        btnQuitarConcepto.setBorderPainted(false);
        btnQuitarConcepto.setContentAreaFilled(false);
        btnQuitarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarConceptoActionPerformed(evt);
            }
        });
        jPanel2.add(btnQuitarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 60, 50));

        tblItemsConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID.PROD", "ID.CAT.PROD", "NOMBRE PRODUCTO", "PRECIO", "CANT", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspTblItemsConceptos.setViewportView(tblItemsConceptos);

        jPanel2.add(jspTblItemsConceptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 850, 250));

        jLabel7.setText("CODIGO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 20));

        txtCodConceptoPago.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        jPanel2.add(txtCodConceptoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 50));

        txtPrecio.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 180, 50));

        jLabel16.setText("CONCEPTO DE PAGO");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 310, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 870, 350));

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

        jLabel10.setBackground(new java.awt.Color(255, 255, 153));
        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel10.setText("     DNI");
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 70, 30));

        btnPagar.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        btnPagar.setForeground(new java.awt.Color(0, 0, 255));
        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/guardar-datos.png"))); // NOI18N
        btnPagar.setText("PAGAR");
        btnPagar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnPagar.setContentAreaFilled(false);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 250, 70));

        jpMatricula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jpMatricula.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdMatricula.setEditable(false);
        txtIdMatricula.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jpMatricula.add(txtIdMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 30));

        btnBuscarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/buscar.png"))); // NOI18N
        btnBuscarMatricula.setBorderPainted(false);
        btnBuscarMatricula.setContentAreaFilled(false);
        btnBuscarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMatriculaActionPerformed(evt);
            }
        });
        jpMatricula.add(btnBuscarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, 60));

        txtDetallesMatricula.setEditable(false);
        txtDetallesMatricula.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jpMatricula.add(txtDetallesMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 520, 30));

        jLabel17.setText("DETALLES DE LA MATRICULA DEL ALUMNO");
        jpMatricula.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 310, 20));

        jLabel19.setText("ID MATRICULA");
        jpMatricula.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 20));

        getContentPane().add(jpMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 700, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setText("    DNI :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, 70, 30));

        txtDniAlumno.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        getContentPane().add(txtDniAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, 180, 30));

        jcbCategoriaProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TECNOLOGIA", "MODA", "ZAPATERIA", "BEBIDAS", "MENSUALIDAD", "SNACK", "FASTFOOD" }));
        jcbCategoriaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCategoriaProductoActionPerformed(evt);
            }
        });
        getContentPane().add(jcbCategoriaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 160, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("CATEGORIA PRODUCTO");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jdchFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 30));

        jLabel8.setText("FECHA DEL PAGO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 20));

        jcbTipoCpbte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoCpbteActionPerformed(evt);
            }
        });
        jPanel1.add(jcbTipoCpbte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 140, 30));

        jLabel9.setText("TIPO CPBTE.");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 20));

        txtPagoEfectivo.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoEfectivo.setText("0.00");
        jPanel1.add(txtPagoEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 150, 20));

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel12.setText("YAPE  ( S/.)");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 140, 20));

        txtPagoYape.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoYape.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoYape.setText("0.00");
        jPanel1.add(txtPagoYape, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 150, 20));

        jLabel13.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel13.setText("PLIN ( S/.)");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 140, 20));

        txtPagoPlin.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        txtPagoPlin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPagoPlin.setText("0.00");
        jPanel1.add(txtPagoPlin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 150, 20));

        jLabel14.setBackground(new java.awt.Color(153, 255, 204));
        jLabel14.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("FORMAS DE PAGO");
        jLabel14.setOpaque(true);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 300, 20));

        txtTotalPago.setBackground(new java.awt.Color(255, 255, 153));
        txtTotalPago.setFont(new java.awt.Font("Bookman Old Style", 1, 24));
        txtTotalPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPago.setText("0.00");
        jPanel1.add(txtTotalPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 150, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 153));
        jLabel11.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel11.setText("TOTAL A PAGAR");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, 30));

        jLabel18.setText("SERIE");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 130, 20));

        jcbSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSerieActionPerformed(evt);
            }
        });
        jPanel1.add(jcbSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 150, 30));

        lblCambio.setBackground(new java.awt.Color(255, 255, 153));
        lblCambio.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lblCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCambio.setText("0.00");
        lblCambio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblCambio.setOpaque(true);
        jPanel1.add(lblCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 150, 30));

        jLabel20.setBackground(new java.awt.Color(255, 255, 153));
        jLabel20.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel20.setText("CAMBIO A DAR");
        jLabel20.setOpaque(true);
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 140, 30));

        jLabel21.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel21.setText("OP. GRAVADA:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, 20));

        lblOperacionGravada.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblOperacionGravada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOperacionGravada.setText("0.00");
        lblOperacionGravada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblOperacionGravada, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, 20));

        jLabel23.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel23.setText("MONTO I.G.V:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, 20));

        lblMontoIgv.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblMontoIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoIgv.setText("0.00");
        lblMontoIgv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblMontoIgv, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 150, 20));

        jLabel25.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel25.setText("OP. INAFECTA:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 20));

        lblOperacionInafecta.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblOperacionInafecta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOperacionInafecta.setText("0.00");
        lblOperacionInafecta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblOperacionInafecta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 150, 20));

        lblOperacionExonerada.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblOperacionExonerada.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOperacionExonerada.setText("0.00");
        lblOperacionExonerada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblOperacionExonerada, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 150, 20));

        jLabel28.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel28.setText("OP. EXONERADA:");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, 20));

        lblDescuento.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblDescuento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDescuento.setText("0.00");
        lblDescuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 150, 20));

        jLabel30.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel30.setText("TOTAL DSCTO.:");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 140, 20));

        jLabel31.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel31.setText("TOTAL GRATUITA:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, 20));

        lblOperacionGratuita.setFont(new java.awt.Font("Bookman Old Style", 1, 18));
        lblOperacionGratuita.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOperacionGratuita.setText("0.00");
        lblOperacionGratuita.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblOperacionGratuita, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 150, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 320, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 320, -1));

        jLabel22.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel22.setText("EFECTIVO  ( S/.)");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 140, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 320, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPagosActionPerformed
        // TODO add your handling code here:
        
}//GEN-LAST:event_btnVerPagosActionPerformed

    private void btnAgregarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarConceptoActionPerformed
        // TODO add your handling code here:
        // El boton agregar concepto ahora se habilita despues de buscar/seleccionar un producto
        // La validacion de la matricula (si es mensualidad) se hace antes de habilitar este boton.

        // Validar que hay un productoServicio seleccionado (de la busqueda previa)
         if (productoServicio == null) {
             Utils.mensajeError("Debe buscar y seleccionar un Concepto de Pago primero.");
             return;
         }

        // Validar que el concepto no este ya agregado (usa tu metodo existente)
         /*if (validarConceptoAgregado() && !Utils.validarDatoRegistroTabla(tblItemsConceptos, 0, txtCodConceptoPago.getText(), txtConceptoPago.getText())) {
            agregarProductoAPagar();
        }*/
        if (!Utils.validarDatoRegistroTabla(tblItemsConceptos, 0, String.valueOf(productoServicio.getIdProducto()), productoServicio.getNombreProducto())) {
            agregarProductoAPagar();
            // Después de agregar, limpia el producto seleccionado temporalmente
            productoServicio = null;
            limpiarCamposProductosServ(); // Limpia los campos del concepto en la UI

            // Deshabilitar btnAgregarConcepto de nuevo hasta que se busque otro producto
            btnAgregarConcepto.setEnabled(false);

        } else {
            Utils.mensajeInformacion("El concepto '" + productoServicio.getNombreProducto() + "' ya ha sido agregado.");
            // Después de informar que ya está, limpiar el producto seleccionado temporalmente
            productoServicio = null;
            limpiarCamposProductosServ();
            btnAgregarConcepto.setEnabled(false);
        }
    }//GEN-LAST:event_btnAgregarConceptoActionPerformed

    private void btnBuscarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarConceptoActionPerformed
        // TODO add your handling code here:
        // Este boton busca productos/servicios para agregarlos a la tabla
        // Solo debe estar habilitado si hay un Alumno/Padre seleccionado
        // Y, si la categoria es Mensualidad, si hay una Matricula seleccionada.

        // Validar que haya un alumno/padre seleccionado (usando los campos de DNI)
        if (!Utils.validarCadena(txtDniAlumno.getText()) && !Utils.validarCadena(txtDniPadre.getText())) {
            Utils.mensajeError("ERROR!!, DEBES BUSCAR EL ALUMNO O APODERADO PRIMERO");
            return;
        }

        // Si la categoría seleccionada es Mensualidad, validar que haya una Matrícula seleccionada
        if (categoriaProductoSeleccionada != null && categoriaProductoSeleccionada.getNombreCategoria().equalsIgnoreCase(LiteralesTexto.LITERAL_MENSUALIDAD)) {
            if (matriculaActual == null) {
                Utils.mensajeError("Para la categoría Mensualidad, debes buscar y seleccionar una Matrícula primero.");
                return;
            }
        }


        // Si las validaciones pasan, abrir la ventana de búsqueda de productos
        if (jfPrincipal.menuProductoServicios == null || jfPrincipal.menuProductoServicios.isClosed()) {
            jfPrincipal.menuProductoServicios = new jifProductoServicios(jdp);
            Utils.visualizarInternalFrame(jfPrincipal.menuProductoServicios, jdp);
        }

        // Configurar la ventana de búsqueda de productos:
        // 1. Permitir selección de una fila
        jfPrincipal.menuProductoServicios.permiteSelFila = 0; // Asumo que 0 significa SINGLE_SELECTION o similar

        // 2. Pasar el ID de la Categoría seleccionada para filtrar la búsqueda
        jfPrincipal.menuProductoServicios.idCategoriaProducto = obtenerIDCategoriaSeleccionada();

        // 3. Necesitas una forma en jifProductoServicios para DEVOLVER el ProductoServicio seleccionado
        // Puedes pasar una referencia de jifPagos a jifProductoServicios,
        // y jifProductoServicios, al seleccionar un producto, llama a un método en jifPagos
        // Por ejemplo: jifProductoServicios.setJifPagosParent(this);
        // En jifProductoServicios.btnSeleccionarProductoActionPerformed:
        //   ProductoServicio ps = obtenerProductoSeleccionadoDeLaTabla();
        
        //   this.dispose();

        // TEMPORAL: Necesitas implementar el mecanismo para que jifProductoServicios devuelva el objeto ProductoServicio seleccionado
        // Mientras tanto, tu código original solo abre la ventana. El resto (selección y retorno) debe estar en jifProductoServicios.
        // Asumiremos que, al cerrar jifProductoServicios después de una selección, la variable `jifPagos.productoServicio` estática *se llenará*
        // (aunque usar static para esto no es la mejor práctica, usaremos tu estructura existente).

        jfPrincipal.menuProductoServicios.listarProductoServicio(paginaActual, tamanioPagina); // Iniciar la búsqueda con la categoría filtrada
        jfPrincipal.menuProductoServicios.toFront();

        // IMPORTANTE: La lógica de habilitar btnAgregarConcepto debe ejecutarse *después* de que se ha seleccionado un producto
        // en `jifProductoServicios` y se ha retornado a `jifPagos`.
        // Esto generalmente se hace en un método de callback o cuando detectas que `productoServicio` estático ya no es null.
        if (productoServicio != null) btnAgregarConcepto.setEnabled(true);
    }//GEN-LAST:event_btnBuscarConceptoActionPerformed

    private void txtDatoBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoBusquedaKeyTyped
        char c = evt.getKeyChar();
        // Permitir solo dígitos y hasta 2 caracteres
        if (!Character.isDigit(c) || txtDatoBusqueda.getText().length() >= 12) {
            evt.consume(); // Ignorar el evento de tecla
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoBusquedaKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Limpiar la lista de items y la matricula si se busca una nueva persona
        tableModel.setRowCount(0);
        actualizarTotalAPagar();
        setMatriculaActual(null); // Limpiar matricula si se busca otra persona

        mostrarDatosPersona(txtDatoBusqueda.getText()); // Esto carga alumno/padre en variables de instancia y UI
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jcbTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoConsultaActionPerformed
        // Cuando cambia el tipo de consulta (DNI ALUMNO), limpiar los campos de persona
        limpiarCamposBusquedaPersona();
        alumnoActual = null;
        padreActual = null;
        // También limpiar la tabla de items y la matricula
        tableModel.setRowCount(0);
        actualizarTotalAPagar();
        setMatriculaActual(null);
         // Asegurarse de que la visibilidad de jpMatricula y la habilitacion de botones se ajuste
         // Esto se maneja mejor al seleccionar la CategoriaProducto, pero un reset aquí no hace daño.
         cargarPanelMatricula(); // Recarga el estado basado en la categoria actual
    }//GEN-LAST:event_jcbTipoConsultaActionPerformed

    private void btnQuitarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarConceptoActionPerformed
        // TODO add your handling code here:
        quitarProductoSeleccionado();
    }//GEN-LAST:event_btnQuitarConceptoActionPerformed

    private void btnBuscarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMatriculaActionPerformed
        // TODO add your handling code here:
        if (/*rbPagarMatricula.isSelected() &&*/ Utils.validarCadena(txtDniAlumno.getText())){
            if(jfPrincipal.menuMatricula==null || jfPrincipal.menuMatricula.isClosed()){
                jfPrincipal.menuMatricula=new jifMatricula(jdp);
                Utils.visualizarInternalFrame(jfPrincipal.menuMatricula, jdp);
            }
            jfPrincipal.menuMatricula.permiteSelFila=0;
            jfPrincipal.menuMatricula.dniAlumno=txtDniAlumno.getText();
            jfPrincipal.menuMatricula.listarMatriculas(paginaActual, tamanioPagina);
            jfPrincipal.menuMatricula.toFront();
        }
    }//GEN-LAST:event_btnBuscarMatriculaActionPerformed

    private void jcbTipoCpbteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoCpbteActionPerformed
        // Guarda el objeto TiposComprobante seleccionado
        tipoComprobanteSeleccionado = (TiposComprobante) jcbTipoCpbte.getSelectedItem();

        // Actualiza el combo de series basado en el tipo de comprobante seleccionado
        if (tipoComprobanteSeleccionado != null /*&& !(tipoComprobanteSeleccionado instanceof TiposComprobante && ((String)tipoComprobanteSeleccionado).startsWith("No hay tipos"))*/) {
            TiposComprobante tiposComprobante = (TiposComprobante) tipoComprobanteSeleccionado;
            int idTipoComprobante = tipoComprobanteSeleccionado.getIdTiposComprobante();
            // mi SerieFacade tiene un método para obtener series por ID de TipoComprobante
            List<Serie> seriesRelacionadas = serieFacade.obtenerTiposSeries(idTipoComprobante); 
            cargarComboSerie(seriesRelacionadas);
        } else {
             cargarComboSerie(null); // Limpiar combo serie si no hay tipo seleccionado válido
             serieSeleccionada = null; // Limpiar la serie seleccionada
        }
    }//GEN-LAST:event_jcbTipoCpbteActionPerformed

    private void jcbSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSerieActionPerformed
        // TODO add your handling code here:
        // Guarda el objeto Serie seleccionado
        serieSeleccionada = (Serie) jcbSerie.getSelectedItem();
        // No se necesita lógica adicional aquí por ahora, el objeto ya está guardado
    }//GEN-LAST:event_jcbSerieActionPerformed

    private void jcbCategoriaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCategoriaProductoActionPerformed
        // TODO add your handling code here:
        // Guarda el objeto CategoriaProducto seleccionado
        Object selectedItem = jcbCategoriaProducto.getSelectedItem();
         if (selectedItem instanceof CategoriaProducto) {
             categoriaProductoSeleccionada = (CategoriaProducto) selectedItem;
         } else {
             categoriaProductoSeleccionada = null; // Si es el placeholder o null
         }

        // Ajustar la UI (visibilidad de jpMatricula y habilitacion de botones)
        cargarPanelMatricula();

        // Limpiar campos de concepto y la tabla de items
        limpiarCamposProductosServ();
        productoServicio = null; // Limpiar el producto temporalmente seleccionado

        if (tableModel.getRowCount() > 0) {
            // Opcional: Preguntar al usuario si quiere quitar los productos si cambia la categoria
            // int confirm = Utils.mensajeConfirmacion("¿Desea limpiar la lista de productos al cambiar la categoría?");
            // if (confirm == JOptionPane.YES_OPTION) {
                Utils.mensajeInformacion("Limpiando lista de productos...");
                tableModel.setRowCount(0);
                actualizarTotalAPagar();
            // } else {
            //    // Considerar qué hacer si no se limpia - puede haber inconsistencias
            // }
        } else {
            actualizarTotalAPagar(); // Asegurarse que el total sea 0 si la tabla ya estaba vacía
        }

        // La habilitacion de botones (buscar/agregar concepto, pagar) se maneja en cargarPanelMatricula y activarBotonProcesoPago
    }//GEN-LAST:event_jcbCategoriaProductoActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        // TODO add your handling code here:

        // *** Validar que el usuario logueado esta disponible ***
        if (this.usuarioLogueadoAplicacion == null) {
             // Esto no deberia ocurrir si el constructor valido correctamente,
             // pero es una capa adicional de seguridad antes de llamar al Facade.
             JOptionPane.showMessageDialog(this, "No se pudo registrar el pago: Usuario no autenticado.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }

        // 1. Obtener y Validar el Total a Pagar (UI)
        BigDecimal totalAPagarCalculado = BigDecimal.ZERO;
        try {
            totalAPagarCalculado = Utils.parseBigDecimal(decimalFormat, txtTotalPago.getText());
            if (totalAPagarCalculado.compareTo(BigDecimal.ZERO) <= 0) {
                Utils.mensajeInformacion("No hay conceptos agregados o el total a pagar es cero. No se puede procesar el pago.");
                return;
            }
        } catch (ParseException e) {
             Utils.mensajeError("El total a pagar no es un número válido: " + e.getMessage());
             return;
        }

        // 2. Validar y obtener montos recibidos por tipo de pago (Contado) (UI)
        BigDecimal montoEfectivo = BigDecimal.ZERO;
        BigDecimal montoYape = BigDecimal.ZERO;
        BigDecimal montoPlin = BigDecimal.ZERO;
        BigDecimal vueltoAEntregar = BigDecimal.ZERO;
        BigDecimal totalPagadoIngresado = BigDecimal.ZERO;

        try {
            montoEfectivo = Utils.parseBigDecimal(decimalFormat, txtPagoEfectivo.getText());
            montoYape = Utils.parseBigDecimal(decimalFormat, txtPagoYape.getText());
            montoPlin = Utils.parseBigDecimal(decimalFormat, txtPagoPlin.getText());
            vueltoAEntregar = Utils.parseBigDecimal(decimalFormat, lblCambio.getText());

            totalPagadoIngresado = montoEfectivo.add(montoYape).add(montoPlin);

        } catch (ParseException e) {
            Utils.mensajeError("Uno o más montos de pago ingresados (Efectivo, Yape, Plin, Cambio a Dar) no son números válidos: " + e.getMessage());
            return;
        }

        // 3. Validar que la suma pagada es suficiente para el Pago al Contado (UI)
        if (totalPagadoIngresado.compareTo(totalAPagarCalculado) < 0) {
            Utils.mensajeError("El total pagado (" + decimalFormat.format(totalPagadoIngresado) + ") es menor que el total a pagar ("
                    + decimalFormat.format(totalAPagarCalculado) + ").");
            return;
        }

        // 4. Calcular y Mostrar Cambio (ya se hizo y mostró en actualizarLabelCambio())
        
        // 5. Recolectar Información Adicional Necesaria para el Objeto Pago y Entidades Relacionadas
        Date fechaPago = jdchFechaPago.getDate();
        if (fechaPago == null) {
            Utils.mensajeError("Error interno: La fecha del pago no está seleccionada.");
            return;
        }

        TiposComprobante tipoComprobante = this.tipoComprobanteSeleccionado;
        if (tipoComprobante == null || !(tipoComprobante instanceof TiposComprobante)) {
            Utils.mensajeError("Debe seleccionar un Tipo de Comprobante válido.");
            return;
        }

        Serie serie = this.serieSeleccionada;
        if (serie == null || !(serie instanceof Serie)) {
            Utils.mensajeError("Debe seleccionar una Serie válida.");
            return;
        }

        // Datos del cliente: Usamos las variables de instancia `alumnoActual` y `padreActual`
        String docIdentidad = "";
        String razonSocial = null;
        String tipoDocumentoCliente = "";
        // *** LÓGICA PARA DETERMINAR EL TIPO Y DATOS DEL DOCUMENTO DEL CLIENTE ***
        // Copia/Adapta la lógica del punto 5 de la explicacion anterior.
        // EJEMPLO SIMPLE: Usa DNI/Nombre del Padre (si existe) o Alumno, y asume tipo '1' (DNI).
        if (padreActual != null) {
            docIdentidad = padreActual.getDniPadre();
            razonSocial = padreActual.getApellidoPadre() + " " + padreActual.getNombrePadre();
            tipoDocumentoCliente = "1"; // Asumo '1' para DNI
        } else if (alumnoActual != null) {
            docIdentidad = alumnoActual.getDniAlumno();
            razonSocial = alumnoActual.getApellidoAlumno() + " " + alumnoActual.getNombreAlumno();
            tipoDocumentoCliente = "1"; // Asumo '1' para DNI
        } else {
            Utils.mensajeError("Error interno: No se ha seleccionado un Alumno/Apoderado para registrar el pago.");
            return;
        }

        // Matrícula: Usamos la variable de instancia `matriculaActual`
        Matricula matriculaParaPago = this.matriculaActual;

        // Validamos que, si la categoría es Mensualidad, la matricula esté seleccionada.
        if (categoriaProductoSeleccionada != null && categoriaProductoSeleccionada.getNombreCategoria().equalsIgnoreCase(LiteralesTexto.LITERAL_MENSUALIDAD)) {
            if (matriculaParaPago == null) {
                Utils.mensajeError("Para registrar un pago de Mensualidad, debe buscar y seleccionar una Matrícula.");
                return;
            }
        }

        // Usuario logueado: *** USANDO LA VARIABLE DE INSTANCIA OBTENIDA EN EL CONSTRUCTOR ***
        // Ya no necesitamos leer JFLogin.usuario aqui, ya esta en `this.usuarioLogueadoAplicacion`
        Usuario usuarioLogueadoParaPago = this.usuarioLogueadoAplicacion; // <<-- Usamos la variable de instancia

        // 6. Calcular Desglose de Montos (operacionGravada, montoIgv, etc.) - Lógica en UI
        // No se calcula de nuevo aquí, pero se obtienen los valores de los labels para el objeto Pago.
        // Asegurarse de que los labels esten actualizados antes de leerlos!
        // llamar a actualizarDesgloseTributarioUI() justo antes si no confias en el TableModelListener.
        // actualizarDesgloseTributarioUI(); // Opcional, si no confias en el listener
        BigDecimal totalOperacionGravada = BigDecimal.ZERO;
        BigDecimal totalMontoIgv = BigDecimal.ZERO;
        BigDecimal totalOperacionInafecta = BigDecimal.ZERO;
        BigDecimal totalOperacionExonerada = BigDecimal.ZERO;
        BigDecimal totalOperacionGratuita = BigDecimal.ZERO;
        BigDecimal totalDescuento = BigDecimal.ZERO;

        try {
            // Leemos los valores YA CALCULADOS Y MOSTRADOS en los labels
            totalOperacionGravada = Utils.parseBigDecimal(decimalFormat, lblOperacionGravada.getText());
            totalMontoIgv = Utils.parseBigDecimal(decimalFormat, lblMontoIgv.getText());
            totalOperacionInafecta = Utils.parseBigDecimal(decimalFormat, lblOperacionInafecta.getText());
            totalOperacionExonerada = Utils.parseBigDecimal(decimalFormat, lblOperacionExonerada.getText());
            totalOperacionGratuita = Utils.parseBigDecimal(decimalFormat, lblOperacionGratuita.getText());
            totalDescuento = Utils.parseBigDecimal(decimalFormat, lblDescuento.getText());

        } catch (ParseException e) {
             Utils.mensajeError("Error al leer los valores calculados del desglose desde la interfaz: " + e.getMessage());
             return; // Detener si no podemos leer el desglose calculado.
        }

        // 7. Preparar el Objeto Pago (Instancia Java) - Llenar con todos los datos
        Pago nuevoPago = new Pago();
        // ID_pago autogenerado
        // Matricula, Usuario, TiposComprobante, Serie se asignan en el Facade con los objetos adjuntos
        nuevoPago.setFechaPago(fechaPago);
        nuevoPago.setMonto(totalAPagarCalculado); // Monto TOTAL del comprobante.
        // *** ASIGNAR LOS NUEVOS CAMPOS DE MONTOS RECIBIDOS Y CAMBIO ***
        // Asegurate de setear los valores a 0.00 si no se usó ese método de pago.
        // Yape y Plin son el monto exacto ingresado. Efectivo es el monto exacto ingresado.
        nuevoPago.setMontoEfectivoRecibido(montoEfectivo); // El monto que el cliente dio en efectivo
        nuevoPago.setMontoYapeRecibido(montoYape);       // El monto que el cliente dio por Yape
        nuevoPago.setMontoPlinRecibido(montoPlin);       // El monto que el cliente dio por Plin
        nuevoPago.setCambioEntregado(vueltoAEntregar);      // El cambio a entregar (0 si no hubo cambio positivo)
        nuevoPago.setEstadoPago(Estado.ACTIVO); // Enum de tu entidad/DB ('Activo')
        // Correlativo se asigna en el Facade
        nuevoPago.setDocIdentidad(docIdentidad);
        nuevoPago.setRazonSocial(razonSocial);
        nuevoPago.setTipoDocumento(tipoDocumentoCliente);
        // Asignar los montos de desglose calculados
        nuevoPago.setOperacionGravada(totalOperacionGravada);
        nuevoPago.setMontoIgv(totalMontoIgv);
        nuevoPago.setOperacionInafecta(totalOperacionInafecta);
        nuevoPago.setOperacionExonerada(totalOperacionExonerada);
        nuevoPago.setOperacionGratuita(totalOperacionGratuita);
        nuevoPago.setDescuento(totalDescuento);
        nuevoPago.setMotivoAnulado(null);
        nuevoPago.setTipoPago(TipoPago.CONTADO); // Enum de tu entidad/DB. HARDCODEADO A CONTADO.
        nuevoPago.setFechaHora(new Date()); // Fecha y hora actual del registro.
        nuevoPago.setHoraRegistro(new Date()); // Hora actual del registro.
        nuevoPago.setMoneda("PEN");
        nuevoPago.setTipoCambio(null); // null para PEN


        // 8. EXTRAER DATOS DE LA TABLA SWING Y PASAR AL FACADE
        // Creamos la lista de DTOs para los detalles del pago.
        List<ItemPagoDTO> itemsParaDetalle = new java.util.ArrayList<ItemPagoDTO>();
        if (!itemsProductosList.isEmpty()) { // Iteramos sobre la lista paralela de objetos
            // Validamos que las listas tengan el mismo tamaño, si no, algo anda mal.
            if (itemsProductosList.size() != tableModel.getRowCount()) {
                Utils.mensajeError("Error interno: El número de ítems en la lista y la tabla no coincide.");
                return;
            }

            for (int i = 0; i < itemsProductosList.size(); i++) {
                try {
                    // Obtenemos el objeto ProductoServicio de la lista paralela
                    ProductoServicio prodServicio = itemsProductosList.get(i);
                    // Obtener cantidad y subtotal de la tabla usando parseBigDecimal()
                    String cantidadStr = tableModel.getValueAt(i, 4) != null ? tableModel.getValueAt(i, 4).toString().trim() :
                        decimalFormat.format(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP)); // Columna 4: CANT
                    String subtotalStr = tableModel.getValueAt(i, 5) != null ? tableModel.getValueAt(i, 5).toString().trim() :
                        decimalFormat.format(BigDecimal.ZERO); // Columna 5: TOTAL

                    BigDecimal cantidad = Utils.parseBigDecimal(decimalFormat, cantidadStr);
                    BigDecimal subtotal = Utils.parseBigDecimal(decimalFormat, subtotalStr);

                    // Validacion basica de datos (redundante si ya validaste al agregar/actualizar, pero seguro)
                    if (prodServicio.getIdProducto() <= 0 || prodServicio.getPrecio().compareTo(BigDecimal.ZERO) < 0 || subtotal.compareTo(BigDecimal.ZERO) < 0) {
                        Utils.mensajeError("Error: Datos inválidos en la fila " + (i + 1) + " de la tabla de ítems.");
                        return; // Detener si un item es inválido
                    }
                    if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
                        Utils.mensajeError("La cantidad debe ser mayor que cero en fila " + (i + 1));
                        return;
                    }

                    // Crear el DTO (usando los datos de la tabla y el objeto ProductoServicio)
                    itemsParaDetalle.add(new ItemPagoDTO(
                            prodServicio.getIdProducto(), // ID del Producto desde el objeto (más seguro que la tabla String)
                            prodServicio.getNombreProducto(), // Nombre desde el objeto
                            prodServicio.getPrecio(), // Precio unitario desde el objeto
                            cantidad, // Cantidad desde la tabla Swing
                            subtotal // Subtotal desde la tabla Swing
                            ));

                } catch (ParseException e) {
                     System.err.println("Error de formato al extraer datos de la tabla para DTO en fila " + (i + 1) + ": " + e.getMessage());
                     Utils.mensajeError("Error al leer los datos numéricos de la tabla de ítems en la fila " + (i + 1) + ". Verifique los formatos.");
                     return;
                } catch (Exception e) { // Otros errores
                     System.err.println("Error inesperado al extraer datos de la tabla para DTO en fila " + (i + 1) + ": " + e.getMessage());
                     Utils.mensajeError("Error inesperado al preparar los datos de los ítems en la fila " + (i + 1) + ".");
                     return;
                }
            }
        } else {
            // Si la lista de productos está vacía pero el total a pagar > 0 (ya validado), esto es un error lógico.
             if (totalAPagarCalculado.compareTo(BigDecimal.ZERO) > 0) {
                  Utils.mensajeError("Error: El total a pagar es mayor que cero, pero no hay ítems agregados.");
                  return;
             }
             // Si total a pagar es 0 y la lista está vacía, no hay nada que procesar, la validación inicial lo manejo.
        }


        // 9. Llamar al PagoFacade para registrar el pago transactionalmente
        try {
            // Llamamos al Facade, pasando el objeto Pago, entidades relacionadas y la lista de items.
            pagoFacade.registrarPagoContadoTransactionally(
                    nuevoPago, // Objeto Pago (con desgloses ya puestos)
                    serie, // Objeto Serie
                    matriculaParaPago, // Objeto Matricula (puede ser null)
                    usuarioLogueadoParaPago, // Objeto Usuario
                    itemsParaDetalle // *** La lista de DTOs de los items ***
            );


            // Si el Facade no lanzó excepción, todo fue bien.
            // El mensaje de éxito ya se mostró en el Facade.

            // 10. Limpiar la interfaz después del éxito
            limpiarFormularioPago();


        } catch (Exception e) {
            // 11. Manejar Errores que vienen del Facade
            // El Facade ya hizo rollback si hubo un error de DB.
            // Aquí mostramos el mensaje de error al usuario que viene del Facade.
            System.err.println("Error al registrar pago (capturado en UI): " + e.getMessage());
            e.printStackTrace(); // Imprimir stack trace para depuración
            Utils.mensajeError("No se pudo registrar el pago: " + e.getMessage());
            // No limpiar el formulario si hay un error, para que el usuario vea los datos.
        }

        // No se necesita bloque finally ni cerrar conexión/statements aquí,
        // eso lo maneja el EntityManager dentro del Facade.
        
    }//GEN-LAST:event_btnPagarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAgregarConcepto;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarConcepto;
    private javax.swing.JButton btnBuscarMatricula;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnQuitarConcepto;
    private javax.swing.JButton btnVerPagos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox jcbCategoriaProducto;
    private javax.swing.JComboBox jcbSerie;
    private javax.swing.JComboBox jcbTipoConsulta;
    private javax.swing.JComboBox jcbTipoCpbte;
    private com.toedter.calendar.JDateChooser jdchFechaPago;
    private javax.swing.JPanel jpMatricula;
    private javax.swing.JScrollPane jspTblItemsConceptos;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblFotoAlumno;
    private javax.swing.JLabel lblMontoIgv;
    private javax.swing.JLabel lblOperacionExonerada;
    private javax.swing.JLabel lblOperacionGratuita;
    private javax.swing.JLabel lblOperacionGravada;
    private javax.swing.JLabel lblOperacionInafecta;
    private javax.swing.JTable tblItemsConceptos;
    private javax.swing.JTextField txtApellidosAlumno;
    public static javax.swing.JTextField txtCodConceptoPago;
    public static javax.swing.JTextField txtConceptoPago;
    private javax.swing.JTextField txtDatoBusqueda;
    public static javax.swing.JTextField txtDetallesMatricula;
    private javax.swing.JTextField txtDniAlumno;
    private javax.swing.JTextField txtDniPadre;
    public static javax.swing.JTextField txtIdMatricula;
    private javax.swing.JTextField txtNombresAlumno;
    private javax.swing.JTextField txtNombresApellidosPadre;
    private javax.swing.JTextField txtPagoEfectivo;
    private javax.swing.JTextField txtPagoPlin;
    private javax.swing.JTextField txtPagoYape;
    public static javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotalPago;
    // End of variables declaration//GEN-END:variables

    private boolean validarConceptoAgregado(){
        boolean esCorrecto = Utils.validarCadena(txtCodConceptoPago.getText());
        if(!esCorrecto){
            Utils.mensajeError("No se ha cargado aun, el concepto de pago.!!");
        }
        return esCorrecto;
    }

    // Asegúrate de que mostrarDatosAlumno y mostrarDatosPadre guarden los objetos completos
    private void mostrarDatosAlumno(Alumno alumno, Padre padre) {
        this.alumnoActual = alumno; // Guardar objeto alumno
        this.padreActual = padre; // Guardar objeto padre

        txtApellidosAlumno.setText(alumno != null ? alumno.getApellidoAlumno() : LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresAlumno.setText(alumno != null ? alumno.getNombreAlumno() : LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDniAlumno.setText(alumno != null ? alumno.getDniAlumno() : LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresApellidosPadre.setText(padre != null ? (padre.getApellidoPadre() + " " + padre.getNombrePadre()) : LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDniPadre.setText(padre != null ? padre.getDniPadre() : LiteralesTexto.LITERAL_CADENA_VACIA);

        // Cargar foto
        try {
            if (alumno != null && alumno.getFoto() != null) {
                Image image = Imagen.abrirImagen(alumno.getFoto());
                Utils.cargarImagenEnLabel(image, lblFotoAlumno);
            } else {
                // Si no hay alumno o no tiene foto, poner imagen por defecto
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/noDisponible.png"));
                Utils.cargarImagenEnLabel(imageIcon.getImage(), lblFotoAlumno);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
             // Si hay un error al cargar la foto, poner imagen por defecto
             ImageIcon imageIcon = new ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/noDisponible.png"));
             Utils.cargarImagenEnLabel(imageIcon.getImage(), lblFotoAlumno);
        }
        // Si se carga un alumno/padre, resetear la matricula actual si no coincide
         if (matriculaActual != null && (this.alumnoActual == null || matriculaActual.getAlumno() == null || matriculaActual.getAlumno().getIdAlumno() != this.alumnoActual.getIdAlumno())) {
             setMatriculaActual(null); // Limpiar info de matricula si no corresponde al nuevo alumno/padre
         }
          // Después de cargar los datos de la persona, habilitar btnBuscarConcepto (si la categoria no es Mensualidad)
          // o btnBuscarMatricula (si es Mensualidad). Esto se maneja en cargarPanelMatricula().
          cargarPanelMatricula();
    }
    
    private void mostrarDatosPadre(String dni) {
        try {
            Padre padre = padreFacade.findPadreByDni(dni);
            if (padre != null) {
                Alumno alumno = alumnoFacade.findAlumnoByIdPadre(padre.getIdPadre()); // Asumo un método para buscar Alumno por ID Padre
                mostrarDatosAlumno(alumno, padre); // Este método guarda los objetos y actualiza UI
            } else {
                Utils.mensajeInformacion("No se encontró un Padre con el DNI ingresado.");
                limpiarCamposBusquedaPersonaCompleta(); // Limpiar todos los campos de persona
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Utils.mensajeError("Error al buscar padre: " + ex.getMessage());
            limpiarCamposBusquedaPersonaCompleta();
        }
    }

    private void mostrarDatosAlumno(String dni){
        Alumno alumno = alumnoFacade.findAlumnoByDni(dni);
        if (alumno!= null){
            Padre padre = alumno.getPadre(); // Asumo que Alumno tiene referencia a Padre
            mostrarDatosAlumno(alumno, padre); // Este método guarda los objetos y actualiza UI
        } else {
             Utils.mensajeInformacion("No se encontró un Alumno con el DNI ingresado.");
             limpiarCamposBusquedaPersonaCompleta();
        }
    }

    private void mostrarDatosPersona(String dni) {
        if (Utils.validarCadena(dni)) {
            // Asumo que 0 es DNI ALUMNO y 1 es DNI PADRE en tu jcbTipoConsulta
            if (jcbTipoConsulta.getSelectedIndex() == 0) { // DNI ALUMNO
                mostrarDatosAlumno(dni);
            } else if (jcbTipoConsulta.getSelectedIndex() == 1) { // DNI PADRE
                mostrarDatosPadre(dni);
            }
        } else {
            Utils.mensajeInformacion("Ingrese un DNI para buscar.");
            limpiarCamposBusquedaPersonaCompleta();
        }
    }

    private void limpiarCamposBusquedaPersona() {
        txtApellidosAlumno.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresAlumno.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresApellidosPadre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDniPadre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDatoBusqueda.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
    }

    private void limpiarCamposProductosServ(){
        txtCodConceptoPago.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtConceptoPago.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtPrecio.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
    }

    private void limpiarCamposProductosServ1(){
        // Limpiar campos de Matricula
        txtIdMatricula.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDetallesMatricula.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        matriculaActual = null; // Limpiar el objeto Matricula seleccionado

    }

    private void activarBotonProcesoPago(){
        boolean hayItemsEnTabla = tableModel.getRowCount() > 0;
        boolean personaSeleccionada = alumnoActual != null || padreActual != null; // O basarse en txtDniAlumno/txtDniPadre
        boolean esCategoriaMensualidad = categoriaProductoSeleccionada != null && categoriaProductoSeleccionada.getNombreCategoria().equalsIgnoreCase(LiteralesTexto.LITERAL_MENSUALIDAD);
        boolean matriculaSeleccionada = matriculaActual != null;

        // btnQuitarConcepto: Habilitado si hay items seleccionados en la tabla (tu lógica existente lo maneja, no lo sobreescribimos aquí)
         btnQuitarConcepto.setEnabled(hayItemsEnTabla); // Tu listener de tabla lo hace

        // btnPagar: Habilitado si hay items en la tabla Y (si es mensualidad) hay matricula seleccionada
        if (hayItemsEnTabla) {
             if (esCategoriaMensualidad) {
                  btnPagar.setEnabled(matriculaSeleccionada); // Solo si hay items Y matricula para mensualidad
             } else {
                  btnPagar.setEnabled(true); // Habilitado si hay items para otras categorias
             }
        } else {
             btnPagar.setEnabled(false); // Deshabilitado si no hay items
        }

        // btnBuscarConcepto: Habilitado si hay persona seleccionada
        btnBuscarConcepto.setEnabled(personaSeleccionada);

        // btnAgregarConcepto: Habilitado por setProductoSeleccionado() cuando se selecciona un producto de la búsqueda.
        // Inicialmente está deshabilitado.

        // btnBuscarMatricula: Habilitado si es categoria Mensualidad Y hay persona seleccionada
        btnBuscarMatricula.setEnabled(esCategoriaMensualidad && personaSeleccionada);
    }

    private void actualizarTotalAPagar(){
        BigDecimal totalCalculado = BigDecimal.ZERO;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
                totalCalculado = totalCalculado.add(Utils.parseBigDecimal(decimalFormat, tableModel.getValueAt(i, 5).toString())); // Manejar coma y parsear
            } catch (ParseException  e) {
                System.err.println("Error al sumar total: Valor no numérico en la fila " + i + ", columna 5.");
                e.printStackTrace();
            }
        }
        // Actualiza el JTextField del total
        txtTotalPago.setText(decimalFormat.format(totalCalculado.setScale(2, RoundingMode.HALF_UP)));
        this.totalAPagar = totalCalculado.setScale(2, RoundingMode.HALF_UP); // Actualiza variable de instancia

        // *** LLAMAR AL METODO PARA ACTUALIZAR EL DESGLOSE EN LA UI ***
        actualizarDesgloseTributarioUI();

        // Llamar a activarBotonProcesoPago ya que el total o el número de items ha cambiado
        activarBotonProcesoPago();

        // El label de cambio se actualiza cuando se ingresan los montos en los campos de pago (Punto 5)
        // No se actualiza automáticamente al cambiar el total a pagar.
        txtPagoEfectivo.setText(decimalFormat.format(totalCalculado.setScale(2, RoundingMode.HALF_UP)));
    }

    // *** NUEVO METODO PARA CALCULAR Y ACTUALIZAR EL DESGLOSE TRIBUTARIO EN LOS LABELS DE LA UI ***
    private void actualizarDesgloseTributarioUI(){
        BigDecimal totalOperacionGravada = BigDecimal.ZERO;
        BigDecimal totalMontoIgv = BigDecimal.ZERO;
        BigDecimal totalOperacionInafecta = BigDecimal.ZERO;
        BigDecimal totalOperacionExonerada = BigDecimal.ZERO;
        BigDecimal totalOperacionGratuita = BigDecimal.ZERO; // Si aplica
        BigDecimal totalDescuento = BigDecimal.ZERO;       // Si aplica

        // La posicion en itemsProductosList corresponde a la fila en tableModel.
        // *** LÓGICA DE DESGLOSE REAL (basada en itemsProductosList) ***
        BigDecimal IGV_RATE;
        // La posicion en itemsProductosList corresponde a la fila en tableModel.
        try {
            IGV_RATE = Utils.parseBigDecimal(decimalFormat, "0.18"); // Tasa de IGV
            // Iterar sobre la lista de objetos ProductoServicio almacenados
        } catch (ParseException ex) {
            Utils.mensajeError("Error para obtener la Tasa de IGV");
            return;
        }
        for (int i = 0; i < itemsProductosList.size(); i++) {
            try {
                // Obtenemos el objeto ProductoServicio de la lista paralela
                ProductoServicio ps = itemsProductosList.get(i);

                // Obtenemos el total de la fila CORRESPONDIENTE en la tabla Swing
                // Aunque podriamos calcularlo de ps.getPrecio() * cantidad de la tabla,
                // usar el total ya calculado en la tabla asegura consistencia visual.
                // Validamos el indice antes de acceder a tableModel
                BigDecimal totalItem = BigDecimal.ZERO;
                if (i < tableModel.getRowCount()) {
                    totalItem = Utils.parseBigDecimal(decimalFormat, tableModel.getValueAt(i, 5).toString());
                } else {
                    Utils.mensajeError("Error lógico: La lista de productos es más larga que la tabla Swing en el cálculo de desglose UI.");
                    continue; // Saltar esta fila si no hay correspondencia
                }

                // Obtenemos el estado tributario del objeto ProductoServicio
                String estadoTributario = ps.getEstadoTributario(); // Asumo que el getter existe y devuelve el String
                // *** AQUI APLICAS LA LÓGICA DE CÁLCULO SEGÚN EL ESTADO TRIBUTARIO ***
                // Debes usar los nombres de estado que guardas en la DB (ej: 'GRAVADO', 'EXONERADO', 'INAFECTO', 'GRATUITO')
                // Considera el caso null o vacio si la columna `estado_tributario` permite null/vacio.
                if (estadoTributario != null) {
                    if (estadoTributario.equalsIgnoreCase("GRAVADO")) {
                        // Cálculo para productos gravados
                        BigDecimal operacionGravadaItem = totalItem.divide(BigDecimal.ONE.add(IGV_RATE), 2, RoundingMode.HALF_UP);
                        BigDecimal montoIgvItem = totalItem.subtract(operacionGravadaItem).setScale(2, RoundingMode.HALF_UP);
                        totalOperacionGravada = totalOperacionGravada.add(operacionGravadaItem);
                        totalMontoIgv = totalMontoIgv.add(montoIgvItem);

                    } else if (estadoTributario.equalsIgnoreCase("EXONERADO")) {
                        // Cálculo para productos exonerados
                        totalOperacionExonerada = totalOperacionExonerada.add(totalItem);

                    } else if (estadoTributario.equalsIgnoreCase("INAFECTO")) {
                        // Cálculo para productos inafectos
                        totalOperacionInafecta = totalOperacionInafecta.add(totalItem);

                    } else if (estadoTributario.equalsIgnoreCase("GRATUITO")) {
                        // Cálculo para productos gratuitos (generalmente no suman al total de pago, pero si al desglose)
                        // Si los productos gratuitos se añaden a la tabla con precio 0, su totalItem será 0.
                        // Si se añaden con precio > 0 pero son gratuitos (ej: para muestras), la lógica es diferente.
                        // Asumiendo que si estado es GRATUITO, su totalItem deberia ser 0 o se suma a operacion_gratuita.
                        // Verifica tu logica de negocio para items gratuitos en la tabla Swing.
                        totalOperacionGratuita = totalOperacionGratuita.add(totalItem);
                        // Si totalItem > 0 para gratuitos, quizas NO SUMA al totalAPagarCalculado pero si va aqui? Revisa tu negocio.
                    }
                    // Agregar logica para DESCUENTO si aplica a nivel de item
                    // totalDescuento = totalDescuento.add(...); // Si hay descuentos por item

                } else {
                    // Manejar caso donde el estado tributario es null o desconocido
                    Utils.mensajeError("Advertencia: Estado tributario no definido para producto con ID " + ps.getIdProducto() + " ('" + ps.getNombreProducto() + "'). No se incluyó en el desglose.");
                    // Decide si esto deberia detener el proceso o simplemente advertir.
                }


            } catch (ParseException ex){// |  ArithmeticException ex
                System.err.println("Error calculando desglose para UI en fila " + i + ": " + ex.getMessage());
                // Continuar con la siguiente fila si hay error en esta, o decidir detener.
            } catch (IndexOutOfBoundsException ex){
                Utils.mensajeError("Error: Indice fuera de rango al acceder a tableModel en fila " + i + ". Lista paralela y tabla desincronizadas?");
                ex.printStackTrace();
                break; // Detener el bucle si hay un problema grave de sincronizacion
            }
        }

        // Los totales de Gratuita y Descuento si no se calcularon por item, serian 0.00 segun DDL.
        // lblOperacionGratuita y lblDescuento labels ya existen en tu UI. Actualizalos.
        lblOperacionGratuita.setText(decimalFormat.format(totalOperacionGratuita.setScale(2, RoundingMode.HALF_UP)));
        lblDescuento.setText(decimalFormat.format(totalDescuento.setScale(2, RoundingMode.HALF_UP)));


        // Actualizar el texto de los Labels de la UI con los totales calculados, formateados a 2 decimales.
        lblOperacionGravada.setText(decimalFormat.format(totalOperacionGravada.setScale(2, RoundingMode.HALF_UP)));
        lblMontoIgv.setText(decimalFormat.format(totalMontoIgv.setScale(2, RoundingMode.HALF_UP)));
        lblOperacionInafecta.setText(decimalFormat.format(totalOperacionInafecta.setScale(2, RoundingMode.HALF_UP)));
        lblOperacionExonerada.setText(decimalFormat.format(totalOperacionExonerada.setScale(2, RoundingMode.HALF_UP)));

        // Nota: El lbl de Cambio se actualiza separadamente en el metodo btnPagarActionPerformed
        // cuando se compara totalPagadoIngresado con totalAPagarCalculado.
    }

    // Método para calcular y mostrar el cambio a dar en el label
    private void actualizarLabelCambio() {
        try {
            BigDecimal totalAPagarCambio = Utils.parseBigDecimal(decimalFormat, txtTotalPago.getText());
            BigDecimal montoEfectivo = Utils.parseBigDecimal(decimalFormat, txtPagoEfectivo.getText());
            BigDecimal montoYape = Utils.parseBigDecimal(decimalFormat, txtPagoYape.getText());
            BigDecimal montoPlin = Utils.parseBigDecimal(decimalFormat, txtPagoPlin.getText());
            BigDecimal totalPagado = montoEfectivo.add(montoYape).add(montoPlin);

            BigDecimal cambio = totalPagado.subtract(totalAPagarCambio).setScale(2, RoundingMode.HALF_UP);

            // Mostrar el cambio en el label
            lblCambio.setText(decimalFormat.format(cambio)); 

        } catch (ParseException  e) {
            // Si hay error de formato en los campos de pago, mostrar 0.00 o un indicador de error
            lblCambio.setText("ERROR"); // O "0.00"
        } catch (ArithmeticException e) {
            lblCambio.setText("ERROR"); // O "0.00"
        }
    }

    private void agregarProductoAPagar(){
        if (productoServicio != null) {// productoServicio es el objeto seleccionado temporalmente

            // Validar que precio sea > 0 antes de agregar si es requerido
            if (productoServicio.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                Utils.mensajeError("No se puede agregar un producto con precio cero o negativo.");
                productoServicio = null; // Limpiar
                limpiarCamposProductosServ();
                btnAgregarConcepto.setEnabled(false);
                return;
            }

            // *** AÑADIR EL OBJETO ProductoServicio A LA LISTA PARALELA ***
            itemsProductosList.add(productoServicio);
            
            // Calcular total del item
            BigDecimal itemPrecio = productoServicio.getPrecio();
            BigDecimal itemCantidad = BigDecimal.ONE.setScale(2,RoundingMode.HALF_UP);   
            BigDecimal itemTotal = itemPrecio.multiply(itemCantidad).setScale(2,RoundingMode.HALF_UP);

            // Datos a agregar a la tabla Swing
            Object item[] = {
                String.valueOf(productoServicio.getIdProducto()),// Col 0: ID.PROD (oculta)
                String.valueOf(productoServicio.getCategoriaProducto().getIdCategoriaProd()),// Col 1: ID.CAT.PROD (oculta)
                productoServicio.getNombreProducto(),// Col 2: NOMBRE PRODUCTO
                decimalFormat.format(itemPrecio.setScale(2, RoundingMode.HALF_UP)), // Col 3: PRECIO (formateado String)
                decimalFormat.format(itemCantidad.setScale(0, RoundingMode.HALF_UP)), // Col 4: CANT (formateado String, sin decimales si es int)
                decimalFormat.format(itemTotal.setScale(2, RoundingMode.HALF_UP)) // Col 5: TOTAL (formateado String)
            };
            tableModel.addRow(item);            

            int[] anchoColumnas = {
                15,// idProd
                15, //idCatPro
                120, //prod
                20, //precio
                20, //cantidad
                20 // total
            }; // Anchos específicos para cada columna
            Utils.setAnchoColumnas(tblItemsConceptos, anchoColumnas);
            Utils.ocultarColumnas(tblItemsConceptos, 0);// Ocultar ID.PROD
            Utils.ocultarColumnas(tblItemsConceptos, 1);// Ocultar ID.CAT.PROD
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblItemsConceptos.setDefaultRenderer(Object.class, new Utils(14));

            // Establece el modo de selección de filas para permitir solo una selección a la vez.
            tblItemsConceptos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            productoServicio = null;
            limpiarCamposProductosServ();
            btnAgregarConcepto.setEnabled(false); // Deshabilitar hasta nueva busqueda
        }        
    }

    private void actualizarColumnaTotal(int filaSeleccionada) {

        if (filaSeleccionada < 0 || filaSeleccionada >= tableModel.getRowCount()) {
            return; // Fila inválida
        }

        try {
            // Obtener precio y cantidad de las columnas editables usando parseBigDecimal()
            String precioStr = tableModel.getValueAt(filaSeleccionada, 3) != null ? tableModel.getValueAt(filaSeleccionada, 3).toString().trim() : decimalFormat.format(BigDecimal.ZERO);
            String cantidadStr = tableModel.getValueAt(filaSeleccionada, 4) != null ? tableModel.getValueAt(filaSeleccionada, 4).toString().trim() : "1";

            // *** Usar parseBigDecimal() ***
            BigDecimal precioActualizado = Utils.parseBigDecimal(decimalFormat, precioStr);
            BigDecimal cantidadActualizada = Utils.parseBigDecimal(decimalFormat, cantidadStr);

            // Validar que los valores sean positivos (precio >= 0, cantidad > 0)
            if (precioActualizado.compareTo(BigDecimal.ZERO) < 0 || cantidadActualizada.compareTo(BigDecimal.ZERO) <= 0) {
                 Utils.mensajeError("El precio debe ser >= 0 y la cantidad > 0 en la fila " + (filaSeleccionada + 1));
                 // Resetear valores invalidos a un valor por defecto (con formato local o como String si es entero)
                 if(precioActualizado.compareTo(BigDecimal.ZERO) < 0) tableModel.setValueAt(decimalFormat.format(BigDecimal.ZERO), filaSeleccionada, 3);
                 if(cantidadActualizada.compareTo(BigDecimal.ZERO) <= 0) tableModel.setValueAt("1", filaSeleccionada, 4); // Resetear cantidad a "1" String

                 // Re-parsear valores de las celdas (posiblemente reseteadas)
                 precioActualizado = Utils.parseBigDecimal(decimalFormat, tableModel.getValueAt(filaSeleccionada, 3).toString().trim());
                 cantidadActualizada = Utils.parseBigDecimal(decimalFormat, tableModel.getValueAt(filaSeleccionada, 4).toString().trim());
            }

            // Opcional: Validar que la cantidad BigDecimal pueda convertirse a int si la DB/Entidad es INT
             int cantidadInt;
             try {
                  cantidadInt = cantidadActualizada.intValueExact(); // Esto lanzara ArithmeticException si tiene decimales no cero
             } catch (ArithmeticException ex) {
                  Utils.mensajeError("La cantidad debe ser un número entero en la fila " + (filaSeleccionada + 1) + ". Se usará la parte entera.");
                  cantidadInt = cantidadActualizada.intValue(); // Truncar o redondear si lo permites
                  tableModel.setValueAt(String.valueOf(cantidadInt), filaSeleccionada, 4); // Actualizar la celda con el entero (como String)
                   cantidadActualizada = BigDecimal.valueOf(cantidadInt); // Usar el entero para el calculo
             }

            // recalcular el total (Precio * Cantidad)
            // Usar cantidadActualizada (BigDecimal) para el calculo, incluso si se guardara como int
            BigDecimal totalCalculado = precioActualizado.multiply(cantidadActualizada).setScale(2, RoundingMode.HALF_UP);
            // Actualizar el valor en la columna "TOTAL" (columna 5) con formato local
            tableModel.setValueAt(decimalFormat.format(totalCalculado.setScale(2, RoundingMode.HALF_UP)), filaSeleccionada, 5);

        } catch (ParseException e) {
            // Si falla el parseo de precio o cantidad
            System.err.println("Error de formato al editar fila " + filaSeleccionada + ": " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en Precio y Cantidad (fila " + (filaSeleccionada + 1) + ").", "Error de formato", JOptionPane.ERROR_MESSAGE);
            // Resetear los valores invalidos a 0.00 o valor por defecto con formato local
            tableModel.setValueAt(decimalFormat.format(BigDecimal.ZERO), filaSeleccionada, 3); // Precio a "0,00" o "0.00" segun el formato
            tableModel.setValueAt("1", filaSeleccionada, 4); // Cantidad a "1"
            tableModel.setValueAt(decimalFormat.format(BigDecimal.ZERO), filaSeleccionada, 5); // Total a "0,00" o "0.00"
        } catch (Exception e) { // Capturar cualquier otra excepcion inesperada
             System.err.println("Error inesperado al editar fila " + filaSeleccionada + ": " + e.getMessage());
             e.printStackTrace();
             // Resetear los valores problematicos
             tableModel.setValueAt(decimalFormat.format(BigDecimal.ZERO), filaSeleccionada, 3);
             tableModel.setValueAt("1", filaSeleccionada, 4);
             tableModel.setValueAt(decimalFormat.format(BigDecimal.ZERO), filaSeleccionada, 5);
        }
    }

    private void quitarProductoSeleccionado() {
        int nroFila = tblItemsConceptos.getSelectedRow();
        if (nroFila != -1) {
            int retorno = Utils.mensajeConfirmacion(LiteralesTexto.ESTA_SEGURO_ELIMINAR_REGISTRO);
            switch (retorno) {
                case JOptionPane.YES_OPTION: {                  

                    // *** REMOVER EL OBJETO ProductoServicio DE LA LISTA PARALELA ***
                    // La posicion en la lista es la misma que la fila en la tabla antes de removerla.
                    if (nroFila < itemsProductosList.size()) {
                        itemsProductosList.remove(nroFila);
                    } else {
                        Utils.mensajeError("Error lógico: No se encontró ProductoServicio en la lista paralela para la fila " + nroFila);
                    }

                    // Ahora remueve de la tabla Swing. Esto disparara el TableModelEvent.
                    tableModel.removeRow(nroFila);// Elimina de la tabla Swing
                    // La actualizacion del total y desgloses se maneja en el TableModelListener
                }
                break;
                case JOptionPane.NO_OPTION: {
                }
                break;
            }
        } else if (nroFila == -1) {
            Utils.mensajeError(LiteralesTexto.LITERAL_ELIMINAR_REGISTRO);
        }
    }

    private void cargarInformacionEnCombos() {
        // Cargar combo de Tipos de Comprobante
        cargarComboTipoComprobante(tiposComprobanteFacade.obtenerTiposComprobante()); // Asumo que este método existe

        // Cargar combo de Categoría Producto (sin disparar el ActionListener inicialmente)
        // 1. Guarda el ActionListener actual (si lo hay)
        ActionListener[] listeners = jcbCategoriaProducto.getActionListeners();

        // 2. Remueve todos los ActionListeners para evitar que se dispare el evento
        for (ActionListener listener : listeners) {
            jcbCategoriaProducto.removeActionListener(listener);
        }

        // 3. Carga los datos en el JComboBox
        cargarComboCategoriaProducto(categoriaProductoFacade.getListadoCategoriaProducto()); // Asumo que este método existe

        // 4. Vuelve a agregar los ActionListeners
        for (ActionListener listener : listeners) {
            jcbCategoriaProducto.addActionListener(listener);
        }

         // Seleccionar el primer item por defecto y disparar su evento manualmente
         if (jcbCategoriaProducto.getItemCount() > 0) {
             jcbCategoriaProducto.setSelectedIndex(0);
              // Disparar el evento ActionPerformed después de cargar los items
              // Esto llamará a jcbCategoriaProductoActionPerformed y a cargarPanelMatricula()
             ActionListener firstListener = jcbCategoriaProducto.getActionListeners()[0]; // Obtiene el primer listener
             if (firstListener != null) {
                  firstListener.actionPerformed(new java.awt.event.ActionEvent(jcbCategoriaProducto, java.awt.event.ActionEvent.ACTION_PERFORMED, null));
             }
         }

    }

    private void cargarComboTipoComprobante(List<TiposComprobante> lista) {
        jcbTipoCpbte.removeAllItems();

        if (lista == null || lista.isEmpty()) {
            jcbTipoCpbte.addItem("No hay tipos disponibles");
            jcbTipoCpbte.setEnabled(false);
            // Limpiar combo serie también
            jcbSerie.removeAllItems();
            jcbSerie.addItem("No hay series disponibles");
            jcbSerie.setEnabled(false);
            tipoComprobanteSeleccionado = null;
            serieSeleccionada = null;
            return;
        }
        jcbTipoCpbte.setEnabled(true);
        for (TiposComprobante tipos : lista) {
            jcbTipoCpbte.addItem(tipos);
        }
        jcbTipoCpbte.setSelectedIndex(0);

    }

    private void cargarComboSerie(List<Serie> lista) {
        jcbSerie.removeAllItems();

        if (lista == null || lista.isEmpty()) {
            jcbSerie.addItem("No hay tipos disponibles");
            jcbSerie.setEnabled(false); // Deshabilitar si no hay elementos
            serieSeleccionada = null;
        } else {
            for (Serie serie : lista) {
                jcbSerie.addItem(serie);
            }
            jcbSerie.setSelectedIndex(0);
            jcbSerie.setEnabled(true); // Habilitar el combo
        }
    }

    private void cargarComboCategoriaProducto(List<CategoriaProducto> lista) {
        jcbCategoriaProducto.removeAllItems();

        if (lista == null || lista.isEmpty()) {
            jcbCategoriaProducto.addItem("No hay CATEGORIAS");
            jcbCategoriaProducto.setEnabled(false);
            categoriaProductoSeleccionada = null;
        } else {
            for (CategoriaProducto categorias : lista) {
                jcbCategoriaProducto.addItem(categorias);
            }
            // No seleccionar el primer item aquí, se hace en cargarInformacionEnCombos()
            // jcbCategoriaProducto.setSelectedIndex(0);
            jcbCategoriaProducto.setEnabled(true);
        }
        // La lógica de ajuste de panel/botones se llama desde el ActionPerformed del combo
        // cargarPanelMatricula();
    }

    private void cargarPanelMatricula(){
        // Obtener la categoría seleccionada (ya debe estar en categoriaProductoSeleccionada)
        boolean esCategoriaMensualidad = categoriaProductoSeleccionada != null && categoriaProductoSeleccionada.getNombreCategoria().equalsIgnoreCase(LiteralesTexto.LITERAL_MENSUALIDAD);

        if(esCategoriaMensualidad){
            jpMatricula.setVisible(true);
            // Limpiar campos de matricula si no hay una seleccionada o la seleccionada no coincide con el alumno actual
             if (matriculaActual == null || (alumnoActual != null && !matriculaActual.getAlumno().equals(alumnoActual))) {
                  limpiarCamposProductosServ1(); // Limpia campos de matricula y setea matriculaActual = null
             }

        } else {
            jpMatricula.setVisible(false);
            limpiarCamposProductosServ1(); // Limpiar campos de matricula y setea matriculaActual = null si no es mensualidad
        }

        // Limpiar campos de concepto y producto seleccionado al cambiar de categoria
        limpiarCamposProductosServ();
        productoServicio = null;

        // Re-evaluar la habilitación de botones
        activarBotonProcesoPago();
    }

    public int obtenerIDCategoriaSeleccionada() {
        if (categoriaProductoSeleccionada != null) {
            return categoriaProductoSeleccionada.getIdCategoriaProd();
        }
        return -1; // En caso de que no haya una selección válida o sea el placeholder
    }

    // Nuevo método para limpiar todos los campos de persona y objetos asociados
    private void limpiarCamposBusquedaPersonaCompleta() {
        txtApellidosAlumno.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresAlumno.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDniAlumno.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtNombresApellidosPadre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDniPadre.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        txtDatoBusqueda.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
        lblFotoAlumno.setIcon(null); // Limpiar foto
        this.alumnoActual = null;
        this.padreActual = null;
        setMatriculaActual(null); // Esto también limpia campos de matricula y ajusta visibilidad/habilitacion

    }

    // Este método DEBE ser llamado desde jifMatricula cuando el usuario selecciona una Matricula.
    public void setMatriculaActual(Matricula matricula) {
         this.matriculaActual = matricula; // Almacena el objeto Matricula completo

          if (matricula != null) {
              // Cargar los datos de la Matrícula en los campos de la UI
              txtIdMatricula.setText(String.valueOf(matricula.getIdMatricula()));
              // Asumo que la entidad Matricula tiene los getters necesarios
              /*String detalles = "Periodo: " + (matricula.getPeriodo() != null ? matricula.getPeriodo() : "") +
                               " - Grado: " + (matricula.getGrado() != null ? matricula.getGrado() : "") +
                               " - Monto Cuota: " + (matricula.getMontoCuota() != null ? decimalFormat.format(matricula.getMontoCuota()) : "0.00");
              txtDetallesMatricula.setText(detalles);*/

              // Si al seleccionar la matricula no estaba cargado el alumno/padre, cargarlos
              if (this.alumnoActual == null && matricula.getAlumno() != null) {
                   mostrarDatosAlumno(matricula.getAlumno(), matricula.getAlumno().getPadre()); // Esto guardará alumnoActual y padreActual
              }
              // Si el alumno actual no coincide con el de la matricula, limpiar el alumno actual
              else if (this.alumnoActual != null && (matricula.getAlumno() == null || !matricula.getAlumno().equals(this.alumnoActual))) {
                  Utils.mensajeInformacion("La matrícula seleccionada no corresponde al alumno actual. Limpiando datos del alumno.");
                  limpiarCamposBusquedaPersonaCompleta(); // Limpia todo
              }


          } else {
               // Si se pasa null, limpiar los campos de Matricula y la variable de instancia
               txtIdMatricula.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
               txtDetallesMatricula.setText(LiteralesTexto.LITERAL_CADENA_VACIA);
               this.matriculaActual = null;
          }

         // Después de seleccionar/deseleccionar una matrícula, re-evaluar la habilitación de botones
         cargarPanelMatricula(); // Esto ajusta la visibilidad del panel y llama a activarBotonProcesoPago()
    }

    // Método para limpiar los campos relevantes después de un pago exitoso
    private void limpiarFormularioPago() {        

        // *** LIMPIAR LA LISTA PARALELA ***
        itemsProductosList.clear();// Elimina todos los objetos de la lista de Productos

        // Limpiar tabla de ítems
        tableModel.setRowCount(0);// Elimina todas las filas de la tabla Swing

        // Limpiar campos de persona y objetos asociados
        limpiarCamposBusquedaPersonaCompleta(); // Ahora usa el método completo

        actualizarTotalAPagar(); // Esto pondrá el total y los desgloses en 0.00

        // Limpiar campos de pago y objetos asociados
        jdchFechaPago.setDate(new Date()); // Resetear a la fecha actual
        if (jcbTipoCpbte.getItemCount() > 0) {
             jcbTipoCpbte.setSelectedIndex(0); // Seleccionar el primer ítem, esto disparará la carga de series
        } else {
            cargarComboTipoComprobante(tiposComprobanteFacade.obtenerTiposComprobante()); // Recargar si no hay items
        }
        // jcbSerie se actualiza por el action listener de jcbTipoCpbte
        reiniciaPrecioFormaPago();
        txtTotalPago.setText("0.00"); // Ya se hizo con actualizarTotalAPagar()

        // Limpiar campos de concepto y producto seleccionado
        limpiarCamposProductosServ();
        productoServicio = null;

        // Limpiar campos de matricula y objeto seleccionado (ya incluido en limpiarCamposBusquedaPersonaCompleta)
        // limpiarCamposProductosServ1(); // Redundante si usas limpiarCamposBusquedaPersonaCompleta

        // Ajustar visibilidad y habilitación de botones basado en el estado inicial
        // Esto se hace al limpiar campos de persona y matricula, y en cargarPanelMatricula() que se llama desde setMatriculaActual(null)
         cargarPanelMatricula();


    }

    // Para que el label de cambio (jLabel6) se actualice automáticamente al ingresar montos.
    // Esto se hace mejor en el constructor o un metodo de inicializacion.
    private void initPaymentFieldsListeners() {
        DocumentListener paymentListener = new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                actualizarLabelCambio();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarLabelCambio();
            }

            public void insertUpdate(DocumentEvent e) {
                actualizarLabelCambio();
            }
        };
        txtPagoEfectivo.getDocument().addDocumentListener(paymentListener);
        txtPagoYape.getDocument().addDocumentListener(paymentListener);
        txtPagoPlin.getDocument().addDocumentListener(paymentListener);
    }

    private void reiniciaPrecioFormaPago(){
        txtPagoEfectivo.setText(decimalFormat.format(BigDecimal.ZERO));
        txtPagoYape.setText(decimalFormat.format(BigDecimal.ZERO));
        txtPagoPlin.setText(decimalFormat.format(BigDecimal.ZERO));
    }

    private void reiniciarPreciosUI(){
        lblOperacionGravada.setText(decimalFormat.format(BigDecimal.ZERO));
        lblMontoIgv.setText(decimalFormat.format(BigDecimal.ZERO));
        lblOperacionInafecta.setText(decimalFormat.format(BigDecimal.ZERO));
        lblOperacionExonerada.setText(decimalFormat.format(BigDecimal.ZERO));
        lblDescuento.setText(decimalFormat.format(BigDecimal.ZERO));
        lblOperacionGratuita.setText(decimalFormat.format(BigDecimal.ZERO));
        lblCambio.setText(decimalFormat.format(BigDecimal.ZERO));
    }
}
