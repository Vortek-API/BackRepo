package fatec.vortek.cimob.presentation.dto.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoRequestDTO {
    private String nome;
    private String descricao;
    private Long usuarioId;
    private Long indicadorPrincipalId;
    private List<Long> indicadoresIds; // para N:N
}
