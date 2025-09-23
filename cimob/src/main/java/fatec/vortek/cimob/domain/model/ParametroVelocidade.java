package fatec.vortek.cimob.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "parametros_velocidade")
@Data
@EqualsAndHashCode(of = "id")
public class ParametroVelocidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tipo_veiculo_id", referencedColumnName = "id", unique = true)
    private TipoVeiculo tipoVeiculo;

    @Column(name = "velocidade_media_kmh", nullable = false)
    private Double velocidadeMediaKmh;
}