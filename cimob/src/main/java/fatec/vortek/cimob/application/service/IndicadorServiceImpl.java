package fatec.vortek.cimob.application.service;

import fatec.vortek.cimob.domain.enums.IndicadorMnemonico;
import fatec.vortek.cimob.domain.model.Evento;
import fatec.vortek.cimob.domain.model.Indicador;
import fatec.vortek.cimob.domain.model.Regiao;
import fatec.vortek.cimob.domain.model.Radar;
import fatec.vortek.cimob.domain.model.RegistroVelocidade;
import fatec.vortek.cimob.domain.service.IndicadorService;
import fatec.vortek.cimob.domain.service.RegiaoService;
import fatec.vortek.cimob.infrastructure.repository.IndicadorRepository;
import fatec.vortek.cimob.infrastructure.repository.EventoRepository;
import fatec.vortek.cimob.infrastructure.repository.RadarRepository;
import fatec.vortek.cimob.infrastructure.repository.RegistroVelocidadeRepository;
import fatec.vortek.cimob.presentation.dto.response.IndiceCriticoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.Local;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class IndicadorServiceImpl implements IndicadorService {

    private final IndicadorRepository repository;
    private final EventoRepository eventoRepository;
    private final RegiaoService regiaoService;
    private final RadarRepository radarRepository;
    private final RegistroVelocidadeRepository registroVelocidadeRepository;
    private final RadarServiceImpl radarService;

    @Override
    @CacheEvict(value = "indicadores", allEntries = true)
    public Indicador criar(Indicador indicador) {
        return repository.save(indicador);
    }

    @Override
    @CacheEvict(value = "indicadores", allEntries = true)
    public Indicador atualizar(Indicador indicador) {
        return repository.save(indicador);
    }

    @Override
    @CacheEvict(value = "indicadores", allEntries = true)
    public void deletar(Long id) {
        Indicador i = repository.findById(id).orElseThrow();
        i.setDeletado("S");
        repository.save(i);
    }

    @Override
    @Cacheable(value = "indicadores", key = "'buscarPorId:' + #id", unless = "#result == null")
    public Indicador buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Indicador> listarTodos() {
        return listarTodos(null);
    }

    @Override
    @Cacheable(value = "indicadores", key = "'listarTodos:' + (#dataInicial != null ? #dataInicial : 'null')", unless = "#result == null || #result.isEmpty()")
    public List<Indicador> listarTodos(String dataInicial) {
        List<Indicador> indicadores = repository.findAll();
        indicadores.forEach(indicador -> {
            indicador = calcularValorIndicadores(indicador, dataInicial);
        });

        return indicadores;
    }

    @Override
    public List<Indicador> listarPorRegiao(Long regiaoId) {
        return listarPorRegiao(regiaoId, null);
    }

    @Override
    @Cacheable(value = "indicadores", key = "'listarPorRegiao:' + #regiaoId + ':' + (#dataInicial != null ? #dataInicial : 'null')", unless = "#result == null || #result.isEmpty()")
    public List<Indicador> listarPorRegiao(Long regiaoId, String dataInicial) {
        Regiao regiao = regiaoService.buscarPorId(regiaoId);
        if (regiao == null) {
            throw new RuntimeException("Região não encontrada com ID: " + regiaoId);
        }

        List<Indicador> todosIndicadores = repository.findAll().stream()
                .filter(indicador -> !"S".equals(indicador.getDeletado()))
                .collect(Collectors.toList());

        todosIndicadores.forEach(indicador -> {
            calcularValorIndicadoresPorRegiao(indicador, regiaoId, dataInicial);
        });

        return todosIndicadores;
    }

    @Override
    @CacheEvict(value = "indicadores", allEntries = true)
    public void associarAEvento(Long indicadorId, Long eventoId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        Evento e = eventoRepository.findById(eventoId).orElseThrow();
        i.getEventos().add(e);
        repository.save(i);
    }

    @Override
    @CacheEvict(value = "indicadores", allEntries = true)
    public void desassociarDeEvento(Long indicadorId, Long eventoId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        Evento e = eventoRepository.findById(eventoId).orElseThrow();
        i.getEventos().remove(e);
        repository.save(i);
    }

    @Override
    @Cacheable(value = "indicadores", key = "'listarEventos:' + #indicadorId", unless = "#result == null || #result.isEmpty()")
    public List<Evento> listarEventos(Long indicadorId) {
        Indicador i = repository.findById(indicadorId).orElseThrow();
        return i.getEventos();
    }

    @Override
    @Cacheable(value = "topExcessosVelocidade", key = "'topExcessos:' + #regiaoId + ':' + (#dataInicial != null ? #dataInicial : 'null')", unless = "#result == null || #result.isEmpty()")
    public java.util.List<IndiceCriticoResponseDTO> listarTopExcessosVelocidade(Long regiaoId, String dataInicial) {
        List<RegistroVelocidade> registros = buscarRegistrosPorRegiao(regiaoId, dataInicial);

        class Agg { double somaVel; int cont; LocalDateTime minInf; LocalDateTime maxInf; Integer velPerm; Long regiaoId; String regiaoNome; String endereco; }

        Map<String, Agg> mapa = new java.util.HashMap<>();

        registros.stream()
                .filter(r -> r.getVelocidadeRegistrada() != null && r.getRadar() != null && r.getRadar().getVelocidadePermitida() != null && r.getData() != null)
                .forEach(r -> {
                    Radar rad = r.getRadar();
                    String endereco = rad.getEndereco() != null ? rad.getEndereco() : "";
                    Integer velPerm = rad.getVelocidadePermitida();
                    Long regIdVal = rad.getRegiao() != null ? rad.getRegiao().getRegiaoId() : null;
                    String regNomeVal = rad.getRegiao() != null ? rad.getRegiao().getNome() : null;
                    String key = endereco + "|" + velPerm + "|" + regIdVal + "|" + regNomeVal;
                    Agg agg = mapa.computeIfAbsent(key, k -> { Agg a = new Agg(); a.velPerm = velPerm; a.regiaoId = regIdVal; a.regiaoNome = regNomeVal; a.endereco = endereco; return a; });
                    if (r.getVelocidadeRegistrada() > velPerm) {
                        agg.somaVel += r.getVelocidadeRegistrada();
                        agg.cont += 1;
                        LocalDateTime dt = r.getData();
                        if (agg.minInf == null || dt.isBefore(agg.minInf)) agg.minInf = dt;
                        if (agg.maxInf == null || dt.isAfter(agg.maxInf)) agg.maxInf = dt;
                    }
                });

        DateTimeFormatter horaFmt = DateTimeFormatter.ofPattern("HH'h'mm");

        return mapa.values().stream()
                .filter(a -> a.cont > 0)
                .map(a -> {
                    double media = a.somaVel / a.cont;
                    double excessoMedio = media - a.velPerm;
                    String intervalo = (a.minInf != null && a.maxInf != null) ? ("das " + a.minInf.format(horaFmt) + " às " + a.maxInf.format(horaFmt)) : null;
                    return new Object[]{a.endereco, a.velPerm, media, a.regiaoId, a.regiaoNome, excessoMedio, intervalo};
                })
                .filter(arr -> ((Double) arr[5]) > 0)
                .sorted(Comparator.comparingDouble(o -> -((Double) ((Object[]) o)[5])))
                .limit(3)
                .map(arr -> IndiceCriticoResponseDTO.builder()
                        .endereco((String) arr[0])
                        .velocidadePermitida((Integer) arr[1])
                        .velocidadeRegistrada((int) Math.round((Double) arr[2]))
                        .regiaoId((Long) arr[3])
                        .regiaoNome((String) arr[4])
                        .dataHora((String) arr[6])
                        .build())
                .collect(Collectors.toList());
    }

    public Indicador calcularValorIndicadores(Indicador indicador) {
        return calcularValorIndicadores(indicador, null);
    }

    public Indicador calcularValorIndicadores(Indicador indicador, String dataInicial) {
        return calcularValorIndicadoresPorRegiao(indicador, null, dataInicial);
    }

    public Indicador calcularValorIndicadoresPorRegiao(Indicador indicador, Long regiaoId) {
        return calcularValorIndicadoresPorRegiao(indicador, regiaoId, null);
    }

    public Indicador calcularValorIndicadoresPorRegiao(Indicador indicador, Long regiaoId, String dataInicial) {
        if (indicador.getMnemonico() == IndicadorMnemonico.EXCESSO_VELOCIDADE) {
            indicador.setValor(calcularExcessoVelocidade(regiaoId, dataInicial));
        } else if (indicador.getMnemonico() == IndicadorMnemonico.VARIABILIDADE_VELOCIDADE) {
            indicador.setValor(calcularVariabilidadeVelocidade(regiaoId, dataInicial));
        } else if (indicador.getMnemonico() == IndicadorMnemonico.VEICULOS_LENTOS) {
            indicador.setValor(calcularVeiculosLentos(regiaoId, dataInicial));
        } else {
            indicador.setValor(0.0);
        }
        return indicador;
    }

    private Double calcularExcessoVelocidade(Long regiaoId) {
        return calcularExcessoVelocidade(regiaoId, null);
    }

    private Double calcularExcessoVelocidade(Long regiaoId, String dataInicial) {
        List<RegistroVelocidade> registros = buscarRegistrosPorRegiao(regiaoId, dataInicial);
        
        if (registros.isEmpty()) {
            return 1.0;
        }

        long totalRegistros = registros.size();
        long excessos = registros.stream()
                .filter(registro -> registro.getVelocidadeRegistrada() > registro.getRadar().getVelocidadePermitida())
                .count();

        double percentualExcessos = (double) excessos / totalRegistros * 100;

        if (percentualExcessos <= 10) {
            return 1.0;
        } else if (percentualExcessos <= 25) {
            return 2.0;
        } else {
            return 3.0;
        }
    }

    private Double calcularVariabilidadeVelocidade(Long regiaoId) {
        return calcularVariabilidadeVelocidade(regiaoId, null);
    }

    private Double calcularVariabilidadeVelocidade(Long regiaoId, String dataInicial) {
        List<RegistroVelocidade> registros = buscarRegistrosPorRegiao(regiaoId, dataInicial);
        
        if (registros.size() < 2) {
            return 1.0;
        }

        List<Integer> velocidades = registros.stream()
                .map(RegistroVelocidade::getVelocidadeRegistrada)
                .collect(Collectors.toList());

        double media = velocidades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double variancia = velocidades.stream()
                .mapToDouble(v -> Math.pow(v - media, 2))
                .average().orElse(0.0);

        double desvioPadrao = Math.sqrt(variancia);

        if (desvioPadrao <= 5) {
            return 1.0;
        } else if (desvioPadrao <= 15) {
            return 2.0;
        } else {
            return 3.0;
        }
    }

    private Double calcularVeiculosLentos(Long regiaoId) {
        return calcularVeiculosLentos(regiaoId, null);
    }

    private Double calcularVeiculosLentos(Long regiaoId, String dataInicial) {
        List<RegistroVelocidade> registros = buscarRegistrosPorRegiao(regiaoId, dataInicial);
        
        if (registros.isEmpty()) {
            return 1.0;
        }

        long totalRegistros = registros.size();
        long veiculosLentos = registros.stream()
                .filter(registro -> registro.getVelocidadeRegistrada() < registro.getRadar().getVelocidadePermitida() * 0.5)
                .count();

        double percentualVeiculosLentos = (double) veiculosLentos / totalRegistros * 100;

        if (percentualVeiculosLentos <= 5) {
            return 1.0;
        } else if (percentualVeiculosLentos <= 15) {
            return 2.0;
        } else {
            return 3.0;
        }
    }

    @Cacheable(value = "registrosVelocidade", key = "'registros:' + (#regiaoId != null ? #regiaoId : 'null') + ':' + (#dataInicial != null ? #dataInicial : 'null')", unless = "#result == null || #result.isEmpty()")
    private List<RegistroVelocidade> buscarRegistrosPorRegiao(Long regiaoId, String dataInicial) {
        LocalDateTime inicioDia;
        LocalDateTime fimDia;
        
        if (dataInicial != null) {
            fimDia = LocalDateTime.parse(dataInicial);
            inicioDia = fimDia.minusMinutes(5);
        } else {
            fimDia = LocalDateTime.now();
            inicioDia = fimDia.minusMinutes(5);
        }


        if (regiaoId == null) {
            return registroVelocidadeRepository.findByDiaInteiroAndDeletado(inicioDia, fimDia);
        } else {
            return registroVelocidadeRepository.findByDiaInteiroAndRegiaoAndDeletado(inicioDia, fimDia, regiaoId);
        }
    }
}
