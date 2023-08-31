package form.api.com.infra.sucessResponse;

public enum Sucesso {
    SC_0("Registro encontrado com sucesso."),
    SC_00("Nenhum registro encontrado."),
    SC_1("Registro criado com sucesso."),
    SC_2("Registro Editado com sucesso."),
    SC_3("Registro Deletado com sucesso.")
    ;

    private String msg;

    Sucesso(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
