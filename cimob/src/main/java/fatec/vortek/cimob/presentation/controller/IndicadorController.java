package fatec.vortek.cimob.presentation.controller;

import fatec.vortek.cimob.application.service.IndicadorServiceImpl;
import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import fatec.vortek.cimob.presentation.dto.request.IndicadorRequestDTO;
import fatec.vortek.cimob.presentation.dto.response.IndicadorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/indicadores")
@RequiredArgsConstructor
@Tag(name = "Indicadores")
public class IndicadorController {

    private final IndicadorServiceImpl service;

    @PostMapping
    public ResponseEntity<IndicadorResponseDTO> criar(@RequestBody IndicadorRequestDTO dto) {
        Indicador i = Indicador.builder()
                .nome(dto.getNome())
                .valor(dto.getValor())
                .descricao(dto.getDescricao())
                .build();
        i = service.criar(i);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new IndicadorResponseDTO(i.getIndicadorId(), i.getNome(), i.getValor(), i.getDescricao(), null));
    }

    @GetMapping
    public ResponseEntity<List<IndicadorResponseDTO>> listar() {
        List<IndicadorResponseDTO> list = service.listarTodos().stream()
                .filter(i -> !"S".equals(i.getDeletado()))
                .map(i -> new IndicadorResponseDTO(i.getIndicadorId(), i.getNome(), i.getValor(), i.getDescricao(), null))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{indicadorId}/associarEvento/{eventoId}")
    public ResponseEntity<Void> associarAEvento(@PathVariable Long indicadorId, @PathVariable Long eventoId) {
        service.associarAEvento(indicadorId, eventoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{indicadorId}/desassociarEvento/{eventoId}")
    public ResponseEntity<Void> desassociarDeEvento(@PathVariable Long indicadorId, @PathVariable Long eventoId) {
        service.desassociarDeEvento(indicadorId, eventoId);
        return ResponseEntity.noContent().build();
    }
}
