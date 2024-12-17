/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.entidades;

import java.math.BigDecimal;
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
 * @author Ronald J
 */

@Entity
@Table(name = "producto_servicio", catalog = "bdacademiafulbito", schema = "")
public class ProductoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_producto")
    private int idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 120)
    private String nombreProducto;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "precio", precision = 14, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "stock", length = 5)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "ID_CategoriaProd", referencedColumnName = "ID_CategoriaProd", nullable = false)
    private CategoriaProducto categoriaProducto;

    public ProductoServicio() {
    }

    public ProductoServicio(int idProducto, String nombreProducto, String descripcion, BigDecimal precio, int stock, CategoriaProducto categoriaProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaProducto = categoriaProducto;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
