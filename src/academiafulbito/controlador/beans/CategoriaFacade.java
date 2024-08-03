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

    private EntityManagerFactory emf;
    private EntityManager em;

    public CategoriaFacade() {
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
        em = emf.createEntityManager();
    }



    public List<Categoria> obtenerTodasLasCategorias() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    public void cerrar() {
        em.close();
        emf.close();
    }
}
