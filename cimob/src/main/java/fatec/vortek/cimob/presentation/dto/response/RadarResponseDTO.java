package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadarResponseDTO {
    private Long radarId;
    private Long regiaoId;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private Integer velocidadePermitida;
}
