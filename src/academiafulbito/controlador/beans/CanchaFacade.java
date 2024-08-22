/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.Cancha;
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
public class CanchaFacade {

    EntityManager em = Persistence.createEntityManagerFactory("AcademiaFulbitoPU").createEntityManager();

    public List<Cancha> getListadoCanchas() {
        return em.createQuery("SELECT c FROM Cancha c", Cancha.class).getResultList();
    }
}
