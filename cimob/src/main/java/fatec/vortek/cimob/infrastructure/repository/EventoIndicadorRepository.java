package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.EventoIndicador;
import fatec.vortek.cimob.domain.model.EventoIndicadorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoIndicadorRepository extends JpaRepository<EventoIndicador, EventoIndicadorId> {
}
