/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.conexion.MiConexion;
import academiafulbito.modelo.entidades.Usuario;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.Rol;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ronald J
 */
public class UsuarioFacade {

    
    // *** Gestionar el EntityManagerFactory a nivel de clase (singleton) ***
    // Es mas eficiente crear el EMF una sola vez y reusarlo.
    private static EntityManagerFactory emf;

    // Bloque estatico para inicializar el EMF la primera vez que se carga la clase
    static {
        try {
             // Usamos tu configuracion de PU. Asegurate que MiConexion.cargarPropiedadesPU()
             // retorne un Map<String, String> con las propiedades de conexion.
            emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU", MiConexion.cargarPropiedadesPU());

        } catch (Exception e) {
            // Manejar error si el EMF no se puede crear (ej: persistence.xml mal configurado, propiedades incorrectas)
            System.err.println("Error fatal al inicializar EntityManagerFactory para UsuarioFacade.");
            e.printStackTrace();
            // Lanzar ErrorInInitializerError para indicar un fallo grave durante la inicializacion de la clase
            throw new ExceptionInInitializerError("Error al inicializar EntityManagerFactory: " + e.getMessage());
        }
    }

    // Constructor - No necesitas crear EMF aqui, ya es estatico.
    public UsuarioFacade() {
    }

    // Metodo para obtener un EntityManager (debe ser nuevo para cada "unidad de trabajo")
    private EntityManager getEntityManager() {
        return emf.createEntityManager(); // Crea un nuevo EM usando el EMF estatico
    }

    /**
     * Intenta autenticar un usuario por su clave y rol para el nombre de usuario "root".
     * Si la autenticacion es exitosa, retorna el objeto Usuario.
     * Si falla, retorna null.
     *
     * @param clave La clave ingresada por el usuario.
     * @param rol El Rol seleccionado por el usuario.
     * @return El objeto Usuario autenticado si las credenciales son correctas, de lo contrario null.
     * @throws Exception Si ocurre un error inesperado (ej: error de BD distinto a NoResultException).
     */
    // *** Metodo modificado: Retorna Usuario, toma clave y rol, asume nombre "root", lanza Exception ***
    public Usuario autenticarUsuario(String clave, Rol rol) throws Exception {
        EntityManager em = getEntityManager(); // Obtiene un nuevo EM
        Usuario usuarioAutenticado = null;

        try {
            // Nombre de usuario fijo "root" basado en tu codigo original de validacion
            // Si tienes otros usuarios, DEBES CAMBIAR LA UI y este metodo para recibir el nombre
            String nombreUsuarioFijo = "root";

            // Crear la consulta JPQL para buscar el usuario por nombre, clave, rol y estado ACTIVO
            String jpql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.clave = :clave AND u.rol = :rol AND u.estado = :estadoActivo";

            // Crear la consulta tipada
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuarioFijo); // Usamos el nombre fijo "root"
            query.setParameter("clave", clave);
            query.setParameter("rol", rol);
            // *** Añadir filtro por estado ACTIVO ***
            query.setParameter("estadoActivo", Estado.ACTIVO); // Usar el Enum Estado.ACTIVO

            // Ejecutar la consulta y obtener el UNICO resultado esperado
            // Si no hay resultado (ningun usuario coincide), getSingleResult lanza NoResultException
            usuarioAutenticado = query.getSingleResult();

            // Si llegamos aqui, significa que getSingleResult encontro exactamente un usuario que coincide.
            // El objeto 'usuarioAutenticado' ahora contiene la entidad Usuario encontrada.

        } catch (NoResultException e) {
            // Si no se encuentra ningun usuario con esas credenciales (nombre="root", clave, rol, estado="ACTIVO").
            // Esto significa que las credenciales son incorrectas o el usuario no esta activo.
            System.out.println("Autenticacion fallida: Usuario 'root' con clave/rol/estado no encontrado."); // O loggear
            usuarioAutenticado = null; // Retornar null para indicar que la autenticacion fallo.
        } catch (Exception e) {
            // Capturar otras posibles excepciones (ej: error de BD al ejecutar la consulta, MoreThanOneResultException - poco probable con clave/rol, error en el Enum Estado)
            System.err.println("Error inesperado durante la autenticacion en UsuarioFacade: " + e.getMessage());
            e.printStackTrace(); // Loggear el error real

            // *** Relanzar la excepcion para que la UI (JFLogin) la maneje ***
            throw new Exception("Error de sistema durante la autenticación. Intente nuevamente.", e); // Encapsular y lanzar
        } finally {
            // Es importante cerrar el EntityManager siempre
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return usuarioAutenticado; // Retorna el objeto Usuario autenticado o null si fallo
    }
    
}
