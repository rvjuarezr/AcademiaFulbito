/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Matricula;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class MatriculaFacade implements EntityFacade<Matricula>{

    private EntityManagerFactory emf;

    public MatriculaFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Matricula> getListadoMatriculas() {
        EntityManager em = getEntityManager();
        List<Matricula> matriculas = null;
        try {
            matriculas = em.createQuery("SELECT m FROM Matricula m", Matricula.class).getResultList();
        } finally {
            em.close();
        }
        return matriculas;
    }

    public void guardarMatricula(Matricula matricula) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(matricula);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Matricula findMatriculaById(int idMatricula) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matricula.class, idMatricula);
        } finally {
            em.close();
        }
    }

    public void actualizarMatricula(Matricula matricula) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(matricula);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarMatricula(Matricula matricula) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            matricula = em.merge(matricula); // Asegurar que la entidad esté gestionada
            em.remove(matricula);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalMatriculas = em.createQuery("SELECT COUNT(m) FROM Matricula m", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalMatriculas / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Matricula> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Matricula m", Matricula.class)
                    .setFirstResult((paginaActual - 1) * tamanioPagina)
                    .setMaxResults(tamanioPagina)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
