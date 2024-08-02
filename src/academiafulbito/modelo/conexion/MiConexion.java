/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.conexion;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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

    public static void cargarPropiedades() {
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            File configFile = new File("config.properties");
            if (!configFile.exists()) {
                throw new IOException("El archivo config.properties no existe");
            }

            fis = new FileInputStream(configFile);
            prop.load(fis);
            nombreDeUsuario = prop.getProperty("db.user");
            claveDeUsuario = prop.getProperty("db.password");

            if (nombreDeUsuario == null || claveDeUsuario == null) {
                throw new IOException("Propiedades de usuario o clave no están definidas en config.properties");
            }
        } catch (IOException ex) {
            Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, "Error al leer config.properties", ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, "Error al cerrar FileInputStream", ex);
                }
            }
        }
    }

    public static Connection obtenerConexion(){
        Connection conexion = null;
        String urlConexion="jdbc:mysql://localhost:3306/bdacademiafulbito";     // url que se va a utilizar para conectar
        try {
            Class.forName(dbDriver); // establece el driver de conexion
            conexion=(Connection) DriverManager.getConnection(urlConexion,nombreDeUsuario,claveDeUsuario);
            System.out.println("Conexion exitosa!!");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, "Driver no encontrado", ex);
            conexion=null;
        } catch(SQLException ex){
            if (ex.getSQLState().equals("42000")) {
                System.out.println("ERROR: La base de datos no está creada: " + ex.getMessage());
            } else if (ex.getSQLState().equals("08S01") || ex.getSQLState().equals("08001")) {
                System.out.println("ERROR: El servicio de MySQL no está iniciado: " + ex.getMessage());
            } else if (ex.getSQLState().equals("28000")) {
                System.out.println("ERROR: Acceso denegado para el usuario: " + ex.getMessage());
            } else {
                Logger.getLogger(MiConexion.class.getName()).log(Level.SEVERE, "Error de SQL", ex);
            }
            conexion=null;
        } finally {
            if (conexion == null) {
                System.out.println("No hay conexión");
            }
        }

        return conexion;
    }
}
