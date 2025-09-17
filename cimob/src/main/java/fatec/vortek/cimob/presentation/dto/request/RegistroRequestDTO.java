package fatec.vortek.cimob.presentation.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RegistroRequestDTO {
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataRegistro;
}