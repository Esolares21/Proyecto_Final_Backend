package umg.edu.sistema_eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.sistema_eventos.model.Cliente;
import umg.edu.sistema_eventos.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // CREATE / UPDATE
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // READ ALL
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    // READ BY ID
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    // DELETE
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}