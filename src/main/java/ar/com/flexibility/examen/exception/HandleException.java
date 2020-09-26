package ar.com.flexibility.examen.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.flexibility.examen.rest.api.model.ErrorDTO;

@ControllerAdvice
public class HandleException {
	private final Logger logger = LoggerFactory.getLogger(HandleException.class);

	@ExceptionHandler(ProductException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorDTO productException(ProductException productException) {
		logger.debug("ProductException - {}", productException.getMessage());
		return createError(HttpStatus.PRECONDITION_FAILED, productException.getMessage());
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorDTO numberFormatException(NumberFormatException numberFormatException) {
		logger.debug("NumberFormatException - {}", numberFormatException.getMessage());
		return createError(HttpStatus.PRECONDITION_FAILED, numberFormatException.getMessage());
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorDTO sqlException(SQLException sqlException) {
		logger.debug("SqlException - {}", sqlException.getMessage());
		return createError(HttpStatus.PRECONDITION_FAILED, "Inconvenientes en la base de datos");
	}

	@ExceptionHandler(ClientException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorDTO clientException(ClientException clientException) {
		logger.debug("ClientException - {}", clientException.getMessage());
		return createError(HttpStatus.PRECONDITION_FAILED, clientException.getMessage());
	}

	@ExceptionHandler(InvoiceException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorDTO invoiceException(InvoiceException invoiceException) {
		logger.debug("InvoiceException - {}", invoiceException.getMessage());
		return createError(HttpStatus.PRECONDITION_FAILED, invoiceException.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDTO> processRuntimeException(HttpServletRequest req, RuntimeException ex)
			throws Exception {
		ErrorDTO error = new ErrorDTO();
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			error.setMessage(ex.getMessage());
			error.setCode(responseStatus.value().value());
		} else {
			error.setMessage(ex.getMessage());
			error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		logger.debug(error.toString());
		return new ResponseEntity<ErrorDTO>(error, HttpStatus.valueOf(error.getCode()));
	}

	/**
	 * Genera el objeto de error a devolver
	 * 
	 * @param status
	 * @param message
	 * @return
	 */
	private ErrorDTO createError(HttpStatus status, String message) {
		ErrorDTO error = new ErrorDTO();
		error.setCode(status.value());
		error.setMessage(message);
		logger.debug("Error to return -> {}", error);
		return error;
	}
}
