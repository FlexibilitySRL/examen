package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.client.ClientRequestApi;
import ar.com.plug.examen.app.api.client.ClientResponseApi;
import ar.com.plug.examen.app.rest.assembler.ClientResponseModelAssembler;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/clients")
@Api(tags = { "clients" })
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private ClientResponseModelAssembler assembler;

	@Autowired
	private PagedResourcesAssembler<Client> pagedResourcesAssembler;

	@ApiOperation(value = "Search a client with an ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved client"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityModel<ClientResponseApi> findById(@PathVariable Long id) {
		return assembler.toModel(service.findById(id));
	}

	@ApiOperation(value = "View a list of available products")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved clients list"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<ClientResponseApi>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int pageNumber,
			@RequestParam(value = "size", defaultValue = "2000") @PositiveOrZero int size) {

		final PagedModel<EntityModel<ClientResponseApi>> collModel = pagedResourcesAssembler
				.toModel(service.findAll(pageNumber, size), assembler);
		return new ResponseEntity<>(collModel, HttpStatus.OK);
	}

	@ApiOperation(value = "Add a client", code = 201)
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Successfully created client"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<ClientResponseApi>> save(@Valid @RequestBody ClientRequestApi request) {

		final Client entity = service.save(new ModelMapper().map(request, Client.class));
		final EntityModel<ClientResponseApi> entityModel = assembler.toModel(entity);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@ApiOperation(value = "Update a client")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully update client"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			})
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<ClientResponseApi>> update(@Valid @RequestBody ClientRequestApi request, @PathVariable Long id) {

		final Client entity = new ModelMapper().map(request, Client.class);
		entity.setId(id);

		return ResponseEntity.ok(assembler.toModel(service.update(entity)));
	}

	@ApiOperation(value = "Delete a client", code = 204)
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successfully deleted client"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
