package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Product;

@Component
public class ProductMapper {

	public ProductDTO productToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setStock(product.getStock());
		return productDTO;
	}

	public Product productDTOtoProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		return product;
	}

	public List<ProductDTO> productToListProductDTO(List<Product> products) {
		return products.stream().map(this::productToProductDTO).collect(Collectors.toList());
	}

	public List<Product> productDTOToListProduct(List<ProductDTO> productsDTO) {
		return productsDTO.stream().map(this::productDTOtoProduct).collect(Collectors.toList());
	}
}
