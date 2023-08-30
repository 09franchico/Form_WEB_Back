package form.api.com.service.mapper;
import form.api.com.domain.Pessoa;
import form.api.com.service.dto.PessoaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaUserMapper extends EntityMapper<PessoaDTO, Pessoa> {}
