package umg.edu.sistema_eventos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umg.edu.sistema_eventos.model.Pago;
import umg.edu.sistema_eventos.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "http://localhost:8080")
public class PagoController {

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // POST: Solo ADMIN puede crear pagos
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Pago> crearPago(@Valid @RequestBody Pago pago) {
        Pago nuevoPago = pagoService.guardarPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    // GET (All): Listar todos los pagos (para reportes, solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Pago>> obtenerTodosLosPagos() {
        List<Pago> pagos = pagoService.obtenerTodosLosPagos();
        return ResponseEntity.ok(pagos);
    }

    // GET (ID): Leer pago por ID (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        return pagoService.obtenerPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Solo ADMIN puede actualizar pagos
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @Valid @RequestBody Pago detallesPago) {
        return pagoService.obtenerPagoPorId(id)
                .map(pagoExistente -> {
                    pagoExistente.setMonto(detallesPago.getMonto());
                    pagoExistente.setFechaPago(detallesPago.getFechaPago());
                    pagoExistente.setMetodoPago(detallesPago.getMetodoPago());
                    pagoExistente.setEstado(detallesPago.getEstado());

                    Pago pagoActualizado = pagoService.guardarPago(pagoExistente);
                    return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Solo ADMIN puede eliminar pagos
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        if (pagoService.obtenerPagoPorId(id).isPresent()) {
            pagoService.eliminarPago(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}