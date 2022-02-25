package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepository;
	private final SellerRepository sellerRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, SellerRepository sellerRepository)
	{
		this.productRepository = productRepository;
		this.sellerRepository = sellerRepository;
	}

	/**
	 * Returns a list of {@link Product} in a paginated format according to {@link PageDto} attributes.
	 * All the products must have the active attribute set to true.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with the active products in it.
	 */
	@Override
	public PageDto<Product> getActiveProductsPageable(int pageNumber, int pageSize)
	{
		log.debug("[getActiveProductsPageable] page:{}, size:{}", pageNumber, pageSize);
		return new PageDto<>(
			this.productRepository.findAllByActiveTrue(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns the list of all {@link Product} in a paginated format according to {@link PageDto} attributes.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with all products in it.
	 */
	@Override
	public PageDto<Product> getAllProductsPageable(int pageNumber, int pageSize)
	{
		log.debug("[getAllProductsPageable] page:{}, size:{}", pageNumber, pageSize);
		return new PageDto<>(
			this.productRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns a {@link Product} according to its {@code id}.
	 *
	 * @param id id of the Product to query
	 * @return {@link Product} with the corresponding {@code id}.
	 */
	@Override
	public Product getProductById(Long id)
	{
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id del producto no puede ser nulo.");
		}
		log.debug("[getProductById] id:{}", id);
		Optional<Product> optionalProduct = this.productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			throw new NoSuchElementException("El id del producto no se encuentra en la base de datos.");
		}
	}

	/**
	 * Returns a {@link Product} according to its {@code sku} code.
	 *
	 * @param sku identifier of a product
	 * @return {@link Product} with the corresponding {@code sku}.
	 */
	@Override
	public Product getProductBySku(String sku)
	{
		if(StringUtils.isBlank(sku)) {
			throw new NoSuchElementException("El código del producto no puede ser nulo.");
		}
		log.debug("[getProductBySku] sku:{}", sku);
		Product productFromDatabase = this.productRepository.findBySku(sku);
		if(Objects.nonNull(productFromDatabase)) {
			return productFromDatabase;
		} else {
			throw new NoSuchElementException("El código del producto no se encuentra en la base de datos.");
		}
	}

	/**
	 * Creates a new {@link Product} by using the data of the {@link ProductDto} passed as parameter.
	 *
	 * @param productDto data which will be use to create a new Product.
	 * @return the new created {@link Product}
	 * @throws ValidationException    In case of a null parameters or null seller.
	 * @throws NoSuchElementException In case of that the seller doesn't exist on the database..
	 */
	@Override
	public Product saveProduct(ProductDto productDto) throws ValidationException
	{
		if(Objects.isNull(productDto)) {
			throw new ValidationException("Los datos para la creación de un producto no pueden ser nulos.");
		}
		if(Objects.isNull(productDto.getSellerId())) {
			throw new ValidationException("El ID del proveedor no puede ser nulo.");
		}
		log.debug("[saveProduct] productDto:{}", productDto);
		Seller seller = this.sellerRepository.findById(productDto.getSellerId())
			.orElseThrow(
				() -> new NoSuchElementException("El proveedor con el id " + productDto.getSellerId() + " no existe.")
			);
		Product newProduct = Product.builder()
			.sku(productDto.getSku())
			.skuVendor(productDto.getSkuVendor())
			.cost(productDto.getCost())
			.salePrice(productDto.getSalePrice())
			.description(productDto.getDescription())
			.active(productDto.getActive())
			.stockQty(productDto.getStockQuantity())
			.seller(seller)
			.modificationDate(new Date())
			.build();
		return this.productRepository.save(newProduct);
	}

	/**
	 * Updates a {@link Product} by using the data of the {@link ProductDto} passed as parameter.
	 *
	 * @param id         identifier to query the Product in the database.
	 * @param productDto data which will be use to create a new Product.
	 * @return the updated {@link Product}
	 * @throws ValidationException    In case of a null parameters or null seller.
	 * @throws NoSuchElementException In case of that the seller doesn't exist on the database..
	 */
	@Override
	public Product updateProduct(Long id, ProductDto productDto) throws ValidationException
	{
		if(Objects.isNull(id) || (Objects.isNull(productDto))) {
			throw new ValidationException("Los datos para la actualización de un producto no pueden ser nulos.");
		}
		log.debug("[updateProduct] id:{}, productDto:{}", id, productDto);
		if(this.productRepository.existsById(id)) {
			Product productFromDatabase = this.productRepository.findBySku(productDto.getSku());
			if(Objects.nonNull(productFromDatabase) && !productFromDatabase.getId().equals(id)) {
				throw new ValidationException("El sku ya pertenece a otro producto.");
			}
			Seller seller = this.sellerRepository.findById(productDto.getSellerId())
				.orElseThrow(
					() -> new NoSuchElementException(
						"El proveedor con el id " + productDto.getSellerId() + " no existe.")
				);
			productFromDatabase.setSku(productDto.getSku());
			productFromDatabase.setSkuVendor(productDto.getSkuVendor());
			productFromDatabase.setCost(productDto.getCost());
			productFromDatabase.setSalePrice(productDto.getSalePrice());
			productFromDatabase.setDescription(productDto.getDescription());
			productFromDatabase.setActive(productDto.getActive());
			productFromDatabase.setStockQty(productDto.getStockQuantity());
			productFromDatabase.setSeller(seller);
			productFromDatabase.setModificationDate(new Date());
			return this.productRepository.save(productFromDatabase);
		} else {
			throw new NoSuchElementException("El producto con el id " + id + " no existe.");
		}
	}

	/**
	 * Updates a {@link Product} by setting the active attribute to false.
	 *
	 * @param id identifier to query the Product in the database.
	 * @return the inactivated {@link Product}
	 * @throws ValidationException    In case of a null parameters or null seller.
	 * @throws NoSuchElementException In case of that the product doesn't exist on the database.
	 */
	@Override
	public Product inactivateProduct(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización del producto no pueden ser nulos.");
		}
		log.debug("[inactivateProduct] id:{}", id);
		Product productFromDatabase = this.productRepository.findById(id)
			.orElseThrow(
				() -> new NoSuchElementException("El producto con el id " + id + " no existe.")
			);
		;
		productFromDatabase.setActive(Boolean.FALSE);
		return this.productRepository.save(productFromDatabase);
	}

	/**
	 * Performs a physical delete of a {@link Product}.
	 *
	 * @param id identifier to query the Product in the database.
	 * @return the inactivated {@link Product}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the product doesn't exist on the database.
	 */
	@Override
	public Long deleteProduct(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para el borrado del producto no pueden ser nulos.");
		}
		log.debug("[deleteProduct] id:{}", id);
		if(this.productRepository.existsById(id)) {
			this.productRepository.deleteById(id);
			return id;
		} else {
			throw new NoSuchElementException("El producto con el id " + id + " no existe.");
		}
	}
}
