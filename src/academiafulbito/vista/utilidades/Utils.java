/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
        JOptionPane.showMessageDialog(null, mensaje, "Capacitacion SIGA", 1);
    }

    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Capacitacion SIGA", 0);
    }

    public static int mensajeConfirmacion(String mensaje){
        return JOptionPane.showConfirmDialog(null, mensaje, "Capacitacion SIGA", 0);
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
        Color color_de_fila = new Color(178,206, 252);
        setBackground(Color.white);//color de fondo
        table.setFont(normal);//tipo de fuente
        table.setForeground(Color.black);//color de texto
        table.setRowHeight(40);
        setHorizontalAlignment(SwingConstants.RIGHT);//derecha

        if (fila_con_color) {
            setBackground(color_de_fila);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}
