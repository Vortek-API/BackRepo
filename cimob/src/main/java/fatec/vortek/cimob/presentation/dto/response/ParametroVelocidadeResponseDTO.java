package fatec.vortek.cimob.presentation.dto.response;

import fatec.vortek.cimob.domain.model.ParametroVelocidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametroVelocidadeResponseDTO {
    private String tipoVeiculo;
    private Double velocidadeMediaKmh;

    public ParametroVelocidadeResponseDTO(ParametroVelocidade parametro) {
        if (parametro.getTipoVeiculo() != null) {
            this.tipoVeiculo = parametro.getTipoVeiculo().getNome();
        }
        this.velocidadeMediaKmh = parametro.getVelocidadeMediaKmh();
    }
    
}
