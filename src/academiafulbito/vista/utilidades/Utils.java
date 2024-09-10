/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import academiafulbito.modelo.enums.Dia;
import academiafulbito.modelo.enums.Estado;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ronald J
 */
public class Utils extends DefaultTableCellRenderer{

    DecimalFormat df;

    int tamanioLetra=12;//valor por defecto
    public Utils(int tamLetra){
        df=new DecimalFormat("##0.00");
        tamanioLetra = tamLetra;
    }

    public static void limpiarModeloTabla(DefaultTableModel modelo, JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount()>0){
           modelo.removeRow(0);
        }
    }

    public static void limpiaTabla(JTable tabla){
       try{
           DefaultTableModel temp = (DefaultTableModel)tabla.getModel();
           int a =temp.getRowCount()-1;
           for(int i=a; i>=0; i--)
               temp.removeRow(i);
       }catch(Exception e){
           e.printStackTrace();
       }
   }

    public static void ocultarColumnas(JTable tbl,int indiceColumna){
        tbl.getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tbl.getColumnModel().getColumn(indiceColumna).setMinWidth(0);
        tbl.getTableHeader().getColumnModel().getColumn(indiceColumna).setMaxWidth(0);
        tbl.getTableHeader().getColumnModel().getColumn(indiceColumna).setMinWidth(0);
    }

    public static void setAnchoColumnas(JTable tabla, int[] anchos) {
        if (anchos.length != tabla.getColumnCount()) {
            throw new IllegalArgumentException("El número de anchos debe coincidir con el número de columnas en la tabla.");
        }

        TableColumnModel modeloColumna = tabla.getColumnModel();
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            TableColumn columnaTabla = modeloColumna.getColumn(i);
            columnaTabla.setPreferredWidth(anchos[i]*3);
        }
    }

    public static boolean validarCadena(String valorCadena) {
        return !valorCadena.isEmpty()
                && valorCadena.matches("[a-zA-Z0-9 ]*")
                && !valorCadena.equalsIgnoreCase("null");
    }

    public static String getFechaFormateada(Date fe){
        String ff;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
         if(fe!=null){
             ff=formatoFecha.format(fe);
         }else{
             ff=null;
         }
        return ff;
    }

    public static void mensajeInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Academia Fulbito", 1);
    }

    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Academia Fulbito", 0);
    }

    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, "Academia Fulbito", 0);
    }

    public static int posicionX(JInternalFrame jif, JDesktopPane dpMostrar){
        return (dpMostrar.getWidth()/2)-(jif.getWidth()/2);
    }

    public static int posicionY(JInternalFrame jif, JDesktopPane dpMostrar){
        return (dpMostrar.getHeight()/2)-(jif.getHeight()/2);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        //variables para el metodo formato tabla
        Font normal = new Font("Bookman Old Style", Font.BOLD + Font.ITALIC, tamanioLetra);
        Font negrilla = new Font("Verdana", Font.BOLD, tamanioLetra);
        Font cursiva = new Font("Times new roman", Font.ITALIC, tamanioLetra);
        boolean fila_con_color = (row % 2 == 0);

        // Personalizar la cabecera
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)); // Borde completo para la cabecera
            header.setBackground(new Color(240, 240, 240));
            header.setPreferredSize(new Dimension(100, 50)); // Ajusta la altura de la cabecera
            header.setFont(negrilla); // Tu fuente personalizada
        }

        Color color_de_fila = new Color(178,206, 252);
        setBackground(Color.white);//color de fondo
        table.setFont(normal);//tipo de fuente
        table.setForeground(Color.black);//color de texto
        table.setRowHeight(40);

        if (fila_con_color) {
            setBackground(color_de_fila);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        // Configura el borde inferior para cada fila
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Solo borde inferior
        setHorizontalAlignment(SwingConstants.CENTER); // Centra el contenido en la celda

        return this;
    }

    /**
     * Genera un DefaultTableModel personalizado para una tabla.
     *
     * @param nombreColumnas Arreglo con los nombres de las columnas.
     * @return Un DefaultTableModel configurado para permitir la edición en las últimas tres columnas.
     */
    public static DefaultTableModel generarModeloTabla(String[] nombreColumnas) {
        return new DefaultTableModel(nombreColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir la edición solo para las últimas tres columnas:
                // column 4: Ver, column 5: Editar, column 6: Eliminar
                int totalColumnas = getColumnCount();
                return column >= totalColumnas - 3 && column < totalColumnas;
            }
        };
    }

    // Método para configurar la transparencia y estilo de la tabla
    public static void configurarEstiloTabla(JTable tabla, JScrollPane contenedor) {
        tabla.setOpaque(false);// Hace la tabla transparente
        ((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setOpaque(false);// Hace las celdas transparentes
        tabla.setShowGrid(false);// Desactiva el grid para ocultar los bordes predeterminados
        tabla.setIntercellSpacing(new Dimension(0, 0));// Elimina el espacio entre celdas
        contenedor.getViewport().setOpaque(false);// Hace el viewport transparente
        contenedor.setPreferredSize(new Dimension(600, 400)); // Cambia las dimensiones a tu gusto.
        contenedor.setMinimumSize(new Dimension(600, 400));   // Garantiza un tamaño mínimo.
        contenedor.setMaximumSize(new Dimension(600, 400));   // Limita el tamaño máximo.
        contenedor.getParent().repaint();    // Redibuja la interfaz.

    }

    // Método para agregar los botones a las columnas
    public static void configurarBotonesAccion(JTable tabla) {
        // Agregar boton ver
        tabla.getColumn(LiteralesTexto.LITERAL_VER).setCellRenderer(new ButtonRenderer(LiteralesTexto.LITERAL_VER));
        tabla.getColumn(LiteralesTexto.LITERAL_VER).setCellEditor(new ButtonEditor(LiteralesTexto.LITERAL_VER));


        // Agregar boton Editar
        tabla.getColumn(LiteralesTexto.LITERAL_EDITAR).setCellRenderer(new ButtonRenderer(LiteralesTexto.LITERAL_EDITAR));
        tabla.getColumn(LiteralesTexto.LITERAL_EDITAR).setCellEditor(new ButtonEditor(LiteralesTexto.LITERAL_EDITAR));


        // Agregar boton Eliminar
        tabla.getColumn(LiteralesTexto.LITERAL_ELIMINAR).setCellRenderer(new ButtonRenderer(LiteralesTexto.LITERAL_ELIMINAR));
        tabla.getColumn(LiteralesTexto.LITERAL_ELIMINAR).setCellEditor(new ButtonEditor(LiteralesTexto.LITERAL_ELIMINAR));
    }

    public static void cargarComboEstado(JComboBox cmbEstado) {
        for (Estado estado : Estado.values()) {
            cmbEstado.addItem(estado);
        }
    }

    public static void cargarComboDiasDeLaSemana(JComboBox cmbDiasDeLaSemana) {
        cmbDiasDeLaSemana.addItem("SELECCIONA EL DIA DE LA SEMANA");
        for (Dia dia : Dia.values()) {
            cmbDiasDeLaSemana.addItem(dia.toString().toUpperCase());
        }
    }

    /**
     * Método para crear un JLabel con estilo HTML personalizado.
     *
     * @param labelBold Texto en negrita (etiqueta).
     * @param labelNormal Texto normal (valor).
     * @return Un JLabel con el texto y estilo especificado.
     */
    public static JLabel createStyledLabel(String labelBold, String labelNormal, int tamLetra, int longitudFijaCadena) {

        // Rellenar el primer parámetro con espacios si es necesario
        while (labelBold.length() < longitudFijaCadena) {
            labelBold += ".";
        }

        String htmlText = "<html>  \n\t<style type=\"text/css\">  " +
                "\n\t\t.estilo1{font-family:Bookman Old Style;font-weight:bold; font-size:"+tamLetra+"px;color:rgb(51,51,51);}  " +
                "\n\t\t.estilo2{font-family:Bookman Old Style; font-size:"+tamLetra+"px;color:rgb(103,98,98);}  " +
                "\n\t</style>  \n\t<span class=\"estilo1\">" + labelBold + "</span>" +
                "\n\t<span class=\"estilo2\">" + labelNormal + "</span> \n</html>";

        return new JLabel(htmlText);
    }

    public static void aplicarEstiloButtonJOptionPane(String nombreBtn, int tamLetra, int ancho, int altura){
        // Configurar el tamaño y estilo del botón "Aceptar"
        UIManager.put("OptionPane.okButtonText", nombreBtn);
        UIManager.put("Button.font", new Font("Bookman Old Style", Font.BOLD, tamLetra)); // Cambiar la fuente y tamaño
        UIManager.put("Button.minimumSize", new Dimension(100, 40)); // Cambiar el tamaño mínimo
        UIManager.put("Button.preferredSize", new Dimension(ancho, altura)); // Cambiar el tamaño preferido
    }

    public static void visualizarInternalFrame(JInternalFrame internalFrame, JDesktopPane desktopPane) {
        desktopPane.add(internalFrame);
        internalFrame.setLocation(Utils.posicionX(internalFrame, desktopPane), Utils.posicionY(internalFrame, desktopPane));
        internalFrame.show();
    }
}
