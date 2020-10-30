package ar.com.plug.examen.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ar.com.plug.examen.domain.model.Product;

@Mapper(componentModel = "spring")
public interface DTOMapper {

	DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

	public Product from(ar.com.plug.generated.model.Product productDTO);

	public ar.com.plug.generated.model.Product from(Product productEntity);

}