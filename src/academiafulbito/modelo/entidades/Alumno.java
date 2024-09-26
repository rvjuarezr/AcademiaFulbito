/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author Walter Jair
 */
@Entity
@Table(name = "alumno", catalog = "bdacademiafulbito", schema = "")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_alumno", nullable = false)
    private int idAlumno;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombreAlumno;
    @Column(name = "Apellido", nullable = false, length = 50)
    private String apellidoAlumno;
    @Column(name = "Fecha_nacimiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "ID_categoria")
    private Categoria Categoria;
    @ManyToOne
    @JoinColumn(name = "ID_padre")
    private Padre padre;
    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private Estado estado;
    @Column(name = "dni", nullable = false, length = 12)
    private String dniAlumno;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, Date fechaNacimiento, Categoria categoria, Padre padre, Estado estado, String dniAlumno) {
        this.nombreAlumno = nombre;
        this.apellidoAlumno = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.Categoria = categoria;
        this.padre = padre;
        this.estado = estado;
        this.dniAlumno = dniAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.Categoria = categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Padre getPadre() {
        return padre;
    }

    public void setPadre(Padre padre) {
        this.padre = padre;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

}
