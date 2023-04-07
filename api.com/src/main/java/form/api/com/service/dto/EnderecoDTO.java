package form.api.com.service.dto;

public class EnderecoDTO {
    private String bairro;
    private String rua;
    private Integer numero;
    private String cep;

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                '}';
    }
}
