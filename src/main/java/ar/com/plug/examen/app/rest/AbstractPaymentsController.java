package ar.com.plug.examen.app.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;

public class AbstractPaymentsController {
	
	@Autowired
	protected MessageSourceProvider messageSourceProvider;
	
	private static final String MESSAGE = "message";
	private static final String ERROR_MESSAGE = "error";
	
	
	
	protected ResponseEntity<?> buildResponseCreate(String entityName, Object entity) {
		String message = messageSourceProvider.get(BusinessExceptionConstants.CREATE_SUCCESS, new String[]{entityName});
		return this.buildResponse(entityName, entity, message, null, HttpStatus.CREATED);
	}
	
	protected ResponseEntity<?> buildResponseUpdate(String entityName, Object entity) {
		String message = messageSourceProvider.get(BusinessExceptionConstants.UPDATE_SUCCESS, new String[]{entityName});
		return this.buildResponse(entityName, entity, message, null, HttpStatus.CREATED);
	}
	
	protected ResponseEntity<?> buildResponseDelete(String entityName) {
		String message = messageSourceProvider.get(BusinessExceptionConstants.DELETE_SUCCESS, new String[]{entityName});
		return this.buildResponse(entityName, null, message, null, HttpStatus.OK);
	}
	
	protected ResponseEntity<?> buildResponseError(String message, Exception e, HttpStatus status) {
		return buildResponse(null, null, message, e, status);
	}
	
	protected ResponseEntity<?> buildResponseGet(Object entity) {
		return new ResponseEntity<Object>(entity, HttpStatus.OK);
	}
	
	protected ResponseEntity<?> buildResponseValidation(BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<String> errors = result.getFieldErrors()
				.stream()
				.map(err -> messageSourceProvider.get(BusinessExceptionConstants.FIELD_VALIDATION, new String[]{err.getField(), err.getDefaultMessage()}))
				.collect(Collectors.toList()); 				
		response.put(ERROR_MESSAGE, errors);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<?> buildResponse(String entityName, Object entity, String message, Exception e, HttpStatus status) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (message != null) {
			response.put(MESSAGE, message);
		}
		if (entity != null) {
			response.put(entityName, entity);
		}
		if (e != null) {
			response.put(ERROR_MESSAGE, e.getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}