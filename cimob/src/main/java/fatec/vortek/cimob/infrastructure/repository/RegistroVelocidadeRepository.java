package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.RegistroVelocidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroVelocidadeRepository extends JpaRepository<RegistroVelocidade, Long> {
}