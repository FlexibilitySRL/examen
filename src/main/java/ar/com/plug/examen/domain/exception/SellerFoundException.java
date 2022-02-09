package ar.com.plug.examen.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FOUND)
public class SellerFoundException extends RuntimeException
{
	static final long serialVersionUID = 1L;

	public SellerFoundException(String message) {
			super(message);
	}

}
