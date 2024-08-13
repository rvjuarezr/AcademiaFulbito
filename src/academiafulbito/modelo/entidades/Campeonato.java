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
 * @author SISTEMAS3
 */
@Entity
@Table(name = "campeonato", catalog = "bdacademiafulbito", schema = "")
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_campeonato", nullable = false)
    private int idCampeonato;
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "Temporada", nullable = false, length = 50)
    private String temporada;

    //Getter y setter
    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }



}
