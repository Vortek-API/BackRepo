package fatec.vortek.cimob.presentation.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorRequestDTO {
    private String nome;
    private Double valor;
    private String descricao;
    private Long usuarioId;
}
