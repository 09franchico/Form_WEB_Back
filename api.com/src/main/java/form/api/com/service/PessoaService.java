package form.api.com.service;
import form.api.com.domain.Endereco;
import form.api.com.domain.Pessoa;
import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.repository.EnderecoRepository;
import form.api.com.repository.PessoaRepository;
import form.api.com.service.dto.PessoaDTO;
import form.api.com.service.mapper.PessoaMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaMapper pessoaMapper;

    /**
     * Pega todos os usuarios no banco de dados
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> pegarTodosUsuario(){

         List<Pessoa> pessoa = pessoaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
         List<PessoaDTO> pessoaTD = pessoaMapper.pessoaToPessoaDTO(pessoa);


        return new ResponseEntity<>(new CustomResponse(200,
                "Registro encontrado com sucesso!!", 0L,pessoaTD),
                HttpStatus.OK);
    }

    /**
     * Cria um Usuario com PessoaDto
     * @param pessoa
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> criarUsuario(PessoaDTO pessoa){

        //Salva endereco
        Endereco endereco = pessoaMapper.pessoaDTOToEndereco(pessoa);
        Endereco resulteEnd = enderecoRepository.save(endereco);

        //Salva pessoa
        Pessoa ps = pessoaMapper.pessoaDTOToPessoa(pessoa,resulteEnd.getId());
        Pessoa us = pessoaRepository.save(ps);

        //Mapear para PessoaDTO
        PessoaDTO responsePessoa = pessoaMapper.itemPessoaToPessoaDTO(us);

        return new ResponseEntity<>(new CustomResponse(201,
                "Pessoa criado com sucesso!!",0L, responsePessoa),
                HttpStatus.CREATED);


    }

    /**
     * Busca uma pessoa pelo ID
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> buscarPessoaID (String id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(Long.valueOf(id));
        if(pessoa.isEmpty()){
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Pessoa não encontrado", pessoa);
        }
        PessoaDTO respostaPessoa = pessoaMapper.optionalPessoaToPessoaDTO(pessoa);
        return new ResponseEntity<>(new CustomResponse(200,
                "Registro encontrado com sucesso!!",0L, respostaPessoa),
                HttpStatus.OK);

    }

    /**
     * Realizar o update de uma pessoa
     * @param pessoaDTO
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> updatePessoa (PessoaDTO pessoaDTO,String id){
        Optional<Pessoa> pessoaDoBanco = pessoaRepository.findById(Long.valueOf(id));
        if (pessoaDoBanco.isEmpty()){
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Pessoa não encontrado", pessoaDoBanco);
        }

        Optional<Endereco> enderecoDoBanco = enderecoRepository.findById(pessoaDoBanco.get().getEndereco().getId());

        //Mapear para as entidades
        Pessoa ps = pessoaMapper.pessoaDTOToPessoa(pessoaDTO,enderecoDoBanco.get().getId());

        //Update nas modificações
        Pessoa pessoa = pessoaRepository.updatePessoa(ps, Long.valueOf(id));
        if (pessoa ==null){
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Registro não editado encontrado", null);
        }

        return new ResponseEntity<>(new CustomResponse(200,
                "Registro Editado com sucesso!!",0L, pessoa),
                HttpStatus.OK);
    }

    /**
     * Deleta uma pessoa pelo ID
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> deletarPessoa (String id){
       Optional<Pessoa> pessoa = pessoaRepository.findById(Long.valueOf(id));

       if (pessoa.isEmpty()){
           throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Registro não encontrado", pessoa);
       }

       //delete
       pessoaRepository.deleteById(Long.valueOf(id));
       enderecoRepository.deleteById(pessoa.get().getEndereco().getId());

        return new ResponseEntity<>(new CustomResponse(200,
                "Registro Deletado com sucesso!!",0L, id),
                HttpStatus.OK);


    }

    /**
     * Busca os dados pelo filtro de pesquisa, limit e offset
     * @param filtro
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> BuscarPessoaFiltroId (String filtro, String limit , String offset){


        Sort sort = Sort.by("nome").ascending(); // Ordenação opcional
        Pageable pageable = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit),sort);
        Page<Pessoa> resultado = pessoaRepository.findByFiltro(filtro, pageable);
        List<Pessoa> pessoas = resultado.getContent();

        List<PessoaDTO> respostaPessoa = pessoaMapper.pessoaToPessoaDTO(pessoas);
        return new ResponseEntity<>(new CustomResponse(200,
                "Registro encontrado com sucesso!!",resultado.getTotalElements() ,respostaPessoa),
                HttpStatus.OK);

    }




}
