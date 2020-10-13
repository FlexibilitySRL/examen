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

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.IClientService;
import ar.com.plug.examen.domain.utils.PaymentsSubsystemTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Clients CRUD")
@RestController
@RequestMapping(path = "/clients")
public class ClientController extends AbstractPaymentsController {
	
	
	@Autowired
	private IClientService clientService;
	
	@ApiOperation(value = "Find all clients")
	@GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> list() {
		List<Client> clientList = clientService.findAll();
		return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getClientApiList(clientList));
	}
	
	@ApiOperation(value = "Find a client", notes = "Return a client by id")
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {

			Client client = clientService.findById(id);
			if (client == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getClientApi(client)); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create a Client", notes = "Create a new Client")
	@PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@Valid @RequestBody ClientApi clientApi, BindingResult result) {
		try {
			
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
		
			Client client = PaymentsSubsystemTranslator.getInstance().getClient(clientApi);
		
			clientApi = PaymentsSubsystemTranslator.getInstance().getClientApi(clientService.save(client));
			return buildResponseCreate(PaymentsConstants.CLIENT_ENTITY, clientApi);
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update a Client", notes = "Modify a Client by id")
	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> update(@Valid @RequestBody ClientApi clientApi, BindingResult result, @PathVariable Long id ) {
		try {
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			Client persistedClient = clientService.findById(id);
			if (persistedClient == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			persistedClient.setName(clientApi.getName());
			persistedClient.setSurname(clientApi.getSurname());
			persistedClient.setEmail(clientApi.getEmail());
			
			Client updatedClient = clientService.save(persistedClient);
		
			clientApi = PaymentsSubsystemTranslator.getInstance().getClientApi(updatedClient);
			return buildResponseUpdate(PaymentsConstants.CLIENT_ENTITY, clientApi);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Delete a Client", notes = "Delete a Client by id")
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			clientService.delete(id);
			return buildResponseDelete(PaymentsConstants.CLIENT_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
