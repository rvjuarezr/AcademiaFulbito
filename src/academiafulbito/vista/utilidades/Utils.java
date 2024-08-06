/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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

    public Utils(){
        df=new DecimalFormat("##0.00");
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
}
