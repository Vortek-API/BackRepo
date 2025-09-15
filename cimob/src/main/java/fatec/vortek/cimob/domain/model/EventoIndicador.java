package fatec.vortek.cimob.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EventoIndicador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(EventoIndicadorId.class)
public class EventoIndicador {

    @Id
    private Long eventoId;

    @Id
    private Long indicadorId;
}
