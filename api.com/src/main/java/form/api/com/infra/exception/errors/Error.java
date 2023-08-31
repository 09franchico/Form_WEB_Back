package form.api.com.infra.exception.errors;

public enum Error {
    ERRO_0("Registro não encontrado"),
    ERRO_1("Registro não editado"),
    ERRO_2("Registro não deletado.")
    ;
    private String msg;

    Error(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
