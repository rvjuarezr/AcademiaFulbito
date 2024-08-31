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
 * @author Jose Checa
 */
@Entity
@Table(name = "lugar_entrenamiento", catalog = "bdacademiafulbito", schema = "")
public class LugarEntrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_lugar", nullable = false)
    private int idLugar;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "Direccion", nullable = true, length = 100)
    private String direccion;
    @Column(name = "Ubigeo", nullable = true, length = 10)
    private String ubigeo;
    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private Estado estado;

    public LugarEntrenamiento() {
    }

    public LugarEntrenamiento(String nombre, String direccion, String ubigeo, Estado estado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ubigeo = ubigeo;
        this.estado = estado;
    }
    //Getter and setter
    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
