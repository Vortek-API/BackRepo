package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.model.Registro;
import fatec.vortek.cimob.domain.service.RegistroService;
import fatec.vortek.cimob.infrastructure.repository.RegistroRepository;
import fatec.vortek.cimob.presentation.dto.request.RegistroRequestDTO;
import fatec.vortek.cimob.presentation.dto.response.RegistroResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    private Registro toEntity(RegistroRequestDTO dto) {
        Registro registro = new Registro();
        registro.setDescricao(dto.getDescricao());
        registro.setValor(dto.getValor());
        registro.setDataRegistro(dto.getDataRegistro());
        return registro;
    }

    private RegistroResponseDTO toResponseDTO(Registro registro) {
        return new RegistroResponseDTO(
            registro.getId(),
            registro.getDescricao(),
            registro.getValor(),
            registro.getDataRegistro()
        );
    }

    @Override
    public RegistroResponseDTO criarRegistro(RegistroRequestDTO registroDTO) {
        Registro registro = toEntity(registroDTO);
        Registro registroSalvo = registroRepository.save(registro);
        return toResponseDTO(registroSalvo);
    }

    @Override
    public RegistroResponseDTO buscarPorId(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado com id: " + id));
        return toResponseDTO(registro);
    }

    @Override
    public List<RegistroResponseDTO> listarTodos() {
        return registroRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarRegistro(Long id) {
        if (!registroRepository.existsById(id)) {
            throw new RuntimeException("Registro não encontrado com id: " + id);
        }
        registroRepository.deleteById(id);
    }
}