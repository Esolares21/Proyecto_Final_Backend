package umg.edu.sistema_eventos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import umg.edu.sistema_eventos.model.Ubicacion;
import umg.edu.sistema_eventos.service.UbicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubicaciones")
@CrossOrigin(origins = "http://localhost:8080")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Autowired
    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    // POST: Solo ADMIN puede crear ubicaciones
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Ubicacion> crearUbicacion(@Valid @RequestBody Ubicacion ubicacion) {
        Ubicacion nuevaUbicacion = ubicacionService.guardarUbicacion(ubicacion);
        return new ResponseEntity<>(nuevaUbicacion, HttpStatus.CREATED);
    }

    // GET (All): Cualquiera puede listar (Público)
    @GetMapping
    public ResponseEntity<List<Ubicacion>> obtenerTodasLasUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionService.obtenerTodasLasUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }

    // GET (ID): Cualquiera puede leer por ID (Público)
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> obtenerUbicacionPorId(@PathVariable Long id) {
        return ubicacionService.obtenerUbicacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}