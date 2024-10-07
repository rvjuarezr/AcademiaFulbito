/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.EstadoPago;
import java.math.BigDecimal;
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
import javax.persistence.TemporalType;

/**
 *
 * @author Ronald J
 */

@Entity
@Table(name = "matricula", catalog = "bdacademiafulbito", schema = "")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_matricula")
    private int idMatricula;

    @ManyToOne
    @JoinColumn(name = "ID_alumno", referencedColumnName = "ID_alumno")
    private Alumno alumno;

    @Column(name = "Fecha_matricula", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;

    @ManyToOne
    @JoinColumn(name = "ID_horario", referencedColumnName = "ID_horario", nullable = false)
    private Horario horario;

    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private EstadoPago estado;

    @Column(name = "observaciones", columnDefinition = "LONGTEXT")
    private String observaciones;

    @Column(name = "monto_pago", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoPago;

    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

    // Constructor vacío
    public Matricula() {}

    // Getters y Setters

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "idMatricula=" + idMatricula +
                ", alumno=" + alumno +
                ", fechaMatricula=" + fechaMatricula +
                ", horario=" + horario +
                '}';
    }
}
