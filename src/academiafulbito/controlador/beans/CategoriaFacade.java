/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Ronald J
 */
public class CategoriaFacade {

    EntityManager em = Persistence.createEntityManagerFactory("AcademiaFulbitoPU").createEntityManager();

    public List<Categoria> obtenerTodasLasCategorias() throws SQLException {
        //return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Categoria.class));
        Query q = (Query) em.createQuery(cq);
        return q.getResultList();
    }

    public List<Categoria> getListadoCategorias() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }
}
