package umg.edu.sistema_eventos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// Anotación para indicar que esta clase es una tabla en la base de datos
@Entity
public class Evento {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // VALIDACION: El nombre no puede ser nulo ni estar en blanco
    @NotBlank(message = "El nombre del evento es obligatorio")
    private String nombre;

    // VALIDACION: La fecha/hora no puede ser nula
    @NotNull(message = "La fecha y hora del evento son obligatorias")
    private LocalDateTime fechaHora;

    // VALIDACION: El lugar no puede ser nulo ni estar en blanco
    @NotBlank(message = "El lugar del evento es obligatorio")
    private String lugar;

    // VALIDACION: El precio de la entrada no puede ser nulo y debe ser positivo o cero
    @PositiveOrZero(message = "El precio debe ser un valor positivo o cero, si se especifica.")
    private BigDecimal precioEntrada;

    // --- RELACIÓN BIDIRECCIONAL ---
    // Un Evento tiene muchas Entradas (One-to-Many)
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Importante para evitar bucles infinitos en JSON
    private List<Entrada> entradas;

    public Evento() {
    }

    // --- Getters y Setters ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public LocalDateTime getFechaHora() {return fechaHora;}
    public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}
    public String getLugar() {return lugar;}
    public void setLugar(String lugar) {this.lugar = lugar;}
    public BigDecimal getPrecioEntrada() {return precioEntrada;}
    public void setPrecioEntrada(BigDecimal precioEntrada) {this.precioEntrada = precioEntrada;}
    public List<Entrada> getEntradas() {return entradas;}
    public void setEntradas(List<Entrada> entradas) {this.entradas = entradas;}
}