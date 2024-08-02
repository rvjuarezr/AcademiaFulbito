/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author SISTEMAS3
 */
public class MiConexion {
private static String dbDriver="com.mysql.jdbc.Driver";     //Controlador jdbc para bd relacional
private static String nombreDeUsuario;
private static String claveDeUsuario;

    public MiConexion() {
        nombreDeUsuario="root";
        claveDeUsuario="123456";
    }

    public static Connection obtenerConexion(){
        Connection conexion;
        String urlConexion="jdbc:mysql://localhost:3306/bdacademiafulbito";     // url que se va a utilizar para conectar
        try {
            Class.forName(dbDriver); // establece el driver de conexion
            conexion=(Connection) DriverManager.getConnection(urlConexion,nombreDeUsuario,claveDeUsuario);
            System.out.println("Conexion exitosa");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, null, ex);
            conexion=null;
            System.out.println("No hay Conexion");
        } catch(SQLException ex){
            Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, null, ex);
            conexion=null;
            System.out.println("No hay Conexion");
        }

        return conexion;
    }
}
