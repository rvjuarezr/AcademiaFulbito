/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class CategoriaFacade {

    EntityManagerFactory emf;
    

    public CategoriaFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las categorías
    public List<Categoria> getListadoCategorias() {
        EntityManager em = getEntityManager();
        List<Categoria> categorias = null;
        try {
            categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return categorias;
    }

    // Método para guardar una categoría
    public void guardarCategoria(Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria); // Guardar la entidad
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

    public Categoria findCategoriaById(int idCategoria) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, idCategoria);
        } finally {
            em.close();
        }
    }

    public void actualizarCategoria(Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(categoria);

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

    public List<Categoria> listarCategoriasPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalCategorias = em.createQuery("SELECT COUNT(c) FROM Categoria c", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalCategorias / tamanioPagina);
        } finally {
            em.close();
        }
    }


}
