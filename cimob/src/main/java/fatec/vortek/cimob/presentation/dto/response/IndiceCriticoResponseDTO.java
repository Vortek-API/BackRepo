package fatec.vortek.cimob.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndiceCriticoResponseDTO {
    private String endereco;
    private Integer velocidadePermitida;
    private Integer velocidadeRegistrada;
    private Long regiaoId;
    private String regiaoNome;
    private String dataHora;
}


