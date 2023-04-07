package form.api.com.service.mapper;

import form.api.com.domain.Endereco;
import form.api.com.domain.Usuario;
import form.api.com.service.dto.EnderecoDTO;
import form.api.com.service.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<UsuarioDTO> usuarioToUsuarioDTO(List<Usuario> usuarios){

        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = modelMapper.map(usuario,UsuarioDTO.class);
            usuarioDTO.setEndereco(modelMapper.map(usuario.getEndereco(), EnderecoDTO.class));
            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }

    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO,Long id){

        Usuario user = new Usuario();
        user.setNome(usuarioDTO.getNome());
        user.setDataNascimento(usuarioDTO.getDataNascimento());
        user.setImagem(usuarioDTO.getImagem());

        Endereco endereco = new Endereco();
        endereco.setId(id);
        user.setEndereco(endereco);
        return user;
    }

    public Endereco usuarioDTOToEndereco(UsuarioDTO usuarioDTO){
        Endereco ende = new Endereco();
        ende.setBairro(usuarioDTO.getEndereco().getBairro());
        ende.setRua(usuarioDTO.getEndereco().getRua());
        ende.setNumero(usuarioDTO.getEndereco().getNumero());
        ende.setCep(usuarioDTO.getEndereco().getCep());
        return ende;
    }



}
