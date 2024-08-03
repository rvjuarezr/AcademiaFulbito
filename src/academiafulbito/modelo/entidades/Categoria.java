/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ronald J
 */
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;
    private String nombre;
    private int edadMin;
    private int edadMax;

    // Getters y Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }
}
