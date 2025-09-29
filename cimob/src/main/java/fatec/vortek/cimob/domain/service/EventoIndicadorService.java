package fatec.vortek.cimob.domain.service;

import fatec.vortek.cimob.domain.model.EventoIndicador;
import fatec.vortek.cimob.domain.model.EventoIndicadorId;

import java.util.List;

public interface EventoIndicadorService {
    EventoIndicador associar(EventoIndicador eventoIndicador);
    void desassociar(EventoIndicadorId id);
    List<EventoIndicador> listarTodos();
}
