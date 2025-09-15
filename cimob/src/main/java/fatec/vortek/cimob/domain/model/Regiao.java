package fatec.vortek.cimob.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Regiao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regiaoId;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 1, nullable = false)
    private String deletado = "N";
}