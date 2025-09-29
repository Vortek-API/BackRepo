package fatec.vortek.cimob.domain.service;

import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import fatec.vortek.cimob.presentation.dto.response.IndiceCriticoResponseDTO;

import java.util.List;

public interface IndicadorService {
    Indicador criar(Indicador indicador);
    Indicador atualizar(Indicador indicador);
    void deletar(Long id);
    Indicador buscarPorId(Long id);
    List<Indicador> listarTodos();
    List<Indicador> listarTodos(String dataInicial);
    List<Indicador> listarPorRegiao(Long regiaoId);
    List<Indicador> listarPorRegiao(Long regiaoId, String dataInicial);
    void associarAEvento(Long indicadorId, Long eventoId);
    void desassociarDeEvento(Long indicadorId, Long eventoId);
    List<Evento> listarEventos(Long indicadorId);
    java.util.List<IndiceCriticoResponseDTO> listarTopExcessosVelocidade(Long regiaoId, String dataInicial);
}
