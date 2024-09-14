/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import academiafulbito.vista.interfaces.jfPrincipal;
import academiafulbito.vista.interfaces.jifCampeonatos;
import academiafulbito.vista.interfaces.jifCanchas;
import academiafulbito.vista.interfaces.jifCategorias;
import academiafulbito.vista.interfaces.jifHorario;
import academiafulbito.vista.interfaces.jifPadres;
import academiafulbito.vista.interfaces.jifProfesores;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Ronald J
 */
public class VentanaManager {

    public static void openVentana(String opcion, JDesktopPane jdpVentanas, Utils utils) {
        JInternalFrame ventana = null;

        if (opcion.equals(LiteralesTexto.LITERAL_MAESTRAS_CATEGORIAS)) {
            if (jfPrincipal.menuCategorias == null || jfPrincipal.menuCategorias.isClosed()) {
                jfPrincipal.menuCategorias = new jifCategorias(jdpVentanas);
            }
            ventana = jfPrincipal.menuCategorias;

        } else if(opcion.equals(LiteralesTexto.LITERAL_MAESTRAS_CANCHAS)){
            if (jfPrincipal.menuCanchas == null || jfPrincipal.menuCanchas.isClosed()) {
                jfPrincipal.menuCanchas = new jifCanchas(jdpVentanas);
            }
            ventana = jfPrincipal.menuCanchas;

        } else if(opcion.equals(LiteralesTexto.LITERAL_MAESTRAS_CAMPEONATO)){
            if (jfPrincipal.menuCampeonatos == null || jfPrincipal.menuCampeonatos.isClosed()) {
                jfPrincipal.menuCampeonatos = new jifCampeonatos(jdpVentanas);
            }
            ventana = jfPrincipal.menuCampeonatos;

        } else if(opcion.equals(LiteralesTexto.LITERAL_MAESTRAS_PADRES)){
            if (jfPrincipal.menuPadres == null || jfPrincipal.menuPadres.isClosed()) {
                jfPrincipal.menuPadres = new jifPadres(jdpVentanas);
            }
            ventana = jfPrincipal.menuPadres;

        } else if(opcion.equals(LiteralesTexto.LITERAL_MAESTRAS_PROFESORES)){
            if (jfPrincipal.menuProfesores == null || jfPrincipal.menuProfesores.isClosed()) {
                jfPrincipal.menuProfesores = new jifProfesores(jdpVentanas);
            }
            ventana = jfPrincipal.menuProfesores;

        } else if(opcion.equals(LiteralesTexto.LITERAL_CONTROL_HORARIOS)){
            if (jfPrincipal.menuHorario == null || jfPrincipal.menuHorario.isClosed()) {
                jfPrincipal.menuHorario = new jifHorario(jdpVentanas);
            }
            ventana = jfPrincipal.menuHorario;

        }

        // Verificación si ventana sigue siendo null
        if (ventana == null) {
            // Manejar el caso en el que ninguna opción coincide
            Utils.mensajeError("Opción no válida o ventana no encontrada");
            return;
        }

        // Verificar si la ventana ya está en el JDesktopPane antes de agregarla
        if (!ventana.isVisible()) {
            jdpVentanas.add(ventana);
            ventana.pack(); // Asegurarse de que se ajuste a su contenido
            ventana.setLocation(Utils.posicionX(ventana, jdpVentanas), Utils.posicionY(ventana, jdpVentanas));
        }
        
        ventana.show();
        ventana.toFront();
    }
}
