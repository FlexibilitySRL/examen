package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
			super(message);
	}

}
