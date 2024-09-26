/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Cancha;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jose Checa C
 */
public class CanchaFacade implements EntityFacade<Cancha>{

    EntityManagerFactory emf;

    public CanchaFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    public List<Cancha> getListadoCanchas() {
        EntityManager em = getEntityManager();
        List<Cancha> listaCancha = null;
        try{
            listaCancha = em.createQuery("SELECT c FROM Cancha c", Cancha.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return listaCancha;
    }

    private EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    // Método para guardar una cancha
    public void guardarCancha(Cancha cancha) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cancha); // Guardar la entidad
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

    public Cancha findCanchaById(int idCancha) {

        EntityManager em = getEntityManager();
        try {
            return em.find(Cancha.class, idCancha);
        } finally {
            em.close();
        }
    }

    public void actualizarCancha(Cancha cancha) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(cancha);

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
            long totalCanchas = em.createQuery("SELECT COUNT(p) FROM Cancha p", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalCanchas / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cancha> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Cancha p", Cancha.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarCancha(Cancha cancha) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            cancha.setEstado(Estado.INACTIVO);
            em.merge(cancha);


             //otra forma de eliminar de manera fisica
             /*cancha = em.merge(cancha);

            // Eliminar la entidad
            em.remove(cancha);*/


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
}
