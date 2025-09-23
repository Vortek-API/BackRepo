package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.ParametroVelocidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroVelocidadeRepository extends JpaRepository<ParametroVelocidade, Long> {

}
