package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import fatec.vortek.cimob.domain.service.EventoService;
import fatec.vortek.cimob.infrastructure.repository.EventoRepository;
import fatec.vortek.cimob.infrastructure.repository.IndicadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository repository;
    private final IndicadorRepository indicadorRepository;

    @Override
    public Evento criar(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public Evento atualizar(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public void deletar(Long id) {
        Evento e = repository.findById(id).orElseThrow();
        e.setDeletado("S");
        repository.save(e);
    }

    @Override
    public Evento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Evento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void associarIndicador(Long eventoId, Long indicadorId) {
        Evento e = repository.findById(eventoId).orElseThrow();
        Indicador i = indicadorRepository.findById(indicadorId).orElseThrow();
        e.getIndicadores().add(i);
        repository.save(e);
    }

    @Override
    public void desassociarIndicador(Long eventoId, Long indicadorId) {
        Evento e = repository.findById(eventoId).orElseThrow();
        Indicador i = indicadorRepository.findById(indicadorId).orElseThrow();
        e.getIndicadores().remove(i);
        repository.save(e);
    }

    @Override
    public List<Indicador> listarIndicadores(Long eventoId) {
        Evento e = repository.findById(eventoId).orElseThrow();
        return e.getIndicadores();
    }
}
