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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ronald J
 */
@Entity
@Table(name = "control_correlativo", catalog = "bdacademiafulbito", schema = "")
public class ControlCorrelativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_control_correlativo")
    private Integer idControlCorrelativo;

    @ManyToOne
    @JoinColumn(name = "ID_serie", nullable = false)
    private Serie serie;

    @Column(name = "ultimo_correlativo", nullable = false)
    private Integer ultimoCorrelativo;

    // Constructor vacío (obligatorio para JPA)
    public ControlCorrelativo() {
    }

    // Constructor con parámetros
    public ControlCorrelativo(Serie serie, Integer ultimoCorrelativo) {
        this.serie = serie;
        this.ultimoCorrelativo = ultimoCorrelativo;
    }

    // Getters y Setters
    public Integer getIdControlCorrelativo() {
        return idControlCorrelativo;
    }

    public void setIdControlCorrelativo(Integer idControlCorrelativo) {
        this.idControlCorrelativo = idControlCorrelativo;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getUltimoCorrelativo() {
        return ultimoCorrelativo;
    }

    public void setUltimoCorrelativo(Integer ultimoCorrelativo) {
        this.ultimoCorrelativo = ultimoCorrelativo;
    }

    @Override
    public String toString() {
        return "ControlCorrelativo{" +
                "idControlCorrelativo=" + idControlCorrelativo +
                ", serie=" + (serie != null ? serie.getCodigoSerie() : "N/A") +
                ", ultimoCorrelativo=" + ultimoCorrelativo +
                '}';
    }
    
}
