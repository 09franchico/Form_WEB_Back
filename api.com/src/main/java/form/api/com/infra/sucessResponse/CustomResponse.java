package form.api.com.infra.sucessResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse {

    private int status;
    private String message;
    private Long total;
    private Object data;

    public CustomResponse (int status , String msg , Object data){
        this.status = status;
        this.message = msg;
        this.data = data;
    }
}

