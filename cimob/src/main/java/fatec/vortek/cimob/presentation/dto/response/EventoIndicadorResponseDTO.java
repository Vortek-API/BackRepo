package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoIndicadorResponseDTO {
    private Long eventoId;
    private Long indicadorId;
}
