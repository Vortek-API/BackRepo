package fatec.vortek.cimob.presentation.controller;

import fatec.vortek.cimob.domain.model.ParametroVelocidade;
import fatec.vortek.cimob.domain.service.ParametroVelocidadeService;
import fatec.vortek.cimob.presentation.dto.response.ParametroVelocidadeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parametros-velocidade")
public class ParametroVelocidadeController {

    @Autowired
    private ParametroVelocidadeService parametroVelocidadeService;

    @GetMapping
    public ResponseEntity<List<ParametroVelocidadeResponseDTO>> listarTodos() {
        List<ParametroVelocidade> parametros = parametroVelocidadeService.listarTodos();
        List<ParametroVelocidadeResponseDTO> dtos = parametros.stream()
            .map(ParametroVelocidadeResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    
}
