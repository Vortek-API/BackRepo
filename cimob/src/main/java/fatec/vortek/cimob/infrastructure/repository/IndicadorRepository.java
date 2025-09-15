package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
}
