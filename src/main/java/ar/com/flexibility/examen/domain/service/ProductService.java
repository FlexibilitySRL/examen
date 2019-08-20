package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.exceptions.ProductDoesNotExists;
import ar.com.flexibility.examen.domain.service.exceptions.ProductIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.UserServiceException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	/**
	 * @post Devuelve el producto con sus descripciones
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public List<ObjectDTO<ProductDTO>> findAllProductsById() {
		List<ObjectDTO<ProductDTO>> productDTOs = new ArrayList<>();
		
		for ( Product eachProduct : this.productRepository.findAll() ) {
			productDTOs.add( new ObjectDTO<ProductDTO>(eachProduct.getProductId(), new ProductDTO(eachProduct)));
		}
		
		return Collections.unmodifiableList(productDTOs);
	}
	
	/**
	 * @pre El DTO de producto no puede ser nulo
	 * @post Dado un DTO de producto agrega un producto.
	 * 		 Devuelve el ID
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public long addProduct(ProductDTO productDTO) {
		if ( productDTO == null )
			throw new NullPointerException();
		
		return this.productRepository.save(productDTO.toEntity()).getProductId();
	}
	
	/**
	 * @pre No tiene que haber órdenes de compra que referencien el producto
	 * 		especificado
	 * @post Remueve un producto con el id especificado
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.SERIALIZABLE
	)
	public void removeProduct(long productId) throws UserServiceException {
		Product product = this.productRepository.findOne(productId);
		
		if ( product == null ) {
			throw new ProductDoesNotExists(productId);
		}
		
		if ( !this.purchaseOrderRepository.existsByProduct(product) ) {
			throw new ProductIsInAPurchaseOrderException(productId);
		}
		
		this.productRepository.delete(product);
	}
}
