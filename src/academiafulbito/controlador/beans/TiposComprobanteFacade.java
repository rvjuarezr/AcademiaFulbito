/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.entidades.TiposComprobante;
import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.interfaces.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronald J
 */
public class TiposComprobanteFacade implements EntityFacade<TiposComprobante>{

    EntityManagerFactory emf;


    public TiposComprobanteFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<TiposComprobante> obtenerTiposComprobante() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT t FROM TiposComprobante t WHERE t.estado = :estado", TiposComprobante.class
                    ).setParameter("estado", Estado.ACTIVO).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TiposComprobante> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
