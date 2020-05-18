package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.core.ControllerUtils;
import ar.com.flexibility.examen.app.core.DTOMapper;
import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.dto.SellerDTO;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.impl.SellerService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST service for {@link Seller}
 *
 */
@Controller
@RequestMapping(path = "v1/seller")
@Log4j
public class SellerController extends BaseController<Seller, SellerDTO> {


	private final SellerService entityService;


	public SellerController(SellerService entityService) {
		this.entityService = entityService;
	}

	/**
	 * Creates a {@link Seller}
	 * @param requestBody the {@link SellerDTO} for the request body
	 * @return the created {@link Seller}
	 */
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Seller> create(@RequestBody @NotNull SellerDTO requestBody) {
		log.info(String.format("Creating Seller: [%s]", requestBody));

		Seller entity = DTOMapper.getInstance().map(requestBody, Seller.class);
		try {
			entity = entityService.create(entity);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		log.info(String.format("Seller created. [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns a single {@link Seller}
	 * @param id the id of the seller
	 * @return
	 * @throws BaseObjectNotFoundException if not found
	 */
	@GetMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Seller> getById(@PathVariable @NotNull Long id) throws BaseObjectNotFoundException {
		log.info(String.format("Searching for seller with id [%d]", id));
		Seller entity = entityService.get(id);

		log.info(String.format("Returning seller [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns the list of all active sellers
	 * @return
	 */
	@GetMapping(
			path = "all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<Seller>> getAll() {
		log.info("Returning all active sellers");
		return ResponseEntity.ok(entityService.getAll());
	}

	/**
	 * Logical delete for {@link Seller}
	 * @param id the id of the {@link Seller}
	 * @return a message if successful
	 * @throws BaseObjectNotFoundException if not found
	 */
	@DeleteMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<String> delete(@PathVariable @NotNull Long id) throws BaseObjectNotFoundException {
		entityService.delete(id);

		log.info(String.format("Seller with id [%d] successfully deleted", id));
		return ResponseEntity.ok(ControllerUtils.responseMessage("Seller deleted"));
	}

	/**
	 * Updates a {@link Seller}
	 * @param id the id of the seller
	 * @param entityDTO an instance of {@link SellerDTO} as request body
	 * @return the updated {@link Seller}
	 * @throws BaseObjectNotFoundException if not found
	 */
	@PutMapping(
			path = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Seller> update(@PathVariable @NotNull Long id, @RequestBody SellerDTO entityDTO) throws BaseObjectNotFoundException {
		Seller entity = DTOMapper.getInstance().map(entityDTO, Seller.class);

		entity = entityService.update(id, entity);

		log.info(String.format("Seller with id [%d] updated [%s]", id, entity));
		return ResponseEntity.ok(entity);
	}
}
