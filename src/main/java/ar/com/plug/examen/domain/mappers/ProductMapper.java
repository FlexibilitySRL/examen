package ar.com.plug.examen.domain.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;

@Component
public class ProductMapper implements Mapper<Product, ProductApi>{

	@Override
	public ProductApi getDto(Product entity) {
		
		ProductApi dto = new ProductApi();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		
		return null;
	}

	@Override
	public Product fillEntity(Product entity, ProductApi dto) {
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		
		return entity;
	}

	@Override
	public List<ProductApi> getDto(Collection<Product> entities) {

		List<ProductApi> dto = new ArrayList<>();
		
		for(Product product : entities) {
			dto.add(getDto(product));
		}
		
		return dto;
	}

}
