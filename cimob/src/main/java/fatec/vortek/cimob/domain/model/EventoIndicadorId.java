package fatec.vortek.cimob.domain.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoIndicadorId implements Serializable {
    private Long eventoId;
    private Long indicadorId;
}
