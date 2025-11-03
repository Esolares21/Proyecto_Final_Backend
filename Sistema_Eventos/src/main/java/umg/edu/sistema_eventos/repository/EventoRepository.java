package umg.edu.sistema_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umg.edu.sistema_eventos.model.Evento;


@Repository

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
