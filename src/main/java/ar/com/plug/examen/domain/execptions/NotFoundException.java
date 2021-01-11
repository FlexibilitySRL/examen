package ar.com.plug.examen.domain.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	/**
	 *  The exceptions extends of Serializable, we give a serial to keep the good practices
	 */
	private static final long serialVersionUID = -2135406537003424131L;

	public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

