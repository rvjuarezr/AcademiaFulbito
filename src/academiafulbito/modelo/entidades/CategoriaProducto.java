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
 * @author Ronald J
 */

@Entity
@Table(name = "categoria_producto", catalog = "bdacademiafulbito", schema = "")
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CategoriaProd")
    private int idCategoriaProd;

    @Column(name = "nombre_categoria", nullable = false, length = 120)
    private String nombreCategoria;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public CategoriaProducto() {
    }

    public CategoriaProducto(int idCategoriaProd, String nombreCategoria, String descripcion) {
        this.idCategoriaProd = idCategoriaProd;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoriaProd() {
        return idCategoriaProd;
    }

    public void setIdCategoriaProd(int idCategoriaProd) {
        this.idCategoriaProd = idCategoriaProd;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


}
