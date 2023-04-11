package form.api.com.controller;

import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.service.PessoaService;
import form.api.com.service.dto.PessoaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api")
@Slf4j
@Validated
public class PessoaControler {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoa")
    public ResponseEntity<CustomResponse> BuscarPessoa(){
        //consulta todos os usuarios
        ResponseEntity<CustomResponse> pessoa = pessoaService.pegarTodosUsuario();
        return pessoa;
    }

    @PostMapping("/pessoa")
    public ResponseEntity<CustomResponse> salvarPessoa(@RequestBody @Valid PessoaDTO pessoa){
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
    public ResponseEntity<CustomResponse> editarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO,
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

    @GetMapping("/pessoa/filtro")
    public ResponseEntity<CustomResponse> BuscarPessoaFiltroId(@Param("filtro") String filtro){

        if (filtro.isEmpty()){
            ResponseEntity<CustomResponse> pessoa = pessoaService.pegarTodosUsuario();
            return pessoa;
        }
        ResponseEntity<CustomResponse> pessoa = pessoaService.BuscarPessoaFiltroId(filtro);
        return pessoa;

    }



}
