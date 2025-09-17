package fatec.vortek.cimob.presentation.controller;

import fatec.vortek.cimob.domain.service.RegistroService;
import fatec.vortek.cimob.presentation.dto.request.RegistroRequestDTO;
import fatec.vortek.cimob.presentation.dto.response.RegistroResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @PostMapping
    public ResponseEntity<RegistroResponseDTO> criar(@RequestBody RegistroRequestDTO registroDTO) {
        RegistroResponseDTO novoRegistro = registroService.criarRegistro(registroDTO);
        return new ResponseEntity<>(novoRegistro, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(registroService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> listar() {
        return ResponseEntity.ok(registroService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroService.deletarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}