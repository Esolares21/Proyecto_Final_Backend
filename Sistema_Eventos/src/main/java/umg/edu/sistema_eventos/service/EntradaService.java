package umg.edu.sistema_eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.sistema_eventos.model.Entrada;
import umg.edu.sistema_eventos.repository.EntradaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;

    @Autowired
    public EntradaService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public Entrada guardarEntrada(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public List<Entrada> obtenerTodasLasEntradas() {
        return entradaRepository.findAll();
    }

    public Optional<Entrada> obtenerEntradaPorId(Long id) {
        return entradaRepository.findById(id);
    }

    public void eliminarEntrada(Long id) {
        entradaRepository.deleteById(id);
    }
}