package form.api.com.service;
import form.api.com.domain.Endereco;
import form.api.com.domain.Pessoa;
import form.api.com.infra.exception.errors.Error;
import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.sucessResponse.CustomResponse;
import form.api.com.infra.sucessResponse.Sucesso;
import form.api.com.repository.EnderecoRepository;
import form.api.com.repository.PessoaRepository;
import form.api.com.service.dto.PessoaDTO;
import form.api.com.service.mapper.EnderecoMapper;
import form.api.com.service.mapper.PessoaUserMapper;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.ZebraPrintException;
import fr.w3blog.zpl.model.ZebraUtils;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
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

import java.util.ArrayList;
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
    private PessoaUserMapper pessoaUserMapper;
    @Autowired
    private EnderecoMapper enderecoMapper;

    /**
     * Pega todos os usuarios no banco de dados
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> pegarTodosUsuario(){

         List<Pessoa> pessoa = pessoaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
         List<PessoaDTO> pessoaTD = pessoaUserMapper.toDto(pessoa);
        return new ResponseEntity<>(new CustomResponse(200,
                Sucesso.SC_0.getMsg(), pessoaTD),
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
        Endereco endereco = enderecoMapper.toEntity(pessoa.getEndereco());
        Endereco resulteEnd = enderecoRepository.save(endereco);

        //Salva Pessoa
        Pessoa pessoaSave = pessoaUserMapper.toEntity(pessoa);
        pessoaSave.setEndereco(resulteEnd);
        Pessoa salvo = pessoaRepository.save(pessoaSave);

        return new ResponseEntity<>(new CustomResponse(201,
                Sucesso.SC_1.getMsg(), pessoaUserMapper.toDto(salvo)),
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
            throw new CustomErrorException(HttpStatus.BAD_REQUEST,
                    Error.ERRO_0.getMsg(),
                    new ArrayList<>());
        }
        PessoaDTO respostaPessoa = pessoaUserMapper.toDto(pessoa.get());
        return new ResponseEntity<>(new CustomResponse(200,
                Sucesso.SC_0.getMsg(), respostaPessoa),
                HttpStatus.OK);

    }

    /**
     * Realizar o update de uma pessoa
     * @param pessoaDTO
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> updatePessoa (PessoaDTO pessoaDTO,Long id){

        Optional<Pessoa> pessoaDoBanco = pessoaRepository.findById(Long.valueOf(id));
        if (pessoaDoBanco.isEmpty()){
            throw new CustomErrorException(HttpStatus.BAD_REQUEST,
                    Error.ERRO_0.getMsg(),
                    pessoaDoBanco);
        }
        Optional<Endereco> enderecoDoBanco = enderecoRepository.findById(pessoaDoBanco.get().getEndereco().getId());
        //Mapear para as entidades
        Pessoa ps = pessoaUserMapper.toEntity(pessoaDTO);
        ps.setEndereco(enderecoDoBanco.get());

        //Update nas modificações
        Pessoa pessoa = pessoaRepository.updatePessoa(ps, Long.valueOf(id));
        if (pessoa ==null){
            throw new CustomErrorException(HttpStatus.BAD_REQUEST,
                    Error.ERRO_1.getMsg(),
                    null);
        }
        return new ResponseEntity<>(new CustomResponse(200,
                Sucesso.SC_2.getMsg(), pessoa),
                HttpStatus.OK);

    }

    /**
     * Deleta uma pessoa pelo ID
     * @param id
     * @return
     */
    @Transactional
    public ResponseEntity<CustomResponse> deletarPessoa (Long id){

       Optional<Pessoa> pessoa = pessoaRepository.findById(id);
       if (pessoa.isEmpty()){
           throw new CustomErrorException(HttpStatus.BAD_REQUEST,
                   Error.ERRO_2.getMsg(), pessoa);
       }
       //delete
       pessoaRepository.deleteById(id);
       enderecoRepository.deleteById(pessoa.get().getEndereco().getId());
        return new ResponseEntity<>(new CustomResponse(
                200,
                Sucesso.SC_3.getMsg(), id),
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
        List<PessoaDTO> respostaPessoa = pessoaUserMapper.toDto(pessoas);
        if (respostaPessoa.isEmpty()){
            return new ResponseEntity<>(new CustomResponse(200,
                    Sucesso.SC_00.getMsg(),
                    respostaPessoa),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomResponse(200,
                Sucesso.SC_0.getMsg(),
                respostaPessoa
        ),
                HttpStatus.OK);

    }




}
