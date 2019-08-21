package ar.com.flexibility.examen.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dto.ObjectDTO;
import ar.com.flexibility.examen.domain.dto.PageRequestDTO;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.exceptions.ProductDoesNotExistException;
import ar.com.flexibility.examen.domain.service.exceptions.ProductIsInAPurchaseOrderException;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;
import ar.com.flexibility.examen.domain.service.exceptions.BusinessException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseOrderLineRepository purchaseOrderLineRepository;
	
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
	public List<ObjectDTO<ProductDTO>> listProducts(PageRequestDTO pageRequestDTO) {
		if ( pageRequestDTO == null )
			throw new UnexpectedNullValueException();
		
		List<ObjectDTO<ProductDTO>> productDTOs = new ArrayList<>();
		
		for ( Product eachProduct : this.productRepository.findAll( pageRequestDTO.toPageRequest() ) ) {
			productDTOs.add( new ObjectDTO<ProductDTO>(eachProduct.getProductId(), new ProductDTO(eachProduct)));
		}
		
		return Collections.unmodifiableList(productDTOs);
	}
	
	/**
	 * @post Devuelve un producto
	 */
	public ProductDTO getProduct(long productId) throws BusinessException {
		Product product = this.productRepository.findOne(productId);
		
		if ( product != null )
			return new ProductDTO(product);
		else
			throw new ProductDoesNotExistException(productId);
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
			throw new UnexpectedNullValueException();
		
		return this.productRepository.save(productDTO.toEntity()).getProductId();
	}
	
	/**
	 * @post Actualiza el cliente especificado
	 * @return
	 */
	@Transactional(
			readOnly = false,
			timeout = 5000,
			propagation = Propagation.SUPPORTS,
			isolation = Isolation.READ_COMMITTED
	)
	public void updateProduct(long productId, ProductDTO productDTO) {
		if ( productDTO == null )
			throw new UnexpectedNullValueException();
		
		Product product = this.productRepository.findOne(productId);
		
		if ( product == null )
			throw new ProductDoesNotExistException(productId);
		
		productDTO.updateEntity(product);
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
	public void removeProduct(long productId) throws BusinessException {
		Product product = this.productRepository.findOne(productId);
		
		if ( product == null ) {
			throw new ProductDoesNotExistException(productId);
		}
		
		if ( !this.purchaseOrderLineRepository.existsPurchaseOrderLineByProduct(product) ) {
			throw new ProductIsInAPurchaseOrderException(productId);
		}
		
		this.productRepository.delete(product);
	}
}
