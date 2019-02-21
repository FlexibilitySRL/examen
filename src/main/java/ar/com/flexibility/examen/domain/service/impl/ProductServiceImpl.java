/**
 * 
 */
package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

/**
 * @author rosalizaracho
 *
 */
@Service
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	@Override
	public ProductApi saveOrUpdate(Product product) {
		return new ProductApi(productRepository.save(product));
		
	}
    
	@Override
	public List<ProductApi> findAll() {
		return getProductApiList(productRepository.findAll());
	}
	
	@Override
	public List<ProductApi>findBySeller(Seller seller) {
		return getProductApiList(productRepository.findBySeller(seller));
	}
	
	public List<ProductApi> getProductApiList(List<Product> productList) {
     	return productList.stream().map(p -> new ProductApi(p)).collect(Collectors.toList());
	}

}
