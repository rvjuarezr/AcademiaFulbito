/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import academiafulbito.modelo.enums.Estado;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ronald J
 */
@Entity
@Table(name = "tipos_comprobante", catalog = "bdacademiafulbito", schema = "")
public class TiposComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_tipos_comprobante")
    private int idTiposComprobante;

    @Column(name = "tipos_cpbte", nullable = false, length = 30)
    private String tiposCpbte;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Enumerated(EnumType.STRING) // Mapeo como tipo STRING
    @Column(name = "estado")
    private Estado estado;

    @OneToMany(mappedBy = "tiposComprobante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos;

    public TiposComprobante() {
    }

    public TiposComprobante(int idTiposComprobante, String tiposCpbte, String descripcion, Estado estado, List<Pago> pagos) {
        this.idTiposComprobante = idTiposComprobante;
        this.tiposCpbte = tiposCpbte;
        this.descripcion = descripcion;
        this.estado = estado;
        this.pagos = pagos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdTiposComprobante() {
        return idTiposComprobante;
    }

    public void setIdTiposComprobante(int idTiposComprobante) {
        this.idTiposComprobante = idTiposComprobante;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public String getTiposCpbte() {
        return tiposCpbte;
    }

    public void setTiposCpbte(String tiposCpbte) {
        this.tiposCpbte = tiposCpbte;
    }

    @Override
    public String toString() {
        return this.tiposCpbte; // Muestra solo el nombre del tipo de comprobante
    }
}
