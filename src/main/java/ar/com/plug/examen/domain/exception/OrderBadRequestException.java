package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderBadRequestException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public OrderBadRequestException(String message) {
			super(message);
	}

}
