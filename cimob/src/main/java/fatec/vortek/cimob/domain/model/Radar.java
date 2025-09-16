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
    private Long radarId;

    @ManyToOne
    @JoinColumn(name = "regiaoId", nullable = false)
    private Regiao regiao;

    @Column(nullable = false, precision = 9, scale = 6)
    private Double latitude;

    @Column(nullable = false, precision = 9, scale = 6)
    private Double longitude;

    @Column(length = 200)
    private String endereco;

    @Column(nullable = false)
    private Integer velocidadePermitida;

    @Column(length = 1, nullable = false)
    private String deletado = "N";
} 