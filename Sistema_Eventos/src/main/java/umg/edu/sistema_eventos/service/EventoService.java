package umg.edu.sistema_eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.sistema_eventos.model.Evento;
import umg.edu.sistema_eventos.repository.EventoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    // Inyección de dependencia del repositorio
    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // CREATE / UPDATE: Guardar o actualizar un evento
    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    // READ: Obtener todos los eventos
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    // READ: Obtener un evento por ID
    public Optional<Evento> obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // DELETE: Eliminar un evento por ID
    public void eliminarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}