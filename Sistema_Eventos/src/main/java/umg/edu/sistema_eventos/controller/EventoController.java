package umg.edu.sistema_eventos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umg.edu.sistema_eventos.model.Evento;
import umg.edu.sistema_eventos.service.EventoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
@CrossOrigin(origins = "http://localhost:8080")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // POST: Solo ADMIN puede crear eventos
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@Valid @RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardarEvento(evento);
        return new ResponseEntity<>(nuevoEvento, HttpStatus.CREATED);
    }

    // GET (All): Cualquiera puede listar (Público)
    @GetMapping
    public ResponseEntity<List<Evento>> obtenerTodosLosEventos() {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        return ResponseEntity.ok(eventos);
    }

    // GET (ID): Cualquiera puede leer por ID (Público)
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
        return eventoService.obtenerEventoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Solo ADMIN puede actualizar eventos
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id, @Valid @RequestBody Evento detallesEvento) {
        return eventoService.obtenerEventoPorId(id)
                .map(eventoExistente -> {
                    eventoExistente.setNombre(detallesEvento.getNombre());
                    eventoExistente.setFechaHora(detallesEvento.getFechaHora());
                    eventoExistente.setLugar(detallesEvento.getLugar());
                    eventoExistente.setPrecioEntrada(detallesEvento.getPrecioEntrada());

                    Evento eventoActualizado = eventoService.guardarEvento(eventoExistente);
                    return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Solo ADMIN puede eliminar eventos
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        if (eventoService.obtenerEventoPorId(id).isPresent()) {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}