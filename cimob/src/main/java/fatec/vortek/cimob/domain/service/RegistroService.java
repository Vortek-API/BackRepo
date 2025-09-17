package fatec.vortek.cimob.domain.service;

import fatec.vortek.cimob.presentation.dto.request.RegistroRequestDTO;
import fatec.vortek.cimob.presentation.dto.response.RegistroResponseDTO;
import java.util.List;

public interface RegistroService {
    RegistroResponseDTO criarRegistro(RegistroRequestDTO registroDTO);
    RegistroResponseDTO buscarPorId(Long id);
    List<RegistroResponseDTO> listarTodos();
    void deletarRegistro(Long id);
}