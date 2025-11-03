package umg.edu.sistema_eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del rol (ADMIN, USER)
    @NotBlank
    @Column(unique = true)
    private String nombre;

    // Relación Many-to-Many: Muchos Roles pueden ser asignados a muchos Usuarios
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    public Rol() {}

    // --- Getters y Setters ---

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Set<Usuario> getUsuarios() {return usuarios;}
    public void setUsuarios(Set<Usuario> usuarios) {this.usuarios = usuarios;}
}