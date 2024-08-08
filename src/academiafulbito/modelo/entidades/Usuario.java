/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.Rol;
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
@Table(name="usuario", catalog="bdacademiafulbito", schema="")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUsuario;
    private String nombreUsuario;
    private String clave;
    private Rol rol;
    private Estado estado;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
