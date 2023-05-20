package form.api.com.repository;

import form.api.com.domain.Endereco;
import form.api.com.domain.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    @Transactional
    default Endereco updateEndereco(Endereco end, Long id) {
        Optional<Endereco> optionalEndereco = findById(id);
        if (optionalEndereco.isPresent()) {
            Endereco endereco = optionalEndereco.get();
            endereco.setCep(end.getCep());
            endereco.setRua(end.getRua());
            endereco.setBairro(end.getBairro());
            endereco.setNumero(end.getNumero());
            return save(endereco);

        }
        return null;
    }

}
