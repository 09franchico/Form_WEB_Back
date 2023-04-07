package form.api.com.controller;

import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.service.PessoaService;
import form.api.com.service.dto.PessoaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
@Slf4j
@Validated
public class UsuarioControler {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoa")
    public ResponseEntity<CustomResponse> BuscarPessoa(){
        //consulta todos os usuarios
        ResponseEntity<CustomResponse> pessoa = pessoaService.pegarTodosUsuario();
        return pessoa;
    }

    @PostMapping("/pessoa")
    public ResponseEntity<CustomResponse> salvarPessoa(@RequestBody PessoaDTO pessoa){
            //Cria o usuario na base de dados
        ResponseEntity<CustomResponse> ps = pessoaService.criarUsuario(pessoa);
        return ps;

    }
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> BuscarPessoaID(@PathVariable @Pattern(regexp = "[0-9]+") String id){

        //consulta todos os usuarios
        ResponseEntity<CustomResponse> pessoa = pessoaService.buscarPessoaID(id);
        return pessoa;

    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> editarPessoa(
            @RequestBody PessoaDTO pessoaDTO,
            @PathVariable @Pattern(regexp = "[0-9]+") String id){
        //consulta todos os usuarios
        ResponseEntity<CustomResponse> pessoa = pessoaService.updatePessoa(pessoaDTO, id);
        return pessoa;

    }
    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> deletarPessoa(
            @PathVariable @Pattern(regexp = "[0-9]+") String id){
        //consulta todos os usuarios
        ResponseEntity<CustomResponse> pessoa = pessoaService.deletarPessoa(id);
        return pessoa;

    }


}

