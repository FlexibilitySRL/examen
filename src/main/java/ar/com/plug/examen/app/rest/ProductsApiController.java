package ar.com.plug.examen.app.rest;

import java.util.Optional;

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

	private ProductServiceImpl prodService;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	@Autowired
	public ProductsApiController(NativeWebRequest request, ProductServiceImpl prodService) {
		this.request = request;
		this.prodService = prodService;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public ResponseEntity<Void> createProduct(@Valid ar.com.plug.generated.model.Product productDTO) {

		log.info("Se recibe una solicitud de creación de producto {}", productDTO);

		Product productEntity = dtoMapper.from(productDTO);

		productEntity = prodService.createProduct(productEntity);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
