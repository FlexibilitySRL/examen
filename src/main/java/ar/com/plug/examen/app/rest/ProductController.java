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

import ar.com.plug.examen.app.api.product.ProductRequestApi;
import ar.com.plug.examen.app.api.product.ProductResponseApi;
import ar.com.plug.examen.app.rest.assembler.ProductResponseModelAssembler;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/products")
@Api(tags = { "products" })
public class ProductController {


	@Autowired
	private ProductService service;

	@Autowired
	private ProductResponseModelAssembler assembler;
	
	@Autowired
    private PagedResourcesAssembler<Product> pagedResourcesAssembler;

	@ApiOperation(value = "Search a product with an ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved product"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
			})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityModel<ProductResponseApi> findById(@PathVariable Long id) {
		return assembler.toModel(service.findById(id));
	}

	@ApiOperation(value = "View a list of available products")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved product list"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<ProductResponseApi>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int pageNumber,
			@RequestParam(value = "size", defaultValue = "2000") @PositiveOrZero int size) {

		final PagedModel<EntityModel<ProductResponseApi>> collModel = pagedResourcesAssembler
                .toModel(service.findAll(pageNumber, size), assembler);
		return new ResponseEntity<>(collModel, HttpStatus.OK);
	}

	@ApiOperation(value = "Add a client", code = 201)
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Successfully created product"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<ProductResponseApi>> save(@Valid @RequestBody ProductRequestApi productApi) {

		final Product product = service.save(new ModelMapper().map(productApi, Product.class));
		final EntityModel<ProductResponseApi> entityModel = assembler.toModel(product);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@ApiOperation(value = "Update a client")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully update product"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			})
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<ProductResponseApi>> update(@Valid @RequestBody ProductRequestApi productApi, @PathVariable Long id) {
		
		final Product currentProduct = new ModelMapper().map(productApi, Product.class);
		currentProduct.setId(id);
		final EntityModel<ProductResponseApi> entityModel = assembler.toModel(service.update(currentProduct));

		return ResponseEntity.ok(entityModel);
	}
	
	@ApiOperation(value = "Delete a product", code = 204)
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successfully deleted product"),
			@ApiResponse(code = 400, message = "If any of the parameters is invalid"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
