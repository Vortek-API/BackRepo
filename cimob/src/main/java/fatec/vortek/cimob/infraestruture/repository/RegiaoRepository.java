package fatec.vortek.cimob.infraestruture.repository;

import fatec.vortek.cimob.domain.model.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
}
