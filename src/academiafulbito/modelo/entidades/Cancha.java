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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author SISTEMAS3
 */
@Entity
@Table(name = "cancha", catalog = "bdacademiafulbito", schema = "")
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_cancha", nullable = false)
    private int idCancha;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "ID_lugar")
    private LugarEntrenamiento id_lugar;
    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private Estado estado;

    public Cancha() {
    }

    public Cancha(String nombre, LugarEntrenamiento id_lugar, Estado estado) {
        this.nombre = nombre;
        this.id_lugar = id_lugar;
        this.estado = estado;
    }

    //Getter y setter
    public int getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public LugarEntrenamiento getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(LugarEntrenamiento id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
