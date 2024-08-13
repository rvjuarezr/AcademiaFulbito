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
@Table(name = "padre", catalog = "bdacademiafulbito", schema = "")
public class Padre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_padre", nullable = false)
    private int idPadre;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombrePadre;
    @Column(name = "Apellido", nullable = false, length = 50)
    private String apellidoPadre;
    @Column(name = "Telefono", nullable = true, length = 15)
    private String telefono;

    public String getApellidoPadre() {
        return apellidoPadre;
    }

    public void setApellidoPadre(String apellidoPadre) {
        this.apellidoPadre = apellidoPadre;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
