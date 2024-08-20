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
 * @author Walter Jair
 */
@Entity
@Table(name = "profesor", catalog = "bdacademiafulbito", schema = "")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_profesor", nullable = false)
    private int idProfesor;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombreProfesor;
    @Column(name = "Apellido", nullable = false, length = 50)
    private String apellidoProfesor;
    @Column(name = "Telefono", nullable = true, length = 15)
    private String telefono;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String telefono) {
        this.nombreProfesor = nombre;
        this.apellidoProfesor = apellido;
        this.telefono = telefono;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
