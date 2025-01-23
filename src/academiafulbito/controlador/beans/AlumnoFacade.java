/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Alumno;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.Sexo;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Walter Jair
 */
public class AlumnoFacade implements EntityFacade<Alumno>{

    private EntityManagerFactory emf;

    public AlumnoFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Alumno> getListadoAlumnos() {
        EntityManager em = getEntityManager();
        List<Alumno> alumnos = null;
        try {
            alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        } finally {
            em.close();
        }
        return alumnos;
    }

    public void guardarAlumno(Alumno alumno) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumnoById(int idAlumno) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, idAlumno);
        } finally {
            em.close();
        }
    }

    public void actualizarAlumno(Alumno alumno) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminarAlumno(Alumno alumno) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Asegúrate de que la entidad esté gestionada
            alumno.setEstado(Estado.INACTIVO);
            em.merge(alumno);


             //otra forma de eliminar de manera fisica
             /*profesor = em.merge(profesor);

            // Eliminar la entidad
            em.remove(profesor);*/


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

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            long totalAlumnos = em.createQuery("SELECT COUNT(a) FROM Alumno a", Long.class).getSingleResult();
            return (int) Math.ceil((double) totalAlumnos / tamanioPagina);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Alumno> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                    .setFirstResult((paginaActual - 1) * tamanioPagina)
                    .setMaxResults(tamanioPagina)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumnoByDni(String dniAlumno) {
        EntityManager em = getEntityManager();
        String sql;
        List<Alumno>lista;
        Alumno alumno;
        alumno=null;
        sql="SELECT a.* FROM alumno a WHERE a.dni='"+dniAlumno+"'";
        lista=em.createNativeQuery(sql,Alumno.class).getResultList();
        if(lista.size()>0){
            alumno=lista.get(0);
        }
        return alumno;
    }

    public Alumno findAlumnoByIdPadre(int idPadre) {
        EntityManager em = getEntityManager();
        String sql;
        List<Alumno>lista;
        Alumno alumno;
        alumno=null;
        sql="SELECT a.* FROM alumno a WHERE a.ID_padre='"+idPadre+"'";
        lista=em.createNativeQuery(sql,Alumno.class).getResultList();
        if(lista.size()>0){
            alumno=lista.get(0);
        }
        return alumno;
    }

}
