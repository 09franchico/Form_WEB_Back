package form.api.com.controller;

import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.service.dto.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class usuarioControler {
    @PostMapping("/usuario")
    public ResponseEntity<CustomResponse> salvarUsusario(@RequestBody UsuarioDTO usuario){
        try {
            log.info("usuario aqui {}",usuario);
            UsuarioDTO us = new UsuarioDTO();
            us.setNome("Francisco");
            us.setDataNascimento(LocalDateTime.parse("09/02/1996"));

            return new ResponseEntity<>(new CustomResponse(
                    201,
                    "Numero de series criado com sucesso!!",
                    us),
                    HttpStatus.CREATED);

        }catch (NullPointerException e){
            throw new CustomErrorException(
                    HttpStatus.BAD_REQUEST,
                    "Error no parametro",
                    usuario
            );
        }

    }
}
