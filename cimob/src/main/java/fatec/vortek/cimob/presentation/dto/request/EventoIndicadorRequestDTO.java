package fatec.vortek.cimob.presentation.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoIndicadorRequestDTO {
    private Long eventoId;
    private Long indicadorId;
}
