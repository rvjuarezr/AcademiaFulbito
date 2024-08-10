/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Usuario;
import academiafulbito.modelo.enums.Rol;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ronald J
 */
public class UsuarioFacade {

    EntityManager em = Persistence.createEntityManagerFactory("AcademiaFulbitoPU").createEntityManager();

    // Método para validar si el usuario existe en la base de datos
    public boolean validarUsuario(String nombreUsuario, String clave, Rol rol) {
        try {
            // Crear la consulta JPQL
            String jpql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.clave = :clave AND u.rol = :rol";

            // Crear la consulta tipada
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("clave", clave);
            query.setParameter("rol", rol);

            // Ejecutar la consulta y obtener resultados
            Usuario usuario = query.getSingleResult();

            // Si la consulta devuelve un resultado, el usuario existe
            return usuario != null;

        } catch (Exception e) {
            // Si ocurre una excepción (por ejemplo, NoResultException), significa que el usuario no existe
            return false;
        }
    }
}
