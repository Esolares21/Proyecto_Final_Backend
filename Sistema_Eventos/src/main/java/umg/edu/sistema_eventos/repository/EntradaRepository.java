package umg.edu.sistema_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umg.edu.sistema_eventos.dto.ReporteIngresosDTO;
import umg.edu.sistema_eventos.model.Entrada;

import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    // --- CONSULTA PARA REPORTE DE INGRESOS POR EVENTO (JPQL) ---
    @Query("SELECT new umg.edu.sistema_eventos.dto.ReporteIngresosDTO(" +
            "e.evento.id, e.evento.nombre, SUM(e.pago.monto), COUNT(e.id)) " +
            "FROM Entrada e " +
            "GROUP BY e.evento.id, e.evento.nombre")
    List<ReporteIngresosDTO> generarReporteIngresosPorEvento();

    // --- CONSULTA PARA REPORTE DE ASISTENCIA (JPQL) ---
    @Query("SELECT e.evento.id, e.evento.nombre, COUNT(e.id), SUM(CASE WHEN e.asistio = true THEN 1 ELSE 0 END) " +
            "FROM Entrada e " +
            "GROUP BY e.evento.id, e.evento.nombre")
    List<Object[]> contarAsistenciaPorEvento();

}