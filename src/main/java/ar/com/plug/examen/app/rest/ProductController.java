package ar.com.plug.examen.app.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.IProductService;
import ar.com.plug.examen.domain.utils.PaymentsSubsystemTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Products CRUD")
@RestController
@RequestMapping(path = "/products")
public class ProductController extends AbstractPaymentsController {
	
	public static final String PRODUCT_ENTITY = "Product";

	@Autowired
	private IProductService productService;
	
	//TODO arreglar esto para que responda un ResponseEntity
	@ApiOperation(value = "Find all products")
	@GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<Product> list() {
		return productService.findAll();
	}
	
	@ApiOperation(value = "Find a Product", notes = "Return a Product by id")
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {
			
			Product product = productService.findById(id);
			if (product == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PRODUCT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getProductApi(product));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create a Product", notes = "Create a new Product")
	@PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@Valid @RequestBody ProductApi productApi, BindingResult result) {
		try {

			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			Product product = PaymentsSubsystemTranslator.getInstance().getProduct(productApi);
			
			productApi = PaymentsSubsystemTranslator.getInstance().getProductApi(productService.save(product));
			return buildResponseCreate(PRODUCT_ENTITY, productApi);
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update a Product", notes = "Return a product by id")
	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> update(@Valid @RequestBody ProductApi productApi, BindingResult result, @PathVariable Long id ) {
		try {
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			Product persistedProduct = productService.findById(id);
			if (persistedProduct == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PRODUCT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			persistedProduct.setName(productApi.getName());
			persistedProduct.setPrice(productApi.getPrice());
			
			Product updatedProduct = productService.save(persistedProduct);
			
			productApi = PaymentsSubsystemTranslator.getInstance().getProductApi(updatedProduct);
			return buildResponseUpdate(PRODUCT_ENTITY, productApi);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Delete a Product", notes = "Delete a Product by id")
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			productService.delete(id);
			return buildResponseDelete(PRODUCT_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError("Unexpected exception at delete.", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
