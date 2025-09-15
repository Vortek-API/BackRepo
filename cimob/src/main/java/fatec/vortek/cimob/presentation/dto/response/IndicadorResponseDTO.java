package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorResponseDTO {
    private Long indicadorId;
    private String nome;
    private Double valor;
    private String descricao;
    private Long usuarioId;
}
