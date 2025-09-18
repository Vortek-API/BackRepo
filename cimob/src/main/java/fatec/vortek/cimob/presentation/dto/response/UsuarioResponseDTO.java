package fatec.vortek.cimob.presentation.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Long usuarioId;
    private String cpf;
    private String nome;
    private String sobreNome;
    private String userName;
    private String email;
    private String cargo;
    private String deletado;
}
