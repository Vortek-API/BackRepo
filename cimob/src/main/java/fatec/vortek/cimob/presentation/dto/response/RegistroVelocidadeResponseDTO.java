package fatec.vortek.cimob.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegistroVelocidadeResponseDTO {
    private Long registroVelocidadeId;
    private String radarId;
    private String tipoVeiculo;
    private Integer velocidadeRegistrada;
    private LocalDateTime data;
}