package fatec.vortek.cimob.presentation.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistroVelocidadeRequestDTO {
    private Long radarId;
    private String tipoVeiculo;
    private Integer velocidadeRegistrada;
    private LocalDateTime data;
}