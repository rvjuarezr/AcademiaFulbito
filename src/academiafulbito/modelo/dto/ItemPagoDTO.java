/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.dto;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para representar la informacion de
 * una fila de la tabla de conceptos de pago en la interfaz de usuario (jifPagos).
 * Se utiliza para transferir estos datos a la capa de negocio (PagoFacade)
 * para la creacion de las entidades DetallePago.
 *
 * @author Ronald J
 */
public class ItemPagoDTO {

    // Estos campos corresponden a la informacion que leemos de las columnas de la tabla Swing.
    private int idProducto;
    private String nombreProducto; // Nombre del producto/servicio
    private BigDecimal precioUnitario; // Precio por unidad
    private BigDecimal cantidad; // Cantidad del item
    private BigDecimal subtotal; // Total de la linea (cantidad * precio unitario)

    // Nota: Si necesitaras pasar mas informacion de la tabla (como el ID de la categoria,
    // o el tipo tributario si ya lo calculaste/obtuviste en la UI), los agregarias aqui.

    /**
     * Constructor para crear un DTO con los datos de un item.
     *
     * @param idProducto ID del producto/servicio.
     * @param nombreProducto Nombre del producto/servicio.
     * @param precioUnitario Precio unitario del producto/servicio.
     * @param cantidad Cantidad del producto/servicio.
     * @param subtotal Subtotal calculado para la linea (cantidad * precioUnitario).
     */
    public ItemPagoDTO(int idProducto, String nombreProducto, BigDecimal precioUnitario, BigDecimal cantidad, BigDecimal subtotal) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // --- Getters (necesarios para acceder a los datos desde el Facade) ---

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    // No necesitamos setters para este DTO, ya que su proposito es solo transportar datos
    // desde la UI (donde se crea con el constructor) hasta el Facade (donde se consumen los datos via getters).

    // Opcional: Puedes agregar un metodo toString() para facilitar la depuracion.
    @Override
    public String toString() {
        return "ItemPagoDTO{" +
               "idProducto=" + idProducto +
               ", nombreProducto='" + nombreProducto + '\'' +
               ", precioUnitario=" + precioUnitario +
               ", cantidad=" + cantidad +
               ", subtotal=" + subtotal +
               '}';
    }
}
