package form.api.com.repository;

import form.api.com.domain.Pessoa;
import form.api.com.service.dto.PessoaDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
            Pessoa pessoaSalva = save(pessoa);

            return pessoaSalva;
        }
        return null;
    }
}
