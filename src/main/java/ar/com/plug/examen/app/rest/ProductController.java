package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.ProductDto;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@Tag(name = "Products")
public class ProductController
{
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}

	@Operation(summary = "Gets a paginated list of products")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Product> allProducts(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.productService.getAllProductsPageable(page, size);
	}

	@Operation(summary = "Gets a paginated list of active products")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@GetMapping(value = "/products/active", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Product> activeProducts(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.productService.getActiveProductsPageable(page, size);
	}

	@Operation(summary = "Gets a product by its sku")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product productBySku(
		@Parameter(description = "Product's sku", example = "sku-231")
		@RequestParam String sku)
	{
		return this.productService.getProductBySku(sku);
	}

	@Operation(summary = "Gets a product by its id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product productById(
		@Parameter(description = "Product's internal id", example = "2")
		@PathVariable Long id)
	{
		return this.productService.getProductById(id);
	}

	@Operation(summary = "Creates a new product.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@RequestBody @Valid ProductDto productDto) throws ValidationException
	{
		return this.productService.saveProduct(productDto);
	}

	@Operation(summary = "Updates the information of a product.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@PutMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(
		@Parameter(description = "Product's internal id", example = "2")
		@PathVariable Long id,
		@RequestBody @Valid ProductDto dto) throws ValidationException
	{
		return this.productService.updateProduct(id, dto);
	}

	@Operation(summary = "Delete a product logically by inactivate it.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@PutMapping(value = "/product/inactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product inactivateProduct(
		@Parameter(description = "Product's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.productService.inactivateProduct(id);
	}

	@Operation(summary = "Delete a product physically from the database")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Product.class)
			)
		}
	)
	@DeleteMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long deleteProduct(
		@Parameter(description = "Product's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.productService.deleteProduct(id);
	}
}
