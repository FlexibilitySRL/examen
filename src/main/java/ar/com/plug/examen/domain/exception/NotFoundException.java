package ar.com.plug.examen.domain.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.plug.examen.domain.service.Messages;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger(NotFoundException.class);

	public NotFoundException(String msg) {
		super(msg);
	}
	
	public static NotFoundException unableToFindException(String entity) throws NotFoundException {
		String errorMsg = String.format(Messages.MSG_EXCEPTION_UNABLE_TO_FIND, entity);
		logger.error(errorMsg);
		return new NotFoundException(errorMsg);
	}
}
