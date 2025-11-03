package umg.edu.sistema_eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.sistema_eventos.dto.ReporteIngresosDTO;
import umg.edu.sistema_eventos.repository.EntradaRepository;
import umg.edu.sistema_eventos.dto.ReporteAsistenciaDTO;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class ReporteService {

    private final EntradaRepository entradaRepository;

    @Autowired
    public ReporteService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    // Servicio para el reporte de ingresos
    public List<ReporteIngresosDTO> obtenerIngresosPorEvento() {
        return entradaRepository.generarReporteIngresosPorEvento();
    }

    public List<ReporteAsistenciaDTO> obtenerReporteAsistencia() {
        List<Object[]> resultados = entradaRepository.contarAsistenciaPorEvento();

        return resultados.stream()
                .map(fila -> {
                    Long eventoId = (Long) fila[0];
                    String nombreEvento = (String) fila[1];
                    Long entradasVendidas = (Long) fila[2];
                    Long asistentes = (Long) fila[3];

                    // Cálculo del porcentaje: (Asistentes / Entradas Vendidas) * 100
                    Double porcentaje = (entradasVendidas > 0)
                            ? (asistentes.doubleValue() / entradasVendidas.doubleValue()) * 100.0
                            : 0.0; // Evita división por cero

                    return new ReporteAsistenciaDTO(eventoId, nombreEvento, entradasVendidas, asistentes, porcentaje);
                })
                .collect(Collectors.toList());
    }
}