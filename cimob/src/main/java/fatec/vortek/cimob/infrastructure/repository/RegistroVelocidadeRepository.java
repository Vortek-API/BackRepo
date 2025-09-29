package fatec.vortek.cimob.infrastructure.repository;

import fatec.vortek.cimob.domain.model.RegistroVelocidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroVelocidadeRepository extends JpaRepository<RegistroVelocidade, Long> {
    
    @Query("SELECT r FROM RegistroVelocidade r WHERE r.deletado = 'N' AND r.data >= :inicioDia AND r.data <= :fimDia")
    List<RegistroVelocidade> findByDataBetweenAndDeletado(@Param("inicioDia") LocalDateTime inicioDia, 
                                                         @Param("fimDia") LocalDateTime fimDia);
    
    @Query("SELECT r FROM RegistroVelocidade r " +
           "JOIN r.radar rad " +
           "WHERE r.deletado = 'N' AND r.data >= :inicioDia AND r.data <= :fimDia " +
           "AND rad.regiao.regiaoId = :regiaoId AND rad.deletado = 'N'")
    List<RegistroVelocidade> findByDataBetweenAndRegiaoAndDeletado(@Param("inicioDia") LocalDateTime inicioDia,
                                                                   @Param("fimDia") LocalDateTime fimDia,
                                                                   @Param("regiaoId") Long regiaoId);

    @Query("SELECT r FROM RegistroVelocidade r WHERE r.deletado = 'N' AND r.data >= :inicioDia AND r.data <= :fimDia")
    List<RegistroVelocidade> findByDiaInteiroAndDeletado(@Param("inicioDia") LocalDateTime inicioDia,
                                                         @Param("fimDia") LocalDateTime fimDia);

    @Query("SELECT r FROM RegistroVelocidade r JOIN r.radar rad WHERE r.deletado = 'N' AND r.data >= :inicioDia AND r.data <= :fimDia AND rad.regiao.regiaoId = :regiaoId AND rad.deletado = 'N'")
    List<RegistroVelocidade> findByDiaInteiroAndRegiaoAndDeletado(@Param("inicioDia") LocalDateTime inicioDia,
                                                                  @Param("fimDia") LocalDateTime fimDia,
                                                                  @Param("regiaoId") Long regiaoId);
}