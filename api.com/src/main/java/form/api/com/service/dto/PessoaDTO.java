package form.api.com.service.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    private String imagem;
    @NotNull
    @Valid
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
