package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.impl.CustomerServiceImpl;
import ar.com.plug.generated.api.CustomersApi;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-29T10:08:36.293-03:00[America/Argentina/Buenos_Aires]")

@Slf4j
@Controller
@RequestMapping("${openapi.paymentManagement.base-path:}")
public class CustomersApiController implements CustomersApi {

	private final NativeWebRequest request;

	private CustomerServiceImpl customerService;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	@org.springframework.beans.factory.annotation.Autowired
	public CustomersApiController(NativeWebRequest request, CustomerServiceImpl customerService) {
		this.request = request;
		this.customerService = customerService;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	/**
	 * Creates the customer.
	 *
	 * @param customerDTO the customer DTO
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Customer> createCustomer(
			ar.com.plug.generated.model.@Valid Customer customerDTO) {
		log.info("Se recibe una solicitud de creación de cliente {}", customerDTO);

		Customer customerEntity = dtoMapper.from(customerDTO);

		customerEntity = customerService.createCustomer(customerEntity);
		customerDTO = dtoMapper.from(customerEntity);

		return ResponseEntity.ok().body(customerDTO);
	}

	/**
	 * Delete customer.
	 *
	 * @param customerId the customer id
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Void> deleteCustomer(Integer customerId) {
		customerService.deleteCustomer(customerId.longValue());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Update customer.
	 *
	 * @param customerId  the customer id
	 * @param customerDTO the customer DTO
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Customer> updateCustomer(Integer customerId,
			ar.com.plug.generated.model.@Valid Customer customerDTO) {
		log.info("Se recibe una solicitud de actualización de cliente {}", customerDTO);

		Customer customerEntity = dtoMapper.from(customerDTO);

		customerEntity = customerService.updateCustomer(customerId.longValue(), customerEntity);
		customerDTO = dtoMapper.from(customerEntity);

		return ResponseEntity.ok().body(customerDTO);
	}

	/**
	 * Gets the customer.
	 *
	 * @param customerId the customer id
	 * @return the customer
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Customer> getCustomer(Integer customerId) {
		return ResponseEntity.ok().body(dtoMapper.from(customerService.getCustomer(customerId.longValue())));
	}

	/**
	 * Gets the customers.
	 * 
	 * TODO: Admitir filtros de búsqueda.
	 * 
	 * TODO: Paginar
	 * 
	 * TODO: Ordenar
	 * 
	 * @return the customers
	 */
	@Override
	public ResponseEntity<List<ar.com.plug.generated.model.Customer>> getCustomers() {

		List<ar.com.plug.generated.model.Customer> customerList = customerService.getCustomers().stream()
				.map((customerEntity) -> dtoMapper.from(customerEntity)).collect(Collectors.toList());

		return ResponseEntity.ok(customerList);

	}

}