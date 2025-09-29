package fatec.vortek.cimob.presentation.dto.response;

import fatec.vortek.cimob.domain.enums.IndicadorMnemonico;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorResponseDTO {
    private Long indicadorId;
    private String nome;
    private Double valor;
    private IndicadorMnemonico mnemonico;
    private String descricao;
    private Long usuarioId;
}
