package ar.com.plug.examen.creator;

import java.util.ArrayList;
import java.util.List;

import ar.com.plug.examen.dao.entities.Product;
import ar.com.plug.examen.dto.ProductDto;

public class ProductCreator {

	public static Product createProductWithNameAndDescription(String name, String description) {
		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setValue(123);
		return p;
	}
	
	public static ProductDto createProductDtoWithNameAndDescription(String name, String description){
		ProductDto p = new ProductDto();
		p.setName(name);
		p.setDescription(description);
		p.setValue(123);
		return p;
	}
	
	public static ProductDto createProductDtoWithId(Long id){
		ProductDto p = new ProductDto();
		p.setId(id);
		return p;
	}

	public static Product createProductWithId(Long id){
		Product p = new Product();
		p.setId(id);
		return p;
	}

	public static List<ProductDto> createProductDtoList(ProductDto p1, ProductDto p2) {
		List<ProductDto> pList = new ArrayList<ProductDto>();
		pList.add(p1);
		pList.add(p2);
		return pList;
	}
	
	public static List<Product> createProductList(Product p1, Product p2) {
		List<Product> pList = new ArrayList<Product>();
		pList.add(p1);
		pList.add(p2);
		return pList;
	}
	
	public static ProductDto createProductDto() {
		ProductDto p = new ProductDto();
		p.setName("product 1");
		p.setDescription("product 1");
		p.setValue(123);
		return p;
	}
	
	public static Product createProduct() {
		Product p = new Product();
		p.setName("product 1");
		p.setDescription("product 1");
		p.setValue(123);
		return p;
	}
	
}
