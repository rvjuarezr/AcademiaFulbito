/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Horario;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class HorarioFacade implements EntityFacade<Horario>{

    private EntityManagerFactory emf;

    public HorarioFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Horario> getListadoHorarios() {
        EntityManager em = getEntityManager();
        List<Horario> horarios = null;
        try {
            horarios = em.createQuery("SELECT h FROM Horario h", Horario.class).getResultList();
        } finally {
            em.close();
        }
        return horarios;
    }

    public void guardarHorario(Horario horario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(horario);
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

    public Horario findHorarioById(int idHorario) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, idHorario);
        } finally {
            em.close();
        }
    }

    public void actualizarHorario(Horario horario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(horario);
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

    public void eliminarHorario(Horario horario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            horario = em.merge(horario); // Asegurar que la entidad esté gestionada
            em.remove(horario);
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
            long totalHorarios = em.createQuery("SELECT COUNT(h) FROM Horario h", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalHorarios / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Horario> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT h FROM Horario h", Horario.class)
                    .setFirstResult((paginaActual - 1) * tamanioPagina)
                    .setMaxResults(tamanioPagina)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
