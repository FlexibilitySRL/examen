package ar.com.plug.examen.app.rest;

import java.util.Optional;

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
    public CustomersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    
    @Override
    public ResponseEntity<Void> createCustomer(@Valid ar.com.plug.generated.model.Customer customerDTO) {

    	log.info("Se recibe una solicitud de creación de cliente {}", customerDTO);

    	Customer customerEntity = dtoMapper.from(customerDTO);
    	
    	customerEntity = customerService.createCustomer(customerEntity);
    	
		return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
