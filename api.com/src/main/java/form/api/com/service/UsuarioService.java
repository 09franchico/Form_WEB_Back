package form.api.com.service;

import form.api.com.service.dto.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {

    @Transactional
    public UsuarioDTO pegarTodosUsuario(){
        return null;
    }
}
