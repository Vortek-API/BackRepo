package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.model.EventoIndicador;
import fatec.vortek.cimob.domain.model.EventoIndicadorId;
import fatec.vortek.cimob.domain.service.EventoIndicadorService;
import fatec.vortek.cimob.infrastructure.repository.EventoIndicadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoIndicadorServiceImpl implements EventoIndicadorService {

    private final EventoIndicadorRepository repository;

    @Override
    public EventoIndicador associar(EventoIndicador eventoIndicador) {
        return repository.save(eventoIndicador);
    }

    @Override
    public void desassociar(EventoIndicadorId id) {
        repository.deleteById(id);
    }

    @Override
    public List<EventoIndicador> listarTodos() {
        return repository.findAll();
    }
}
