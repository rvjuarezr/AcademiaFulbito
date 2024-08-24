/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Padre;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class PadreFacade implements EntityFacade<Padre> {

    EntityManagerFactory emf;
    

    public PadreFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las padres
    public List<Padre> getListadoPadres() {
        EntityManager em = getEntityManager();
        List<Padre> padres = null;
        try {
            padres = em.createQuery("SELECT p FROM Padre p", Padre.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return padres;
    }

    // Método para guardar un padre
    public void guardarPadre(Padre padre) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(padre); // Guardar la entidad
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

    public Padre findPadreById(int idPadre) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Padre.class, idPadre);
        } finally {
            em.close();
        }
    }

    public void actualizarPadre(Padre padre) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(padre);

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

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalPadres = em.createQuery("SELECT COUNT(p) FROM Padre p", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalPadres / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Padre> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Padre p", Padre.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }
}
