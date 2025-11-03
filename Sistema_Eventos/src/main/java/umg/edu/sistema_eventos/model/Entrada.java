package umg.edu.sistema_eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDateTime fechaCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    private BigDecimal precioVenta;

    private boolean asistio = false; // Usado para el reporte de Control de Asistencia

    // --- RELACIONES PK/FK ---

    // FK 1: Un cliente puede tener muchas entradas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false) // Columna FK en la BD
    @NotNull(message = "La entrada debe estar asociada a un cliente")
    private Cliente cliente;

    // FK 2: Un evento puede tener muchas entradas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false) // Columna FK en la BD
    @NotNull(message = "La entrada debe estar asociada a un evento")
    private Evento evento;

    // FK 3: Una entrada tiene un registro de pago
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id", nullable = false)
    @NotNull(message = "La entrada debe tener un pago asociado")
    private Pago pago;


    public Entrada() {
        this.fechaCompra = LocalDateTime.now();
    }

    // --- Getters y Setters ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public LocalDateTime getFechaCompra() {return fechaCompra;}
    public void setFechaCompra(LocalDateTime fechaCompra) {this.fechaCompra = fechaCompra;}
    public BigDecimal getPrecioVenta() {return precioVenta;}
    public void setPrecioVenta(BigDecimal precioVenta) {this.precioVenta = precioVenta;}
    public boolean isAsistio() {return asistio;}
    public void setAsistio(boolean asistio) {this.asistio = asistio;}
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public Evento getEvento() {return evento;}
    public void setEvento(Evento evento) {this.evento = evento;}
    public Pago getPago() {return pago;}
    public void setPago(Pago pago) {this.pago = pago;}
}