/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.reportes;

import academiafulbito.Main;
import academiafulbito.modelo.conexion.MiConexion;
import academiafulbito.vista.utilidades.Utils;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ronald J
 */
public class Reportes {

    Connection conexion;

    public Reportes() {
    }

    // Método para cargar el reporte y generar JasperPrint
    public static JasperPrint cargarReporte(String rutaReporte, String nombreJasper, Map<String, Object> parametros, Connection conexion) throws JRException {
        System.out.println("rutaReporte:"+rutaReporte);
        // Cargar el archivo .jasper desde la ruta especificada
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte+nombreJasper);

        // Agregar el directorio de subreportes a los parámetros si es necesario
        parametros.put("SUBREPORT_DIR", rutaReporte);

        // Generar y llenar el reporte con datos y parámetros
        return JasperFillManager.fillReport(jasperReport, parametros, conexion);
    }

    // Método para mostrar el reporte en una ventana gráfica
    public static void mostrarReporte(JasperPrint reporteLleno, String titulo) {
        JasperViewer viewer = new JasperViewer(reporteLleno, false);
        viewer.setTitle(titulo);
        viewer.setLocationRelativeTo(null);
        viewer.setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        viewer.setVisible(true);
    }

    // Método principal que integra la lógica de conexión, carga y visualización del reporte
    public static void imprimirReporte(Map<String, Object> parametros, String jasper) {
        try {
            JasperPrint reporteLleno = cargarReporte(MiConexion.rutaReportes, jasper, parametros, Main.miConexion);
            mostrarReporte(reporteLleno, "Reporte Academia Fulbito");
        } catch (JRException e) {
            Utils.mensajeError("NO SE HA CARGADO SU REPORTE");
            e.printStackTrace(); // Aquí podrías mejorar el manejo de excepciones para mostrar un mensaje más informativo
        }
    }
}
