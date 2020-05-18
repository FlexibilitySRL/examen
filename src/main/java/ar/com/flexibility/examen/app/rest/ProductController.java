package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.core.ControllerUtils;
import ar.com.flexibility.examen.app.core.DTOMapper;
import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rest controller for the {@link Product} entity
 */
@RestController
@RequestMapping("v1/product")
@Log4j
public class ProductController extends BaseController<Product, ProductDTO> {

	private final ProductService entityService;


	public ProductController(ProductService entityService) {
		this.entityService = entityService;
	}

	/**
	 * Creates a @{@link Product}
	 *
	 * @param requestBody an instance of {@link ProductDTO}
	 * @return the created {@link Product}
	 */
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Product> create(@RequestBody ProductDTO requestBody) {
		log.info(String.format("Creating Product: [%s]", requestBody));

		Product entity = DTOMapper.getInstance().map(requestBody, Product.class);
		try {
			entity = entityService.create(entity);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		log.info(String.format("Product created. [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns a single {@link Product}
	 * @param id the product id
	 * @return an instance of {@link Product} if found
	 * @throws BaseObjectNotFoundException if not found
	 */
	@GetMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Product> getById(@PathVariable("id") Long id) throws BaseObjectNotFoundException {
		log.info(String.format("Searching for product with id [%d]", id));
		Product entity = entityService.get(id);

		log.info(String.format("Returning product [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns a list with all active products
	 * @return
	 */
	@GetMapping(
			path = "all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<Product>> getAll() {
		log.info("Returning all active customers");
		return ResponseEntity.ok(entityService.getAll());
	}

	/**
	 * Logical delete for {@link Product}
	 * @param id the product id
	 * @return a message if successful
	 * @throws BaseObjectNotFoundException if not found
	 */
	@DeleteMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<String> delete(@PathVariable("id") Long id) throws BaseObjectNotFoundException {

		entityService.delete(id);

		log.info(String.format("Product with id [%d] successfully deleted", id));
		return ResponseEntity.ok(ControllerUtils.responseMessage("Entity deleted"));
	}

	/**
	 * Updates a {@link Product}
	 * @param id the product id
	 * @param entityDTO an instance of @{@link ProductDTO} as request body
	 * @return the updated {@link Product}
	 * @throws BaseObjectNotFoundException if the product id is not found
	 */
	@PutMapping(
			path = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Product> update(@NotNull @PathVariable("id") Long id, @RequestBody ProductDTO entityDTO) throws BaseObjectNotFoundException {
		Product entity = DTOMapper.getInstance().map(entityDTO, Product.class);

		entity = entityService.update(id, entity);

		log.info(String.format("Object with id [%d] updated [%s]", id, entity));
		return ResponseEntity.ok(entity);
	}
}
