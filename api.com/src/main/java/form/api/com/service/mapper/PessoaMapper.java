package form.api.com.service.mapper;

import form.api.com.domain.Endereco;
import form.api.com.domain.Pessoa;
import form.api.com.service.dto.EnderecoDTO;
import form.api.com.service.dto.PessoaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaMapper {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Mapeia a Pessoa para PessoaDTO
     * @param pessoas
     * @return
     */
    public List<PessoaDTO> pessoaToPessoaDTO(List<Pessoa> pessoas){

        List<PessoaDTO> pessoaDTOs = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            PessoaDTO pessoaDTO = modelMapper.map(pessoa, PessoaDTO.class);
            pessoaDTO.setEndereco(modelMapper.map(pessoa.getEndereco(), EnderecoDTO.class));
            pessoaDTOs.add(pessoaDTO);
        }
        return pessoaDTOs;
    }

    /**
     * Mapeia Optional Pessoa para PessoaDTO
     * @param pessoa
     * @return
     */
    public PessoaDTO optionalPessoaToPessoaDTO(Optional<Pessoa> pessoa){

        if (pessoa.isEmpty()){
            return null;
        }

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.get().getId());
        pessoaDTO.setNome(pessoa.get().getNome());
        pessoaDTO.setDataNascimento(pessoa.get().getDataNascimento());
        pessoaDTO.setImagem(pessoa.get().getImagem());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(pessoa.get().getEndereco().getId());
        enderecoDTO.setBairro(pessoa.get().getEndereco().getBairro());
        enderecoDTO.setCep(pessoa.get().getEndereco().getCep());
        enderecoDTO.setRua(pessoa.get().getEndereco().getRua());
        enderecoDTO.setNumero(pessoa.get().getEndereco().getNumero());
        pessoaDTO.setEndereco(enderecoDTO);
        return pessoaDTO;
    }

    /**
     * Mapeia Item Pessoa para PessoaDTO
     * @param pessoa
     * @return
     */
    public PessoaDTO itemPessoaToPessoaDTO(Pessoa pessoa){

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());
        pessoaDTO.setImagem(pessoa.getImagem());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(pessoa.getEndereco().getId());
        enderecoDTO.setBairro(pessoa.getEndereco().getBairro());
        enderecoDTO.setCep(pessoa.getEndereco().getCep());
        enderecoDTO.setRua(pessoa.getEndereco().getRua());
        enderecoDTO.setNumero(pessoa.getEndereco().getNumero());
        pessoaDTO.setEndereco(enderecoDTO);

        return pessoaDTO;
    }

    /**
     * Mepeia pessoaDTO e id para Pessoa
     * @param pessoaDTO
     * @param id
     * @return
     */
    public Pessoa pessoaDTOToPessoa(PessoaDTO pessoaDTO,Long id){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setImagem(pessoaDTO.getImagem());
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setBairro(pessoaDTO.getEndereco().getBairro());
        endereco.setRua(pessoaDTO.getEndereco().getRua());
        endereco.setNumero(pessoaDTO.getEndereco().getNumero());
        endereco.setCep(pessoaDTO.getEndereco().getCep());
        pessoa.setEndereco(endereco);
        return pessoa;
    }

    /**
     * Mepeia PessoaDTO para endereco
     * @param pessoaDTO
     * @return
     */
    public Endereco pessoaDTOToEndereco(PessoaDTO pessoaDTO){
        Endereco ende = new Endereco();
        ende.setBairro(pessoaDTO.getEndereco().getBairro());
        ende.setRua(pessoaDTO.getEndereco().getRua());
        ende.setNumero(pessoaDTO.getEndereco().getNumero());
        ende.setCep(pessoaDTO.getEndereco().getCep());
        return ende;
    }

}
