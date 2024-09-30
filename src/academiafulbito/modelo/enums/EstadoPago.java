/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.modelo.enums;

/**
 *
 * @author Ronald J
 */
public enum EstadoPago {
    PENDIENTE,     // El pago aún no ha sido realizado
    COMPLETO,      // El pago se ha realizado en su totalidad
    PARCIAL,       // El pago se ha realizado parcialmente
    VENCIDO,       // El pago no se realizó en el tiempo estipulado
    CANCELADO      // El pago o la matrícula han sido cancelados
}
