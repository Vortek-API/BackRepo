package fatec.vortek.cimob.presentation.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadarRequestDTO {
    private Long regiaoId;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private Integer velocidadePermitida;
}
