package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    // Da para adicionar metodosd de busca customizada aqui
}