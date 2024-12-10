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
@Table(name = "pago", catalog = "bdacademiafulbito", schema = "")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_pago")
    private int idPago;

    @ManyToOne
    @JoinColumn(name = "ID_matricula", referencedColumnName = "ID_matricula")
    private Matricula matricula;

    @Column(name = "Fecha_pago", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "Monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "Estado")
    private EstadoPago estadoPago;

    @ManyToOne
    @JoinColumn(name = "ID_usuario", referencedColumnName = "ID_usuario")
    private Usuario usuario;

    public Pago() {
    }

    public Pago(int idPago, Matricula matricula, Date fechaPago, BigDecimal monto, EstadoPago estadoPago, Usuario usuario) {
        this.idPago = idPago;
        this.matricula = matricula;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.usuario = usuario;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
