package umg.edu.sistema_eventos.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// Esta clase simplifica la recepción de datos para crear una Entrada
public class EntradaRequestDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El ID del evento es obligatorio")
    private Long eventoId;

    @NotNull(message = "El precio de venta es obligatorio")
    private BigDecimal precioVenta;

    @NotNull(message = "El monto del pago es obligatorio")
    private BigDecimal montoPago;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago;

    // --- Getters y Setters ---

    public Long getClienteId() {return clienteId;}
    public void setClienteId(Long clienteId) {this.clienteId = clienteId;}
    public Long getEventoId() {return eventoId;}
    public void setEventoId(Long eventoId) {this.eventoId = eventoId;}
    public BigDecimal getPrecioVenta() {return precioVenta;}
    public void setPrecioVenta(BigDecimal precioVenta) {this.precioVenta = precioVenta;}
    public BigDecimal getMontoPago() {return montoPago;}
    public void setMontoPago(BigDecimal montoPago) {this.montoPago = montoPago;}
    public String getMetodoPago() {return metodoPago;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}
}