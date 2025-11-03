package umg.edu.sistema_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.sistema_eventos.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
}
