package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadarResponseDTO {
    private String radarId;
    private Long regiaoId;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private Integer velocidadePermitida;
}
