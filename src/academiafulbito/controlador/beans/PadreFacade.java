/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Padre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class PadreFacade {

    EntityManagerFactory emf;
    

    public PadreFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    // Método para listar las padres
    public List<Padre> getListadoPadres() {
        EntityManager em = emf.createEntityManager();
        List<Padre> padres = null;
        try {
            padres = em.createQuery("SELECT p FROM Padre p", Padre.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return padres;
    }

    // Método para guardar una categoría
    public void guardarPadre(Padre padre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(padre); // Guardar la entidad
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
