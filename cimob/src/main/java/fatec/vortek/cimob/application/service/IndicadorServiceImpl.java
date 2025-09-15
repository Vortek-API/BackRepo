package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import fatec.vortek.cimob.domain.service.IndicadorService;
import fatec.vortek.cimob.infrastructure.repository.IndicadorRepository;
import fatec.vortek.cimob.infrastructure.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicadorServiceImpl implements IndicadorService {

    private final IndicadorRepository repository;
    private final EventoRepository eventoRepository;

    @Override
    public Indicador criar(Indicador indicador) {
        return repository.save(indicador);
    }

    @Override
    public Indicador atualizar(Indicador indicador) {
        return repository.save(indicador);
    }

    @Override
    public void deletar(Long id) {
        Indicador i = repository.findById(id).orElseThrow();
        i.setDeletado("S");
        repository.save(i);
    }

    @Override
    public Indicador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Indicador> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void associarAEvento(Long indicadorId, Long eventoId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        Evento e = eventoRepository.findById(eventoId).orElseThrow();
        i.getEventos().add(e);
        repository.save(i);
    }

    @Override
    public void desassociarDeEvento(Long indicadorId, Long eventoId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        Evento e = eventoRepository.findById(eventoId).orElseThrow();
        i.getEventos().remove(e);
        repository.save(i);
    }

    @Override
    public List<Evento> listarEventos(Long indicadorId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        return i.getEventos();
    }
}
