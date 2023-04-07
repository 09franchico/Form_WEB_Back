package form.api.com.controller;

import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.service.UsuarioService;
import form.api.com.service.dto.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class UsuarioControler {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public ResponseEntity<CustomResponse> BuscarUsusario(){

        //consulta todos os usuarios
        List<UsuarioDTO> user = usuarioService.pegarTodosUsuario();
        return new ResponseEntity<>(new CustomResponse(
                200,
                "Registro encontrado com sucesso!!",
                user),
                HttpStatus.CREATED);

    }

    @PostMapping("/usuario")
    public ResponseEntity<CustomResponse> salvarUsuario(@RequestBody UsuarioDTO usuario){

            log.info("dados do usuario {}",usuario);
            //Cria o usuario na base de dados
            UsuarioDTO user = usuarioService.criarUsuario(usuario);
            return new ResponseEntity<>(new CustomResponse(
                    201,
                    "Usuario criado com sucesso!!",
                    user),
                    HttpStatus.CREATED);

    }
}


//throw new CustomErrorException(
//        HttpStatus.BAD_REQUEST,
//        "Error no parametro",
//        usuario
