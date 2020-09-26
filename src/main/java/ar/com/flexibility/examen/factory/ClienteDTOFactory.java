package ar.com.flexibility.examen.factory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Client;
import ar.com.flexibility.examen.rest.api.model.ClienteDTO;

@Mapper
public interface ClienteDTOFactory {
	static ClienteDTOFactory INSTANCE = Mappers.getMapper(ClienteDTOFactory.class);

	@Mapping(source = "id", target = "udid")
	@Mapping(source = "documentNumber", target = "numeroDocumento")
	@Mapping(source = "documentType", target = "tipoDocumento")
	@Mapping(source = "name", target = "nombre")
	@Mapping(source = "lastName", target = "apellido")
	@Mapping(source = "sex", target = "sexo")
	@Mapping(source = "age", target = "edad")
	ClienteDTO from(Client client);
}
