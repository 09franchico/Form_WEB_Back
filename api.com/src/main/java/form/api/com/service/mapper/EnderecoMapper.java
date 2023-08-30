package form.api.com.service.mapper;

import form.api.com.domain.Endereco;
import form.api.com.service.dto.EnderecoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {}
