package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.generated.api.ProductsApi;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-29T10:08:36.293-03:00[America/Argentina/Buenos_Aires]")

@Slf4j
@Controller
@RequestMapping("${openapi.paymentManagement.base-path:}")
public class ProductsApiController implements ProductsApi {

	private final NativeWebRequest request;

	private ProductServiceImpl productService;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	@Autowired
	public ProductsApiController(NativeWebRequest request, ProductServiceImpl productService) {
		this.request = request;
		this.productService = productService;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	/**
	 * Creates the product.
	 *
	 * @param product the product
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Product> createProduct(
			@Valid ar.com.plug.generated.model.Product productDTO) {

		Product productEntity = dtoMapper.from(productDTO);

		productEntity = productService.createProduct(productEntity);
		productDTO = dtoMapper.from(productEntity);

		return ResponseEntity.ok().body(productDTO);

	}

	/**
	 * Delete product.
	 *
	 * @param productId the product id
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Void> deleteProduct(Integer productId) {
		productService.deleteProduct(productId.longValue());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Update product.
	 *
	 * @param productId  the product id
	 * @param productDTO the product DTO
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Product> updateProduct(Integer productId,
			ar.com.plug.generated.model.@Valid Product productDTO) {
		log.info("Se recibe una solicitud de creación de product {}", productDTO);

		Product productEntity = dtoMapper.from(productDTO);

		productEntity = productService.updateProduct(productId.longValue(), productEntity);
		productDTO = dtoMapper.from(productEntity);

		return ResponseEntity.ok().body(productDTO);
	}

	/**
	 * Gets the product.
	 *
	 * @param customerId the product id
	 * @return the product
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Product> getProduct(Integer productId) {
		return ResponseEntity.ok().body(dtoMapper.from(productService.getProduct(productId.longValue())));
	}

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	@Override
	public ResponseEntity<List<ar.com.plug.generated.model.Product>> getproducts() {
		List<ar.com.plug.generated.model.Product> productList = productService.getProducts().stream()
				.map((productEntity) -> dtoMapper.from(productEntity)).collect(Collectors.toList());

		return ResponseEntity.ok(productList);

	}

}
