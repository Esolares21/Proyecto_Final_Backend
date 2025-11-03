package umg.edu.sistema_eventos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto pagado es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo")
    private BigDecimal monto;

    @NotNull(message = "La fecha del pago es obligatoria")
    private LocalDateTime fechaPago;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago; // Ejemplo: Tarjeta, Efectivo, Transferencia

    @NotBlank(message = "El estado del pago es obligatorio")
    private String estado; // Ejemplo: Completado, Pendiente, Fallido


    public Pago() {
        this.fechaPago = LocalDateTime.now(); // Por defecto, la fecha actual
    }

    // --- Getters y Setters ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public BigDecimal getMonto() {return monto;}
    public void setMonto(BigDecimal monto) {this.monto = monto;}
    public LocalDateTime getFechaPago() {return fechaPago;}
    public void setFechaPago(LocalDateTime fechaPago) {this.fechaPago = fechaPago;}
    public String getMetodoPago() {return metodoPago;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}