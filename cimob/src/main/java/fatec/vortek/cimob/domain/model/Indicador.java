package fatec.vortek.cimob.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Indicador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indicadorId")
    private Long indicadorId;

    @Column(nullable = false, length = 100)
    private String nome;

    private Double valor;

    @Column(length = 255)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @Column(length = 1, nullable = false)
    private String deletado = "N";

    @ManyToMany(mappedBy = "indicadores")
    private List<Evento> eventos;
}
