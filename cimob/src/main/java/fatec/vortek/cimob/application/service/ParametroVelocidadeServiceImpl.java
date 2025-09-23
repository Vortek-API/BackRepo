package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.model.ParametroVelocidade;
import fatec.vortek.cimob.domain.service.ParametroVelocidadeService;
import fatec.vortek.cimob.infrastructure.repository.ParametroVelocidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametroVelocidadeServiceImpl implements ParametroVelocidadeService{

    @Autowired
    private ParametroVelocidadeRepository parametroVelocidadeRepository;

    @Override
    public List<ParametroVelocidade> listarTodos() {
        return parametroVelocidadeRepository.findAll();
    }
    
}
