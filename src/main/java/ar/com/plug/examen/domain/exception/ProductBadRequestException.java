package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductBadRequestException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public ProductBadRequestException(String message) {
			super(message);
	}

}
