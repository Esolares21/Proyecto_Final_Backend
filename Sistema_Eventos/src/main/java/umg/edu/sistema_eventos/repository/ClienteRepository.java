package umg.edu.sistema_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umg.edu.sistema_eventos.model.Cliente;

// Hereda todos los métodos CRUD para Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}