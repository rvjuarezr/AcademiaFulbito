/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.Rol;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Walter Jair
 */
@Entity
@Table(name="usuario", catalog="bdacademiafulbito", schema="")
public class Alumno {
@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_alumno", nullable = false)
    private int idAlumno;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombreAlumno;
    @Column(name = "Apellido", nullable = false, length = 50)
    private String apellidoAlumno;
    @Column(name = "Fecha_nacimiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "ID_categoria", nullable = false, length = 11)
    private int idCategoria;
    @Column(name = "ID_padre", nullable = false, length = 11)
    private int idPadre;
    private Rol rol;
    private Estado estado;

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }




}
