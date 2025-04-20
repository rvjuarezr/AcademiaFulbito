/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Serie;
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
public class SerieFacade implements EntityFacade<Serie> {

    EntityManagerFactory emf;
    

    public SerieFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    // Método para listar las padres
    public List<Serie> getListadoPadres() {
        EntityManager em = getEntityManager();
        List<Serie> series = null;
        try {
            series = em.createQuery("SELECT s FROM Serie s", Serie.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return series;
    }

    // Método para guardar una serie
    public void guardarSerie(Serie serie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(serie); // Guardar la entidad
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


    public Padre findSerieById(int idSerie) {

        EntityManager em = getEntityManager();
        try {
            return em.find(Padre.class, idSerie);
        } finally {
            em.close();
        }
    }

    public void actualizarSerie(Serie serie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(serie);

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
            long totalSeries = em.createQuery("SELECT COUNT(s) FROM Serie s", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalSeries / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Serie> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {

        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Serie s", Serie.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

        public void eliminarSerie(Serie serie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            /*// Asegúrate de que la entidad esté gestionada
            serie.setEstado(Estado.INACTIVO);
            em.merge(serie);*/


             //otra forma de eliminar de manera fisica
             serie = em.merge(serie);

            // Eliminar la entidad
            em.remove(serie);


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
