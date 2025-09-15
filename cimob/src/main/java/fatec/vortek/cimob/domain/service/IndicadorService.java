package fatec.vortek.cimob.domain.service;

import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import java.util.List;

public interface IndicadorService {
    Indicador criar(Indicador indicador);
    Indicador atualizar(Indicador indicador);
    void deletar(Long id);
    Indicador buscarPorId(Long id);
    List<Indicador> listarTodos();
    void associarAEvento(Long indicadorId, Long eventoId);
    void desassociarDeEvento(Long indicadorId, Long eventoId);
    List<Evento> listarEventos(Long indicadorId);
}
