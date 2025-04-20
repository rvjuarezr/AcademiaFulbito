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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Walter Jair
 */
@Entity
@Table(name = "serie", catalog = "bdacademiafulbito", schema = "")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_serie", nullable = false)
    private int idSerie;
    @Column(name = "codigo_serie", nullable = false, length = 10)
    private String codigo_serie;
    @ManyToOne
    @JoinColumn(name = "ID_tipos_comprobante")
    private TiposComprobante tipoComprobantes;

    public Serie() {
    }

    public Serie(String codigo_serie, TiposComprobante tipoComprobantes) {
        this.codigo_serie = codigo_serie;
        this.tipoComprobantes = tipoComprobantes;
    }

    public String getCodigo_serie() {
        return codigo_serie;
    }

    public void setCodigo_serie(String codigo_serie) {
        this.codigo_serie = codigo_serie;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public TiposComprobante getTipo_comprobantes() {
        return tipoComprobantes;
    }

    public void setTipoCcomprobantes(TiposComprobante tipoComprobantes) {
        this.tipoComprobantes = tipoComprobantes;
    }

     public String getCodigoSerie() {
        return codigo_serie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigo_serie = codigoSerie;
    }
    @Override
    public String toString() {
        return this.codigo_serie; // Muestra solo el nombre del tipo de comprobante
    }

}
