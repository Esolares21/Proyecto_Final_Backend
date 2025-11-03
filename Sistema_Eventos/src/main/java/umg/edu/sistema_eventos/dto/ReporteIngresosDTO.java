package umg.edu.sistema_eventos.dto;

import java.math.BigDecimal;

// DTO para mostrar el resultado del reporte de ingresos
public class ReporteIngresosDTO {

    private Long eventoId;
    private String nombreEvento;
    private BigDecimal totalRecaudado;
    private Long totalEntradasVendidas;

    public ReporteIngresosDTO(Long eventoId, String nombreEvento, BigDecimal totalRecaudado, Long totalEntradasVendidas) {
        this.eventoId = eventoId;
        this.nombreEvento = nombreEvento;
        this.totalRecaudado = totalRecaudado;
        this.totalEntradasVendidas = totalEntradasVendidas;
    }

    // --- Getters y Setters ---

    public Long getEventoId() {return eventoId;}
    public void setEventoId(Long eventoId) {this.eventoId = eventoId;}
    public String getNombreEvento() {return nombreEvento;}
    public void setNombreEvento(String nombreEvento) {this.nombreEvento = nombreEvento;}
    public BigDecimal getTotalRecaudado() {return totalRecaudado;}
    public void setTotalRecaudado(BigDecimal totalRecaudado) {this.totalRecaudado = totalRecaudado;}
    public Long getTotalEntradasVendidas() {return totalEntradasVendidas;}
    public void setTotalEntradasVendidas(Long totalEntradasVendidas) {this.totalEntradasVendidas = totalEntradasVendidas;}
}