/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author SISTEMAS3
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

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

}
