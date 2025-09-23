package fatec.vortek.cimob.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Radar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Radar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "radarId")
    private String radarId;

    @ManyToOne
    @JoinColumn(name = "regiaoId", nullable = false)
    private Regiao regiao;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(length = 200)
    private String endereco;

    @Column(name = "velocidadePermitida", nullable = false)
    private Integer velocidadePermitida;

    @Column(length = 1, nullable = false)
    private String deletado = "N";
} 