package fatec.vortek.cimob.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal; // Mantenha BigDecimal, é uma boa prática

@Entity
@Data
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valor; // Recomendo manter BigDecimal ao invés de Double

    private LocalDateTime dataRegistro; // <-- ADICIONE ESTA LINHA DE VOLTA

}