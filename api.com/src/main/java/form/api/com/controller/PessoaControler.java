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

    /**
     * Controle responsavel de buscar todas as pessoas
     * @return
     */
    @GetMapping("/pessoa")
    public ResponseEntity<CustomResponse> BuscarPessoa(){
        //consulta todos os usuarios
        return pessoaService.pegarTodosUsuario();
    }

    /**
     * Controle responsavel por salvar uma pessoa
     * @param pessoa
     * @return
     */
    @PostMapping("/pessoa")
    public ResponseEntity<CustomResponse> salvarPessoa(@RequestBody @Valid PessoaDTO pessoa){
            //Cria o usuario na base de dados
        return pessoaService.criarUsuario(pessoa);
    }

    /**
     * Controler resposnavel por buscar Pessoa pelo ID
     * @param id
     * @return
     */
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> BuscarPessoaID(@PathVariable @Pattern(regexp = "[0-9]+") String id){

        //consulta todos os usuarios
        return pessoaService.buscarPessoaID(id);

    }

    /**
     * Controler responsavel por editar uma pessoa
     * @param pessoaDTO
     * @param id
     * @return
     */
    @PutMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> editarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO,
                                                       @PathVariable @Pattern(regexp = "[0-9]+") String id){
        //consulta todos os usuarios
        return pessoaService.updatePessoa(pessoaDTO, id);

    }

    /**
     * Controler resposnavel por deletar uma pessoa
     * @param id
     * @return
     */
    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<CustomResponse> deletarPessoa(
            @PathVariable @Pattern(regexp = "[0-9]+") String id){
        //consulta todos os usuarios
        return pessoaService.deletarPessoa(id);

    }

    /**
     * Controler resposnavel por realizar o filtro de pesquissa
     * @param filtro
     * @return
     */
    @GetMapping("/pessoa/filtro")
    public ResponseEntity<CustomResponse> BuscarPessoaFiltroId(@Param("filtro") String filtro){

        if (filtro.isEmpty()){
            return pessoaService.pegarTodosUsuario();
        }
        return pessoaService.BuscarPessoaFiltroId(filtro);
    }



}
