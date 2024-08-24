/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Profesor;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class ProfesorFacade implements EntityFacade<Profesor> {

    EntityManagerFactory emf;

    public ProfesorFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las profesores
    public List<Profesor> getListadoProfesores() {
        EntityManager em = getEntityManager();
        List<Profesor> profesores = null;
        try {
            profesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return profesores;
    }

    // Método para guardar un profesor
    public void guardarProfesor(Profesor profesor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(profesor); // Guardar la entidad
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

    public Profesor findProfesorById(int idProfesor) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profesor.class, idProfesor);
        } finally {
            em.close();
        }
    }

    public void actualizarProfesor(Profesor profesor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(profesor);

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
            long totalProfesores = em.createQuery("SELECT COUNT(p) FROM Profesor p", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalProfesores / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Profesor> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Profesor p", Profesor.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }
}
