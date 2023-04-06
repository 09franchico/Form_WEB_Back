package form.api.com.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UsuarioDTO {
    private String nome;

    private LocalDateTime dataNascimento;

    private EnderecoDTO endereco;

}
