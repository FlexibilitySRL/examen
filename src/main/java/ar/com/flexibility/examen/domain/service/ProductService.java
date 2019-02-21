/**
 * 
 */
package ar.com.flexibility.examen.domain.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ProductRepository;

/**
 * @author rosalizaracho
 *
 */
@Service
@Transactional(readOnly=true)
public class ProductService {
  
	@Autowired
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public Product createProduct(Product product) {
		return productRepository.save(product);
		
	}

	public List<ProductApi> findAll() {
		return getProductApiList(productRepository.findAll());
	}
	
	@Transactional
	public void update(Product product) {
		productRepository.save(product);
	}

	public List<ProductApi>findBySeller(Seller seller) {
		return getProductApiList(productRepository.findBySeller(seller));
	}
	
	public List<ProductApi> getProductApiList(List<Product> productList) {

		return productList.stream().map(p -> new ProductApi(p)).collect(Collectors.toList());
	}
}
