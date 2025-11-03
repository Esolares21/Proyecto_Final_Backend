package umg.edu.sistema_eventos.dto;

// DTO para mostrar el resultado del reporte de asistencia
public class ReporteAsistenciaDTO {

    private Long eventoId;
    private String nombreEvento;
    private Long entradasVendidas;
    private Long asistentes;
    private Double porcentajeAsistencia;

    public ReporteAsistenciaDTO(Long eventoId, String nombreEvento, Long entradasVendidas, Long asistentes, Double porcentajeAsistencia) {
        this.eventoId = eventoId;
        this.nombreEvento = nombreEvento;
        this.entradasVendidas = entradasVendidas;
        this.asistentes = asistentes;
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    // --- Getters y Setters ---

    public Long getEventoId() {return eventoId;}
    public void setEventoId(Long eventoId) {this.eventoId = eventoId;}
    public String getNombreEvento() {return nombreEvento;}
    public void setNombreEvento(String nombreEvento) {this.nombreEvento = nombreEvento;}
    public Long getEntradasVendidas() {return entradasVendidas;}
    public void setEntradasVendidas(Long entradasVendidas) {this.entradasVendidas = entradasVendidas;}
    public Long getAsistentes() {return asistentes;}
    public void setAsistentes(Long asistentes) {this.asistentes = asistentes;}
    public Double getPorcentajeAsistencia() {return porcentajeAsistencia;}
    public void setPorcentajeAsistencia(Double porcentajeAsistencia) {this.porcentajeAsistencia = porcentajeAsistencia;}
}