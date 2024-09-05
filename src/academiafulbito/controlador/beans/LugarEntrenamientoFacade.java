/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import academiafulbito.modelo.entidades.LugarEntrenamiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class LugarEntrenamientoFacade {

    EntityManagerFactory emf;
    

    public LugarEntrenamientoFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar lugarEntrenamientos
    public List<LugarEntrenamiento> getListadoLugarEntrenamientos() {
        EntityManager em = getEntityManager();
        List<LugarEntrenamiento> lugarEntrenamientos = null;
        try {
            lugarEntrenamientos = em.createQuery("SELECT c FROM LugarEntrenamiento c", LugarEntrenamiento.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return lugarEntrenamientos;
    }

    // Método para guardar una categoría
    public void guardarLugarEntrenamiento(LugarEntrenamiento lugarEntrenamiento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lugarEntrenamiento); // Guardar la entidad
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();// Siempre cerrar el EntityManager al final
        }
    }

    public LugarEntrenamiento findLugarEntrenamientoById(int idLugarEntrenamiento) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LugarEntrenamiento.class, idLugarEntrenamiento);
        } finally {
            em.close();
        }
    }

    public void actualizarLugarEntrenamiento(LugarEntrenamiento lugarEntrenamiento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(lugarEntrenamiento);

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<LugarEntrenamiento> listarLugarEntrenamientosPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM LugarEntrenamiento c", LugarEntrenamiento.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalLugarEntrenamientos = em.createQuery("SELECT COUNT(c) FROM LugarEntrenamiento c", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalLugarEntrenamientos / tamanioPagina);
        } finally {
            em.close();
        }
    }


}
