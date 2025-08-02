/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import academiafulbito.modelo.enums.TipoPago;
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
    private Estado estadoPago;

    @ManyToOne
    @JoinColumn(name = "ID_usuario", referencedColumnName = "ID_usuario")
    private Usuario usuario;

    @Column(name = "correlativo", length = 8, nullable = false)
    private String correlativo;

    @ManyToOne
    @JoinColumn(name = "ID_tipos_comprobante", referencedColumnName = "ID_tipos_comprobante")
    private TiposComprobante tiposComprobante;

    @Column(name = "serie_documento", length = 4, nullable = false)
    private String serieDocumento;

    @Column(name = "doc_identidad", length = 20, nullable = false)
    private String docIdentidad;

    @Column(name = "razon_social", length = 120)
    private String razonSocial;

    @Column(name = "tipo_documento", length = 1, nullable = false)
    private String tipoDocumento;

    @Column(name = "operacion_gravada", nullable = false, precision = 10, scale = 2)
    private BigDecimal operacionGravada;

    @Column(name = "monto_igv", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoIgv;

    @Column(name = "operacion_inafecta", nullable = false, precision = 10, scale = 2)
    private BigDecimal operacionInafecta;

    @Column(name = "operacion_exonerada", nullable = false, precision = 10, scale = 2)
    private BigDecimal operacionExonerada;

    @Column(name = "operacion_gratuita", nullable = false, precision = 10, scale = 2)
    private BigDecimal operacionGratuita;

    @Column(name = "descuento", nullable = false, precision = 10, scale = 2)
    private BigDecimal descuento;

    @Column(name = "motivo_anulado", length = 255)
    private String motivoAnulado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pago", nullable = false)
    private TipoPago tipoPago;

    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Column(name = "hora_registro", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaRegistro;

    @Column(name = "moneda", length = 3, nullable = false)
    private String moneda;

    @Column(name = "tipo_cambio", precision = 10, scale = 3)
    private BigDecimal tipoCambio;
    // --- Nuevos campos para montos recibidos y cambio ---
    @Column(name = "monto_efectivo_recibido", precision = 10, scale = 2)
    private BigDecimal montoEfectivoRecibido;
    @Column(name = "monto_yape_recibido", precision = 10, scale = 2)
    private BigDecimal montoYapeRecibido;
    @Column(name = "monto_plin_recibido", precision = 10, scale = 2)
    private BigDecimal montoPlinRecibido;
    @Column(name = "cambio_entregado", precision = 10, scale = 2)
    private BigDecimal cambioEntregado;
// --- Fin nuevos campos ---
    public Pago() {
        // Inicializar los nuevos campos a 0.00 por defecto si no son null en DB
        this.montoEfectivoRecibido = BigDecimal.ZERO;
        this.montoYapeRecibido = BigDecimal.ZERO;
        this.montoPlinRecibido = BigDecimal.ZERO;
        this.cambioEntregado = BigDecimal.ZERO;
    }

    public Pago(int idPago, Matricula matricula, Date fechaPago, BigDecimal monto, Estado estadoPago, Usuario usuario, 
            String correlativo, TiposComprobante tiposComprobante, String serieDocumento, String docIdentidad, String razonSocial,
            String tipoDocumento, BigDecimal operacionGravada, BigDecimal montoIgv, BigDecimal operacionInafecta,
            BigDecimal operacionExonerada, BigDecimal operacionGratuita, BigDecimal descuento, String motivoAnulado, TipoPago tipoPago,
            Date fechaHora, Date horaRegistro, String moneda, BigDecimal tipoCambio, BigDecimal montoEfectivoRecbido, BigDecimal montoYapeRecibido,
            BigDecimal montoPlinRecibido, BigDecimal cambioEntregado) {
        this.idPago = idPago;
        this.matricula = matricula;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.usuario = usuario;
        this.correlativo = correlativo;
        this.tiposComprobante = tiposComprobante;
        this.serieDocumento = serieDocumento;
        this.docIdentidad = docIdentidad;
        this.razonSocial = razonSocial;
        this.tipoDocumento = tipoDocumento;
        this.operacionGravada = operacionGravada;
        this.montoIgv = montoIgv;
        this.operacionInafecta = operacionInafecta;
        this.operacionExonerada = operacionExonerada;
        this.operacionGratuita = operacionGratuita;
        this.descuento = descuento;
        this.motivoAnulado = motivoAnulado;
        this.tipoPago = tipoPago;
        this.fechaHora = fechaHora;
        this.horaRegistro = horaRegistro;
        this.moneda = moneda;
        this.tipoCambio = tipoCambio;
        this.montoEfectivoRecibido = montoEfectivoRecbido;
        this.montoYapeRecibido = montoYapeRecibido;
        this.montoPlinRecibido = montoPlinRecibido;
        this.cambioEntregado = cambioEntregado;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public Estado getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(Estado estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(Date horaRegistro) {
        this.horaRegistro = horaRegistro;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getMontoIgv() {
        return montoIgv;
    }

    public void setMontoIgv(BigDecimal montoIgv) {
        this.montoIgv = montoIgv;
    }

    public String getMotivoAnulado() {
        return motivoAnulado;
    }

    public void setMotivoAnulado(String motivoAnulado) {
        this.motivoAnulado = motivoAnulado;
    }

    public BigDecimal getOperacionExonerada() {
        return operacionExonerada;
    }

    public void setOperacionExonerada(BigDecimal operacionExonerada) {
        this.operacionExonerada = operacionExonerada;
    }

    public BigDecimal getOperacionGratuita() {
        return operacionGratuita;
    }

    public void setOperacionGratuita(BigDecimal operacionGratuita) {
        this.operacionGratuita = operacionGratuita;
    }

    public BigDecimal getOperacionGravada() {
        return operacionGravada;
    }

    public void setOperacionGravada(BigDecimal operacionGravada) {
        this.operacionGravada = operacionGravada;
    }

    public BigDecimal getOperacionInafecta() {
        return operacionInafecta;
    }

    public void setOperacionInafecta(BigDecimal operacionInafecta) {
        this.operacionInafecta = operacionInafecta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSerieDocumento() {
        return serieDocumento;
    }

    public void setSerieDocumento(String serieDocumento) {
        this.serieDocumento = serieDocumento;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public TiposComprobante getTiposComprobante() {
        return tiposComprobante;
    }

    public void setTiposComprobante(TiposComprobante tiposComprobante) {
        this.tiposComprobante = tiposComprobante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getCambioEntregado() {
        return cambioEntregado;
    }

    public void setCambioEntregado(BigDecimal cambioEntregado) {
        this.cambioEntregado = cambioEntregado;
    }

    public BigDecimal getMontoEfectivoRecibido() {
        return montoEfectivoRecibido;
    }

    public void setMontoEfectivoRecibido(BigDecimal montoEfectivoRecibido) {
        this.montoEfectivoRecibido = montoEfectivoRecibido;
    }

    public BigDecimal getMontoPlinRecibido() {
        return montoPlinRecibido;
    }

    public void setMontoPlinRecibido(BigDecimal montoPlinRecibido) {
        this.montoPlinRecibido = montoPlinRecibido;
    }

    public BigDecimal getMontoYapeRecibido() {
        return montoYapeRecibido;
    }

    public void setMontoYapeRecibido(BigDecimal montoYapeRecibido) {
        this.montoYapeRecibido = montoYapeRecibido;
    }

}
