package form.api.com.service.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String imagem;
    private EnderecoDTO endereco;

    @Override
    public String toString() {
        return "PessoaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", imagem='" + imagem + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
