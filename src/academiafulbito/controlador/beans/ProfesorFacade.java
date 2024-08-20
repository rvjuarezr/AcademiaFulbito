/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class ProfesorFacade {

    EntityManagerFactory emf;

    public ProfesorFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    // Método para listar las padres
    public List<Profesor> getListadoProfesores() {
        EntityManager em = emf.createEntityManager();
        List<Profesor> profesores = null;
        try {
            profesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return profesores;
    }

    // Método para guardar una categoría
    public void guardarProfesor(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(profesor); // Guardar la entidad
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
