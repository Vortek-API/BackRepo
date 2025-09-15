package fatec.vortek.cimob.domain.service;

import fatec.vortek.cimob.domain.model.Regiao;
import java.util.List;

public interface RegiaoService {
    Regiao criar(Regiao regiao);
    Regiao atualizar(Regiao regiao);
    void deletar(Long id);
    Regiao buscarPorId(Long id);
    List<Regiao> listarTodos();
}
