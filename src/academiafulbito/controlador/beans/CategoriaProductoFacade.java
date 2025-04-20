/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import academiafulbito.modelo.entidades.CategoriaProducto;
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
public class CategoriaProductoFacade implements EntityFacade<CategoriaProducto>{

    EntityManagerFactory emf;
    

    public CategoriaProductoFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para listar las categorías
    public List<CategoriaProducto> getListadoCategoriaProducto() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM CategoriaProducto c where c.estado = :estado", CategoriaProducto.class)
                    .setParameter("estado", Estado.ACTIVO)
                    .getResultList();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
    }

    // Método para guardar una categoría
    public void guardarCategoriaProducto(CategoriaProducto categoriaProducto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoriaProducto); // Guardar la entidad
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

    public CategoriaProducto findCategoriaProductoById(int idCategoria) {
          EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaProducto.class, idCategoria);
        } finally {
            em.close();
        }
    }

    public void actualizarCategoria(CategoriaProducto categoriaProducto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Simplemente se realiza el merge para actualizar la entidad
            em.merge(categoriaProducto);

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
            long totalCategorias = em.createQuery("SELECT COUNT(c) FROM CategoriaProducto c", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalCategorias / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<CategoriaProducto> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM CategoriaProducto c", CategoriaProducto.class).setFirstResult((paginaActual - 1) * tamanioPagina).setMaxResults(tamanioPagina).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarCategoria(CategoriaProducto categoriaProducto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            categoriaProducto.setEstado(Estado.ANULADO);
            em.merge(categoriaProducto);

            
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
