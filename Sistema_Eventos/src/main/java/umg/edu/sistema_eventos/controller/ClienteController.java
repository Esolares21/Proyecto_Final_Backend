package umg.edu.sistema_eventos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umg.edu.sistema_eventos.model.Cliente;
import umg.edu.sistema_eventos.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // POST: Solo ADMIN puede crear clientes
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // GET (All): Cualquier usuario autenticado puede listar
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    // GET (ID): Cualquier usuario autenticado puede leer por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Solo ADMIN puede actualizar clientes
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente detallesCliente) {
        return clienteService.obtenerClientePorId(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(detallesCliente.getNombre());
                    clienteExistente.setApellido(detallesCliente.getApellido());
                    clienteExistente.setEmail(detallesCliente.getEmail());
                    clienteExistente.setTelefono(detallesCliente.getTelefono());

                    Cliente clienteActualizado = clienteService.guardarCliente(clienteExistente);
                    return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Solo ADMIN puede eliminar clientes
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (clienteService.obtenerClientePorId(id).isPresent()) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}