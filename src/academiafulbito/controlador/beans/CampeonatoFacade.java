/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Campeonato;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class CampeonatoFacade implements EntityFacade<Campeonato>{

    EntityManagerFactory emf;
    

    public CampeonatoFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las categorías
    public List<Campeonato> getListadoCampeonatos() {
        EntityManager em = getEntityManager();
        List<Campeonato> campeonatos = null;
        try {
            campeonatos = em.createQuery("SELECT c FROM Campeonato c", Campeonato.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return campeonatos;
    }

    // Método para guardar una categoría
    public void guardarCampeonato(Campeonato campeonato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(campeonato); // Guardar la entidad
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

    public Campeonato findCampeonatoById(int idCampeonato) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Campeonato.class, idCampeonato);
        } finally {
            em.close();
        }
    }

    public void actualizarCampeonato(Campeonato campeonato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(campeonato);

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

    public List<Campeonato> listarCampeonatosPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Campeonato c", Campeonato.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalCampeonatos = em.createQuery("SELECT COUNT(c) FROM Campeonato c", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalCampeonatos / tamanioPagina);
        } finally {
            em.close();
        }
    }



    public void eliminarCampeonato(Campeonato campeonato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            campeonato.setEstado(Estado.INACTIVO);
            em.merge(campeonato);


             //otra forma de eliminar de manera fisica
             /*categoria = em.merge(campeonato);

            // Eliminar la entidad
            em.remove(campeonato);*/


            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Campeonato> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Campeonato c", Campeonato.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }

    }

}
