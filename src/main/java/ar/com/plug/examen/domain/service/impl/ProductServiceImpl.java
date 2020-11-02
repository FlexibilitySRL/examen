package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	@Autowired
	ProductRepository productRepo;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	/**
	 * Creates the product.
	 *
	 * @param entity the entity
	 * @return the product
	 */
	public Product createProduct(@Valid Product entity) {
		return productRepo.save(entity);
	}

	/**
	 * Delete product.
	 *
	 * @param productId the product id
	 */
	public void deleteProduct(long productId) {
		try {
			productRepo.deleteById(productId);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException();
		}
	}

	/**
	 * Update product.
	 *
	 * @param productId the product id
	 * @param newEntity the new entity
	 * @return the product
	 */
	public Product updateProduct(long productId, Product newEntity) {

		Product oldEntity = productRepo.findById(productId).orElseThrow(EntityNotFoundException::new);

		dtoMapper.updateProduct(newEntity, oldEntity);

		oldEntity = productRepo.save(oldEntity);

		return oldEntity;
	}

	/**
	 * Método que busca un único Product por Id. Si no lo encuentra, levanta una
	 * excepción EntityNotFoundException que luego es mapeada al objeto Error
	 * documentado en la API.
	 * 
	 * @param productId
	 * @return
	 */
	public Product getProduct(long productId) {
		return productRepo.findById(productId).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * Gets the products.
	 * 
	 * TODO: Admitir filtros de búsqueda TODO: Paginar TODO: Ordenar
	 *
	 * @param productId the product id
	 * @return the products
	 */
	public List<Product> getProducts() {
		return ImmutableList.copyOf(productRepo.findAll());
	}
}
