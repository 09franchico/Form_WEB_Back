package form.api.com.service;

import form.api.com.domain.Usuario;
import form.api.com.repository.UsuarioRepository;
import form.api.com.service.dto.UsuarioDTO;
import form.api.com.service.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public List<UsuarioDTO> pegarTodosUsuario(){

       List<Usuario> user = usuarioRepository.findAll();

       log.info("--------------------> usuario{}",user.get(0).getEndereco().getBairro());

       List<UsuarioDTO> ususarioTD = usuarioMapper.usuarioToUsuarioDTO(user);


       return ususarioTD;

    }

}
