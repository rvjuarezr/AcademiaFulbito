/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;


import academiafulbito.modelo.entidades.ProductoServicio;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class ProductoServicioFacade implements EntityFacade<ProductoServicio>{

    EntityManagerFactory emf;
    

    public ProductoServicioFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las productos servicios
    public List<ProductoServicio> getListadoProductoServicio() {
        EntityManager em = getEntityManager();
        List<ProductoServicio> productoServicio = null;
        try {
            productoServicio = em.createQuery("SELECT p FROM ProductoServicio p", ProductoServicio.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return productoServicio;
    }

    // Método para guardar una categoría
    public void guardarProductoServicio(ProductoServicio productoServicio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(productoServicio); // Guardar la entidad
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

    public ProductoServicio findProductoServicioById(int ID_producto) {
        EntityManager em = getEntityManager();
        if (em == null) {
            throw new IllegalStateException("EntityManager no está disponible.");
        }
        try {
            return em.find(ProductoServicio.class, ID_producto);
        } finally {
            em.close();
        }
    }

    public void actualizarProductoServicio(ProductoServicio productoServicio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(productoServicio);

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
            long totalProductoServicio = em.createQuery("SELECT COUNT(p) FROM ProductoServicio p", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalProductoServicio / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProductoServicio> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM ProductoServicio p", ProductoServicio.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarProductoServicio(ProductoServicio productoServicio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            productoServicio.setEstado(Estado.INACTIVO);
            em.merge(productoServicio);

            
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
