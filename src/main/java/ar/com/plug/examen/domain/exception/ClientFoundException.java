package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FOUND)
public class ClientFoundException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public ClientFoundException(String message) {
			super(message);
	}

}
