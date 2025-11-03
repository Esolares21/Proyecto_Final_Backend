package umg.edu.sistema_eventos.dto;

import jakarta.validation.constraints.NotNull;

// DTO para manejar la actualización del estado de asistencia
public class ActualizarAsistenciaDTO {

    @NotNull(message = "El estado de asistencia es obligatorio")
    private Boolean asistio;

    // --- Getters y Setters ---

    public Boolean getAsistio() {return asistio;}
    public void setAsistio(Boolean asistio) {this.asistio = asistio;}
}