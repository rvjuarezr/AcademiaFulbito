/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    // Método para listar las categorías
    public List<Categoria> getListadoCategorias() {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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
}
