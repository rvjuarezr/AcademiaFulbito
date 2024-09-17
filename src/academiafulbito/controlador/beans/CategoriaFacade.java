/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class CategoriaFacade implements EntityFacade<Categoria>{

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
        if (em == null) {
            throw new IllegalStateException("EntityManager no está disponible.");
        }
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

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalCategorias = em.createQuery("SELECT COUNT(c) FROM Categoria c", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalCategorias / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Categoria> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarCategoria(Categoria categoria) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            categoria.setEstado(Estado.INACTIVO);
            em.merge(categoria);

            
             //otra forma de eliminar de manera fisica
             /*categoria = em.merge(categoria);

            // Eliminar la entidad
            em.remove(categoria);*/
             

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
