/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito;

import academiafulbito.modelo.conexion.MiConexion;

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
    }

}
