package form.api.com.repository;

import form.api.com.domain.Pessoa;
import form.api.com.service.dto.PessoaDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Transactional
    default Pessoa updatePessoa(Pessoa pes,Long id) {
        Optional<Pessoa> optionalPessoa = findById(id);
        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            pessoa.setNome(pes.getNome());
            pessoa.setDataNascimento(pes.getDataNascimento());
            pessoa.setImagem(pes.getImagem());
            pessoa.setEndereco(pes.getEndereco());
            return save(pessoa);

        }
        return null;
    }

    @Query(value = "SELECT p.id ,p.nome ,p.data_nascimento ,p.imagem ,p.endereco_id ,e.bairro ,e.rua ,e.numero ,e.cep  FROM Pessoa p " +
            "JOIN endereco e ON e.id = p.endereco_id " +
            "WHERE p.nome LIKE %:filtro% " +
            "OR CAST(p.data_nascimento AS VARCHAR) LIKE %:filtro% " +
            "OR e.bairro LIKE %:filtro% " +
            "OR e.rua LIKE %:filtro% " +
            "OR CAST(e.numero AS VARCHAR) LIKE %:filtro% " +
            "OR e.cep LIKE %:filtro%",nativeQuery = true)
    Page<Pessoa> findByFiltro(@Param("filtro") String filtro, Pageable pageable);

}
