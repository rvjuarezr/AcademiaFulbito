/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito;

import academiafulbito.modelo.conexion.MiConexion;
import academiafulbito.vista.interfaces.jifCategorias;
import academiafulbito.vista.logueo.JFLogin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SISTEMAS3
 */
public class Main {
public static MiConexion miConexion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MiConexion.cargarPropiedades(); // Cargar las propiedades antes de intentar la conexión
        miConexion=new MiConexion();
        miConexion.obtenerConexion();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFLogin().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
