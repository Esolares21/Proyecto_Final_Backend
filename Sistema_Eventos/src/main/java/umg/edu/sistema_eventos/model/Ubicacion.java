package umg.edu.sistema_eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la ubicación es obligatorio")
    private String nombre; // Nombre del lugar

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    private Integer capacidadMaxima;

    public Ubicacion() {
    }

    // --- Getters y Setters ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public Integer getCapacidadMaxima() {return capacidadMaxima;}
    public void setCapacidadMaxima(Integer capacidadMaxima) {this.capacidadMaxima = capacidadMaxima;}
}