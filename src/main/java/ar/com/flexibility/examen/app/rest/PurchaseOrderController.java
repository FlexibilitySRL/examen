package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.core.ControllerUtils;
import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderStatus;
import ar.com.flexibility.examen.domain.service.impl.PurchaseOrderService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * REST controller for {@link PurchaseOrder}
 *
 */
@Controller
@RequestMapping("v1/order")
@Log4j
public class PurchaseOrderController extends BaseController<PurchaseOrder, PurchaseOrderDTO>{


	private final PurchaseOrderService entityService;

	public PurchaseOrderController(PurchaseOrderService entityService) {
		this.entityService = entityService;
	}


	/**
	 * Creation service for a {@link PurchaseOrder}
	 * @param requestBody receibes a {@link PurchaseOrderDTO} as request body
	 * @return the created @{@link PurchaseOrder}
	 * @throws BaseObjectNotFoundException if any of the requested elements is not found
	 */
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	ResponseEntity<PurchaseOrder> create(@RequestBody @NotNull PurchaseOrderDTO requestBody) throws BaseObjectNotFoundException {
		log.info(String.format("Creating Product: [%s]", requestBody));

		PurchaseOrder entity = entityService.createFroMDTO(requestBody);


		log.info(String.format("Product created. [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns a single {@link PurchaseOrder} instance
	 * @param id the id of the purchase order to search
	 * @return an instance of the {@link PurchaseOrder}
	 * @throws BaseObjectNotFoundException if not found
	 *
	 */
	@GetMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	ResponseEntity<PurchaseOrder> getById(@PathVariable("id") Long id) throws BaseObjectNotFoundException {
		log.info(String.format("Searching for purchase order with id [%d]", id));
		PurchaseOrder entity = entityService.get(id);

		log.info(String.format("Returning product [%s]", entity));
		return ResponseEntity.ok(entity);
	}

	/**
	 * Returns a list of all registered {@link PurchaseOrder}
	 * @return
	 */
	@GetMapping(
			path = "all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	ResponseEntity<List<PurchaseOrder>> getAll() {
		log.info("Returning all purchase orders");
		return ResponseEntity.ok(entityService.getAll());
	}

	/**
	 * Returns a list of all registered {@link PurchaseOrder}
	 * @return
	 */
	@GetMapping(
			path = "allbycustomer/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<PurchaseOrder>> getAllbyUser(@PathVariable("id") Long id) {
		log.info("Returning all purchase orders by user");
		return ResponseEntity.ok(entityService.getAll());
	}

	/**
	 * Logical delete for a {@link PurchaseOrder} entity
	 * @param id the id of the entity to delete
	 * @return a string if successful
	 * @throws BaseObjectNotFoundException if the entity is not found
	 */
	@DeleteMapping(
			path = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	ResponseEntity<String> delete(@NotNull @PathVariable("id") Long id) throws BaseObjectNotFoundException {
		log.info(String.format("Deleting purchase order with id [%d]", id));
		entityService.delete(id);

		log.info(String.format("PurchaseOrder with id [%d] successfully deleted", id));
		return ResponseEntity.ok(ControllerUtils.responseMessage("Entity deleted"));
	}

	@Override
	ResponseEntity<PurchaseOrder> update( Long id, PurchaseOrderDTO entityDTO) throws BaseObjectNotFoundException {
		return null;
	}

	/**
	 * Rejects a purchase order
	 * @param id the purchase order id
	 * @return the updated purchase order
	 * @throws BaseObjectNotFoundException if not found
	 */
	@PostMapping(
			path = "/reject/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PurchaseOrder> reject(@PathVariable("id") @NotNull Long id) throws BaseObjectNotFoundException {

		PurchaseOrder entity = entityService.setStatus(id, PurchaseOrderStatus.REJECTED);
		log.info(String.format("Rejected purchase order with id [%d]", id));
		return ResponseEntity.ok(entity);
	}

	/***
	 * Approves a purchase order
	 * @param id the purchase order id
	 * @return the updated purchase order
	 * @throws BaseObjectNotFoundException if not found
	 */
	@PostMapping(
			path = "/approve/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PurchaseOrder> approve(@PathVariable("id") @NotNull Long id) throws BaseObjectNotFoundException {
		PurchaseOrder entity = entityService.setStatus(id, PurchaseOrderStatus.APPROVED);
		log.info(String.format("Approved purchase order with id [%d]", id));
		return ResponseEntity.ok(entity);
	}

}
