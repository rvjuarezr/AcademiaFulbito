/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.controlador.beans;

import academiafulbito.modelo.dto.ItemPagoDTO;
import academiafulbito.modelo.entidades.ControlCorrelativo;
import academiafulbito.modelo.entidades.DetallePago;
import academiafulbito.modelo.entidades.Matricula;
import academiafulbito.modelo.entidades.Pago;
import academiafulbito.modelo.entidades.ProductoServicio;
import academiafulbito.modelo.entidades.Serie;
import academiafulbito.modelo.entidades.Usuario;
import academiafulbito.modelo.enums.EstadoPago;
import academiafulbito.modelo.interfaces.EntityFacade;
import academiafulbito.vista.utilidades.Utils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ronald J
 */
public class PagoFacade  implements EntityFacade<Pago>{

    EntityManagerFactory emf;

    public PagoFacade(){
        emf = Persistence.createEntityManagerFactory("AcademiaFulbitoPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Registra un pago al contado, incluyendo la gestion transaccional
     * del correlativo, la actualizacion de la matricula (si aplica)
     * y la insercion de los detalles de los items.
     *
     * @param nuevoPago El objeto Pago pre-configurado con los datos (incluido el desglose y total).
     * @param serieSeleccionada La Serie seleccionada para el comprobante.
     * @param matriculaParaPago La Matricula asociada (puede ser null).
     * @param usuarioLogueado El Usuario que realiza la operacion.
     * @param itemsDeLaTabla La lista de DTOs con los datos de los items de la tabla Swing.
     * @throws Exception Si ocurre algun error durante la transaccion.
     */
    public void registrarPagoContadoTransactionally(
            Pago nuevoPago,// Recibe el Pago con total y desgloses ya calculados en la UI
            Serie serieSeleccionada,
            Matricula matriculaParaPago, // Puede ser null
            Usuario usuarioLogueado,
            List<ItemPagoDTO> itemsDeLaTabla // Lista de items para los detalles
            ) throws Exception {
        EntityManager em = getEntityManager();
        em.getTransaction().begin(); // *** INICIA LA TRANSACCIÓN JPA ***

        try {
            // Asegurarse de que las entidades relacionadas esten en el contexto de persistencia
            // merge() devuelve la instancia "managed" (adjunta) que debes usar.
            Serie managedSerie = em.merge(serieSeleccionada); // Adjunta la Serie
            if (managedSerie == null) {
                // Si managedSerie no existe en la DB, es un error de datos.
                // Lanzamos una excepcion para hacer rollback de toda la transaccion.
                throw new Exception("Error de datos: SERIE no encontrada en la base de datos.");
            }
            Usuario managedUsuario = em.merge(usuarioLogueado); // Adjunta el Usuario
            Matricula managedMatricula = null;
            if (matriculaParaPago != null) {
                 managedMatricula = em.merge(matriculaParaPago); // Adjunta la Matricula si no es null
            }

            // 1. Gestionar el Correlativo (Obtener, Incrementar, Actualizar ControlCorrelativo)
            ControlCorrelativo control = null;
            try {
                Query query = em.createQuery("SELECT c FROM ControlCorrelativo c WHERE c.serie = :serie");
                query.setParameter("serie", managedSerie); // Usamos la serie adjunta
                query.setLockMode(LockModeType.PESSIMISTIC_WRITE); // Bloqueo pesimista
                control = (ControlCorrelativo) query.getSingleResult();

            } catch (NoResultException e) {
                // Si no se encuentra el registro para esta serie, creamos uno nuevo.
                control = new ControlCorrelativo(managedSerie, 0); // Empezamos con 0
                em.persist(control); // Persistimos el nuevo registro de control
                em.flush(); // Forzar la inserción para que el bloqueo funcione correctamente si no existía
            }

            // Incrementar el correlativo
            int siguienteCorrelativo = control.getUltimoCorrelativo() + 1;
            control.setUltimoCorrelativo(siguienteCorrelativo); // Actualizar el correlativo en la entidad control

            // JPA detectara el cambio en `control` al hacer commit.

            // Formatear el correlativo a String de 8 dígitos con ceros iniciales
            String correlativoGenerado = String.format("%08d", siguienteCorrelativo);
            nuevoPago.setCorrelativo(correlativoGenerado); // Asignar al objeto Pago

            // Asignar las entidades relacionadas adjuntas al objeto Pago
            nuevoPago.setMatricula(managedMatricula); // Puede ser null
            nuevoPago.setUsuario(managedUsuario);
            nuevoPago.setTiposComprobante(managedSerie.getTipo_comprobantes()); // Enlazar TipoComprobante a traves de la Serie adjunta
            nuevoPago.setSerieDocumento(managedSerie.getCodigoSerie());

            // 2. Persistir el Registro del Pago (Insert en tabla `pago`)
            // El objeto `nuevoPago` ya viene con el total y los desgloses calculados desde la UI.
            // Lo adjuntamos y persistimos.
            em.persist(nuevoPago);
            em.flush(); // Forzar la inserción para obtener el ID autogenerado de Pago.

            // 3. Persistir Detalles de Ítems (Insert en tabla `detalle_pago`)
            // Iterar sobre la lista de DTOs recibida para crear entidades DetallePago
            if (itemsDeLaTabla != null && !itemsDeLaTabla.isEmpty()) {
                 for (ItemPagoDTO itemDto : itemsDeLaTabla) {
                     try {
                         // Buscar la entidad ProductoServicio en el contexto de persistencia usando el ID del DTO.
                         ProductoServicio managedProducto = em.find(ProductoServicio.class, itemDto.getIdProducto());

                         if (managedProducto == null) {
                              // Si el ProductoServicio no existe en la DB, es un error de datos.
                              // Lanzamos una excepcion para hacer rollback de toda la transaccion.
                              throw new Exception("Error de datos: Producto con ID " + itemDto.getIdProducto() + " (item '" + itemDto.getNombreProducto() + "') no encontrado en la base de datos.");
                         }

                         // Validar que la cantidad BigDecimal del DTO se pueda convertir a int para la entidad/DB
                         // Tu campo 'cantidad' en detalle_pago es INT. Si el DTO trae decimales, debes manejarlos.
                         int cantidadInt;
                         try {
                             // Intenta obtener el valor entero exacto. Fallará si hay decimales.
                             cantidadInt = itemDto.getCantidad().intValueExact();
                             itemDto.getCantidad().intValueExact();
                         } catch (ArithmeticException ex) {
                             // Si falla, significa que la cantidad tiene decimales no cero.
                              // Decide si redondear, truncar o lanzar error. Tu DB es INT.
                              // Lanzar error es más seguro para evitar pérdida de datos si esperas enteros.
                              throw new Exception("Error: La cantidad para el producto '" + managedProducto.getNombreProducto() + "' (" + itemDto.getCantidad() + ") no es un número entero válido.", ex);
                         }

                         // Crear una nueva instancia de DetallePago (ENTIDAD JPA)
                         DetallePago detalle = new DetallePago();
                         detalle.setPago(nuevoPago); // Enlazar el detalle con el pago principal (el objeto persistido `nuevoPago` ya es managed)
                         detalle.setProductoServicio(managedProducto); // Enlazar con el producto managed
                         detalle.setCantidad(cantidadInt); // Asignar la cantidad entera validada
                         detalle.setSubtotal(itemDto.getSubtotal()); // Subtotal del item (ya es BigDecimal)

                         // Persistir la nueva entidad DetallePago.
                         em.persist(detalle);

                     } catch (Exception e) {
                          // Captura errores durante el procesamiento de un item (ej: producto no encontrado, error de conversion de cantidad).
                          // Relanza la excepcion para que el bloque catch exterior haga rollback.
                          throw e; // Relanzar la excepcion original o una nueva con mas contexto
                     }
                 }
                 // JPA gestionará la insercion de todos los detalles persistidos al hacer commit.
            } else if (itemsDeLaTabla == null || itemsDeLaTabla.isEmpty()) {
                 // Si la lista de DTOs esta vacia o es null.
                 // Si un pago SIEMPRE debe tener detalles, lanza una excepcion aqui.
                 // throw new Exception("No se proporcionaron detalles de items para el pago."); // Ejemplo si es requerido.
                  // Si un pago puede ser por ajuste sin items, este bloque no haria nada y continuaria.
                  System.out.println("Advertencia: Se registró un pago sin detalles de items."); // Logear o ignorar
            }

            // 4. Actualizar el Estado de la Matrícula (Si aplica)
            if (managedMatricula != null) {
                // *** Lógica para determinar el NUEVO ESTADO de la Matrícula ***
                // Basado en tu logica de negocio (si cubre el monto total, etc.)
                // Asumimos que la entidad Matricula tiene un setter para el estado
                managedMatricula.setEstadoPago(EstadoPago.COMPLETO); // Asumiendo que el setter recibe String

                // JPA detectara el cambio en managedMatricula al hacer commit.
            }

            em.getTransaction().commit(); // *** CONFIRMA LA TRANSACCIÓN: Guarda todos los cambios en la DB ***

            // *** MENSAJE DE EXITO DESDE EL FACADE ***
            // Este mensaje se muestra solo si la transaccion fue exitosa.
            Utils.mensajeInformacion("Comprobante registrado exitosamente.\n" +
                                     nuevoPago.getTiposComprobante().getTiposCpbte() + // Asegurate que getTiposCpbte() existe en TiposComprobante
                                     " Nro: " + nuevoPago.getSerieDocumento() + "-" + nuevoPago.getCorrelativo() + "\n" +
                                     "Total Pagado: S/. " + nuevoPago.getMonto());
        } catch (Exception e) {
            // Si ocurre cualquier error DENTRO del bloque try, hacemos rollback si la transaccion esta activa.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // *** HACER ROLLBACK ***
            }
            // Relanzar la excepción para que la UI la maneje.
            throw e;

        } finally {
            // Cerrar el EntityManager SIEMPRE.
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public int obtenerTotalPaginas(int tamanioPagina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pago> listarEntidadesPaginadas(int paginaActual, int tamanioPagina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
