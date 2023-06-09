package form.api.com.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotNull
    private Integer numero;
    @NotBlank
    private String cep;

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "id=" + id +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                '}';
    }
}
