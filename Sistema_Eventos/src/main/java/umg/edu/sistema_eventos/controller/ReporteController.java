package umg.edu.sistema_eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umg.edu.sistema_eventos.dto.ReporteIngresosDTO;
import umg.edu.sistema_eventos.service.ReporteService;
import umg.edu.sistema_eventos.dto.ReporteAsistenciaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@CrossOrigin(origins = "http://localhost:8080")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // Endpoint: GET /api/v1/reportes/ingresos-eventos
    @GetMapping("/ingresos-eventos")
    public ResponseEntity<List<ReporteIngresosDTO>> generarReporteIngresos() {
        List<ReporteIngresosDTO> reporte = reporteService.obtenerIngresosPorEvento();
        return ResponseEntity.ok(reporte);
    }

    // Endpoint: GET /api/v1/reportes/asistencia-eventos
    @GetMapping("/asistencia-eventos")
    public ResponseEntity<List<ReporteAsistenciaDTO>> generarReporteAsistencia() {
        List<ReporteAsistenciaDTO> reporte = reporteService.obtenerReporteAsistencia();
        return ResponseEntity.ok(reporte);
    }
}