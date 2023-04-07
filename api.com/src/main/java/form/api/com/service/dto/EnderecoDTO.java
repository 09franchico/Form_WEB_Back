package form.api.com.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private Long id;
    private String bairro;
    private String rua;
    private Integer numero;
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
