/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Alumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class AlumnoFacade {

    EntityManagerFactory emf;

    public AlumnoFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    // Método para listar las padres
    public List<Alumno> getListadoAlumnos() {
        EntityManager em = emf.createEntityManager();
        List<Alumno> alumnos = null;
        try {
            alumnos = em.createQuery("SELECT a FROM Alumnos a", Alumno.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Siempre cerrar el EntityManager al final
        }
        return alumnos;
    }

    // Método para guardar una categoría
    public void guardarAlumno(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alumno); // Guardar la entidad
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
