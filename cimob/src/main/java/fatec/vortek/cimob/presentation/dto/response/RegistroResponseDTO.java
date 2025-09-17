package fatec.vortek.cimob.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegistroResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataRegistro;
}