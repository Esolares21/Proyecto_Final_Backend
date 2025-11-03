package umg.edu.sistema_eventos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umg.edu.sistema_eventos.dto.ActualizarAsistenciaDTO;
import umg.edu.sistema_eventos.dto.EntradaRequestDTO;
import umg.edu.sistema_eventos.model.Cliente;
import umg.edu.sistema_eventos.model.Entrada;
import umg.edu.sistema_eventos.model.Evento;
import umg.edu.sistema_eventos.model.Pago;
import umg.edu.sistema_eventos.service.ClienteService;
import umg.edu.sistema_eventos.service.EntradaService;
import umg.edu.sistema_eventos.service.EventoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/entradas")
@CrossOrigin(origins = "http://localhost:8080")
public class EntradaController {

    private final EntradaService entradaService;
    private final ClienteService clienteService;
    private final EventoService eventoService;

    @Autowired
    public EntradaController(EntradaService entradaService, ClienteService clienteService, EventoService eventoService) {
        this.entradaService = entradaService;
        this.clienteService = clienteService;
        this.eventoService = eventoService;
    }

    // POST: Solo ADMIN puede crear entradas (transacción de venta)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> crearEntrada(@Valid @RequestBody EntradaRequestDTO entradaDTO) {
        // 1. Verificar si existen las entidades relacionadas
        Cliente cliente = clienteService.obtenerClientePorId(entradaDTO.getClienteId())
                .orElse(null);
        Evento evento = eventoService.obtenerEventoPorId(entradaDTO.getEventoId())
                .orElse(null);

        if (cliente == null || evento == null) {
            return new ResponseEntity<>("Cliente o Evento no encontrado.", HttpStatus.NOT_FOUND);
        }

        // 2. Crear la entidad Pago
        Pago nuevoPago = new Pago();
        nuevoPago.setMonto(entradaDTO.getMontoPago());
        nuevoPago.setMetodoPago(entradaDTO.getMetodoPago());
        nuevoPago.setEstado("Completado");
        nuevoPago.setFechaPago(LocalDateTime.now());

        // 3. Crear la Entidad Entrada y establecer las relaciones
        Entrada nuevaEntrada = new Entrada();
        nuevaEntrada.setCliente(cliente);
        nuevaEntrada.setEvento(evento);
        nuevaEntrada.setPago(nuevoPago);

        nuevaEntrada.setPrecioVenta(entradaDTO.getPrecioVenta());
        nuevaEntrada.setFechaCompra(LocalDateTime.now());
        nuevaEntrada.setAsistio(false);

        // 4. Guardar la Entrada
        Entrada entradaGuardada = entradaService.guardarEntrada(nuevaEntrada);

        return new ResponseEntity<>(entradaGuardada, HttpStatus.CREATED);
    }

    // GET (All): Cualquier usuario autenticado puede listar
    @GetMapping
    public ResponseEntity<List<Entrada>> obtenerTodasLasEntradas() {
        List<Entrada> entradas = entradaService.obtenerTodasLasEntradas();
        return ResponseEntity.ok(entradas);
    }

    // GET (ID): Cualquier usuario autenticado puede leer por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrada> obtenerEntradaPorId(@PathVariable Long id) {
        return entradaService.obtenerEntradaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Solo ADMIN puede actualizar el estado de asistencia
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Entrada> actualizarEstadoAsistencia(@PathVariable Long id, @RequestBody ActualizarAsistenciaDTO asistenciaDTO) {

        // 1. Buscar la entrada existente
        return entradaService.obtenerEntradaPorId(id)
                .map(entradaExistente -> {

                    // 2. Solo actualizamos el campo 'asistio' usando el DTO
                    entradaExistente.setAsistio(asistenciaDTO.getAsistio());

                    // 3. Guardar la entrada actualizada
                    Entrada entradaActualizada = entradaService.guardarEntrada(entradaExistente);
                    return new ResponseEntity<>(entradaActualizada, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}