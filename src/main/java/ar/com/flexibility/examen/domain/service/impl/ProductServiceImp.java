package ar.com.flexibility.examen.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImp.class);
	
	@Autowired
    ProductRepository productRepository;
	
	@Override
	public ResponseEntity<?> getProducts() {
		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getProductById(Long id) {
		return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> insertProduct(Product product) {
		return  new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateProduct(Long id, Product product) {
		product.setId(id);
		productRepository.save(product);
		return  new ResponseEntity<>("Producto actualizado", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteProduct(Long id) {
		try {
			productRepository.delete(productRepository.findById(id));
		} catch(InvalidDataAccessApiUsageException ex) {
	    	LOGGER.info(String.format("Producto solicitado: %s, no encontrado", id));
	        return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
	    }
		return  new ResponseEntity<>("Producto borrado", HttpStatus.OK);
	}

	

}
