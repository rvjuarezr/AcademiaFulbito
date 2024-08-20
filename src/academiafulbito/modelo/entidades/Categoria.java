/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ronald J
 */
@Entity
@Table(name = "categoria", catalog = "bdacademiafulbito", schema = "")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_categoria", nullable = false)
    private int idCategoria;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "Edad_min", nullable = false, length = 11)
    private int edadMin;
    @Column(name = "Edad_max", nullable = false, length = 11)
    private int edadMax;
    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private Estado estado;

    public Categoria() {
    }

    public Categoria(String nombre, int edadMin, int edadMax, Estado estado) {
        this.nombre = nombre;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.estado = estado;
    }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
