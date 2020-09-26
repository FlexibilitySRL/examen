package ar.com.flexibility.examen.factory;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ar.com.flexibility.examen.domain.model.db.Product;
import ar.com.flexibility.examen.rest.api.model.ProductoDTO;

@Mapper
public interface ProductFactory {
	static ProductFactory INSTANCE = Mappers.getMapper(ProductFactory.class);

	@Mapping(target = "id", expression = "java(ProductFactory.generateId(identificador))")
	@Mapping(target = "name", source = "productoDTO.nombre")
	@Mapping(target = "decription", source = "productoDTO.descripcion")
	@Mapping(target = "cost", source = "productoDTO.costo")
	@Mapping(target = "salePrice", source = "productoDTO.precioVenta")
	@Mapping(target = "quantity", source = "productoDTO.cantidad")
	@Mapping(target = "provider", source = "productoDTO.proveedor")
	Product from(String identificador, ProductoDTO productoDTO);
	
	@Mapping(target = "name", source = "nombre")
	@Mapping(target = "decription", source = "descripcion")
	@Mapping(target = "cost", source = "costo")
	@Mapping(target = "salePrice", source = "precioVenta")
	@Mapping(target = "quantity", source = "cantidad")
	@Mapping(target = "provider", source = "proveedor")
	Product from(ProductoDTO productoDTO);
	List<Product> from (List<ProductoDTO> productosDTO);
	
	static Long generateId(String id) {
		return Long.valueOf(id);
	}
	
	
}
