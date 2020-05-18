package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.core.ControllerUtils;
import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.model.BaseObject;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Base controller defining common methods in entity controllers
 *
 * @param <E> the object class on which the controller is based, extending from {@link BaseObject}
 * @param <D> the object class for Id
 *
 */
@Log4j
@Controller
public abstract class BaseController<E extends BaseObject, D> {


	abstract ResponseEntity<E> create(D requestBody) throws BaseObjectNotFoundException;
	abstract ResponseEntity<E> getById(Long id) throws BaseObjectNotFoundException;
	abstract ResponseEntity<List<E>> getAll();
	abstract ResponseEntity<String> delete(Long id) throws BaseObjectNotFoundException;
	abstract ResponseEntity<E> update(Long id, D entityDTO) throws BaseObjectNotFoundException;


	/**
	 * Exception handler for {@link BaseObjectNotFoundException}
	 * @return a string with the error message
	 */
	@ExceptionHandler({BaseObjectNotFoundException.class})
	public ResponseEntity<String> handleNotFound() {
		return new ResponseEntity<>(ControllerUtils.responseMessage("entity not found"), HttpStatus.NOT_FOUND);
	}


	/**
	 * Exception handler for general Exceptions
	 * @return a string with the error message
	 */
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> handleGenericException() {
		return new ResponseEntity<>(ControllerUtils.responseMessage("Internal server error, please contact administrator"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
