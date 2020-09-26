package ar.com.flexibility.examen.factory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Product;
import ar.com.flexibility.examen.rest.api.model.ProductoDTO;

@Mapper
public interface ProductoDTOFactory {

	static ProductoDTOFactory INSTANCE = Mappers.getMapper(ProductoDTOFactory.class);

	@Mapping(source = "id", target = "udid")
	@Mapping(source = "name", target = "nombre")
	@Mapping(source = "decription", target = "descripcion")
	@Mapping(source = "cost", target = "costo")
	@Mapping(source = "salePrice", target = "precioVenta")
	@Mapping(source = "quantity", target = "cantidad")
	@Mapping(source = "provider", target = "proveedor")
	ProductoDTO from(Product product);
}
