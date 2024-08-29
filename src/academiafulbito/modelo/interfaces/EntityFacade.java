/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.interfaces;

import java.util.List;

/**
 *
 * @author Ronald J
 */
public interface EntityFacade<T> {

    int obtenerTotalPaginas(int tamanioPagina);
    List<T> listarEntidadesPaginadas(int paginaActual, int tamanioPagina);
}
