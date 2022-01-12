package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.transaction.TransactionRequestApi;
import ar.com.plug.examen.app.api.transaction.TransactionResponseApi;
import ar.com.plug.examen.app.api.transaction.TransactionStatusRequestApi;
import ar.com.plug.examen.app.rest.assembler.TransactionResponseModelAssembler;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;
import ar.com.plug.examen.domain.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/transactions")
@Api(tags = { "transactions" })
public class TransactionController {

	@Autowired
	private TransactionService service;

	@Autowired
	private TransactionResponseModelAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Transaction> pagedResourcesAssembler;

	@ApiOperation(value = "Search a transaction with an ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved transaction"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityModel<TransactionResponseApi> findById(@PathVariable Long id) {
		return assembler.toModel(service.findById(id));
	}
	
	@ApiOperation(value = "View a list of available transactions")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved transaction list"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<TransactionResponseApi>>> findAll(@RequestParam(value = "status", required = false) TransactionStatusEnum status,
			@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int pageNumber,
			@RequestParam(value = "size", defaultValue = "2000") @PositiveOrZero int size) {

		Page<Transaction> page;

		if (status == null) {
			page = service.findAll(pageNumber, size);
		} else {
			page = service.findAllByStatus(status, pageNumber, size);
		}

		final PagedModel<EntityModel<TransactionResponseApi>> collModel = pagedResourcesAssembler.toModel(page, assembler);
		return new ResponseEntity<>(collModel, HttpStatus.OK);
	}

	@ApiOperation(value = "Add a client", code = 201)
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Successfully created transaction"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<TransactionResponseApi>> save(@Valid @RequestBody TransactionRequestApi request) {

		final Transaction entity = service.save(new ModelMapper().map(request, Transaction.class));
		final EntityModel<TransactionResponseApi> entityModel = assembler.toModel(entity);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@ApiOperation(value = "Update a status client")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully update status transaction"),
			@ApiResponse(code = 304, message = "If the transaction has the same status"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 409, message = "If the transaction has a incompatible status"),
			})
	@PatchMapping
	public ResponseEntity<EntityModel<TransactionResponseApi>> updateStatus(@Valid @RequestBody TransactionStatusRequestApi request) {
		final Transaction transaction =  service.updateStatus(request.getId(), request.getStatus());
		
		if(transaction == null) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
		final EntityModel<TransactionResponseApi> entityModel = assembler.toModel(transaction);
		
		return ResponseEntity.ok(entityModel);
	}

	
	@ApiOperation(value = "Delete a transaction", code = 204)
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successfully deleted transaction"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
