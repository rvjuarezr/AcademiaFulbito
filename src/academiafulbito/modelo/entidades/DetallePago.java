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
@Table(name = "detalle_pago", catalog = "bdacademiafulbito", schema = "")
public class DetallePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_detalle")
    private int idDetalle;
    
    @ManyToOne
    @JoinColumn(name = "ID_pago", referencedColumnName = "ID_pago")
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "ID_producto", referencedColumnName = "ID_producto")
    private ProductoServicio productoServicio;

    @Column(name = "subtotal", precision = 14, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "cantidad", length = 5, nullable = false)
    private int cantidad;

    public DetallePago() {
    }

    public DetallePago(int idDetalle, Pago pago, ProductoServicio productoServicio, BigDecimal subtotal, int cantidad) {
        this.idDetalle = idDetalle;
        this.pago = pago;
        this.productoServicio = productoServicio;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public ProductoServicio getProductoServicio() {
        return productoServicio;
    }

    public void setProductoServicio(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    
}
