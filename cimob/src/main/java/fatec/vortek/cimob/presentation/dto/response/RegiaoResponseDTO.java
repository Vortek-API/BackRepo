package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegiaoResponseDTO {
    private Long regiaoId;
    private String nome;
}
