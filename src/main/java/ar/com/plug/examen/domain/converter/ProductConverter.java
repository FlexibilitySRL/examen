package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter
{
	public Product toModel(ProductDTO productDTO) {
		return Product.builder()
				.id(productDTO.getId())
				.description(productDTO.getDescription())
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.stock(productDTO.getStock())
				.build();
	}

	public ProductDTO toDTO(Product product) {
		return ProductDTO.builder()
				.id(product.getId())
				.description(product.getDescription())
				.name(product.getName())
				.price(product.getPrice())
				.stock(product.getStock())
				.build();
	}
}
