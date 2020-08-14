package ar.com.flexibility.examen.domain.service.impl;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.rest.dto.ProductRequestDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.CreationException;
import ar.com.flexibility.examen.exception.DataValidationException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	private Log log = LogFactory.getLog(ProductServiceImpl.class);
	
	/*
	 * se crea un nuevo producto con el nombre ingresado
	 * por el usuario. Se valida que el nombre no sea igual
	 * al de un producto que ya exista
	 */
	@Override
	public void createProduct(ProductRequestDto productDto) {
		Product product = productRepository.findByName(productDto.getName());
		if (product != null) {
			log.error("ya existe un producto con este nombre, no se puede crear");
			throw new CreationException("ya existe un producto con este nombre, no se puede crear");
		}
		product = new Product();
		product.setName(productDto.getName());
		
		productRepository.saveAndFlush(product);
		log.error("El producto se creó exitosamente");
	}

	/*
	 * se borra un producto y se valida que el mismo 
	 * exista antes de intentar eliminarlo
	 */
	@Override
	public void deleteProduct(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(!product.isPresent()) {
			log.error("el producto no existe, no se puede eliminar");
			throw new DataValidationException("el producto no existe, no se puede eliminar");
		}
		
		productRepository.delete(product.get());
		log.error("El producto se eliminó exitosamente");
	}

	/*
	 * se actualiza un producto, el unico campo modificable es el nombre.
	 * se valida que exista previamente
	 */
	@Override
	public void updateProduct(ProductRequestDto dto) {
		Optional<Product> product = productRepository.findById(dto.getId());
		if(!product.isPresent()) {
			log.error("el producto no existe, no se puede actualizar");
			throw new DataValidationException("el producto no existe, no se puede actualizar");
		}
		
		product.get().setName(dto.getName());
		productRepository.saveAndFlush(product.get());
		log.error("El producto se actualizó exitosamente");
	}

	/*
	 * se busca el producto por su nombre
	 */
	@Override
	public Product findProduct(String productName) {
		Product product = productRepository.findByName(productName);
		log.error("Se realizó la busqueda del producto exitosamente");
		
		return product;
	}

}
