package ar.com.plug.examen.domain.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
	 *  The exceptions extends of Serializable, we give a serial to keep the good practices
	 */
	private static final long serialVersionUID = 1027973213498875163L;

	public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

