package form.api.com.service;

import form.api.com.domain.Endereco;
import form.api.com.domain.Usuario;
import form.api.com.repository.EnderecoRepository;
import form.api.com.repository.UsuarioRepository;
import form.api.com.service.dto.UsuarioDTO;
import form.api.com.service.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public List<UsuarioDTO> pegarTodosUsuario(){
         List<Usuario> user = usuarioRepository.findAll();
         List<UsuarioDTO> ususarioTD = usuarioMapper.usuarioToUsuarioDTO(user);
       return ususarioTD;
    }

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO usuario){
        log.info("-----------------> {}",usuario);
       // Optional<Endereco> endereco = enderecoRepository.save();
        return null;

    }


}
