package ar.com.flexibility.examen.factory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Client;
import ar.com.flexibility.examen.rest.api.model.ClienteDTO;

@Mapper
public interface ClientFactory {
	static ClientFactory INSTANCE = Mappers.getMapper(ClientFactory.class);

	@Mapping(target = "documentNumber", source = "numeroDocumento")
	@Mapping(target = "documentType", source = "tipoDocumento")
	@Mapping(target = "name", source = "nombre")
	@Mapping(target = "lastName", source = "apellido")
	@Mapping(target = "sex", source = "sexo")
	@Mapping(target = "age", source = "edad")
	Client from(ClienteDTO clienteDTO);

	@Mapping(target = "id", expression = "java(ProductFactory.generateId(identificador))")
	@Mapping(target = "documentNumber", source = "clienteDTO.numeroDocumento")
	@Mapping(target = "documentType", source = "clienteDTO.tipoDocumento")
	@Mapping(target = "name", source = "clienteDTO.nombre")
	@Mapping(target = "lastName", source = "clienteDTO.apellido")
	@Mapping(target = "sex", source = "clienteDTO.sexo")
	@Mapping(target = "age", source = "clienteDTO.edad")
	Client from(String identificador, ClienteDTO clienteDTO);

	static Long generateId(String id) {
		return Long.valueOf(id);
	}
}
