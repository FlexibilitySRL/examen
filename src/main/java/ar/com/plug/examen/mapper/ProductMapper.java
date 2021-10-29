package ar.com.plug.examen.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.entities.Product;
import ar.com.plug.examen.dto.ProductDto;

@Component
public class ProductMapper {

	public ProductDto entityToDto(Product dao) {
		ProductDto dto = new ProductDto();
		dto.setName(dao.getName());
		dto.setDescription(dao.getDescription());
		dto.setValue(dao.getValue());
		return dto;
	}
	
	public Product dtoToEntity(ProductDto dto) {
		Product dao = new Product();
		dao.setName(dto.getName());
		dao.setDescription(dto.getDescription());
		dao.setValue(dto.getValue());
		return dao;
	}
	
	public List<ProductDto> entityListToDtoList(List<Product> daos){
		return daos.stream().map(d -> this.entityToDto(d)).collect(Collectors.toList());
	}
	
	public List<Product> dtoListToEntityList(List<ProductDto> dtos){
		return dtos.stream().map(d -> this.dtoToEntity(d)).collect(Collectors.toList());
	}
}
