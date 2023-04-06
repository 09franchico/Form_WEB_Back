package form.api.com.infra.sucessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse {

    private int status;
    private String message;
    private Object data;
}

