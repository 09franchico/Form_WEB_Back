package form.api.com.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class UsuarioDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String imagem;
    private EnderecoDTO endereco;

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco=" + endereco +
                '}';
    }
}
