package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public ClientException(String message) {
			super(message);
	}

}
