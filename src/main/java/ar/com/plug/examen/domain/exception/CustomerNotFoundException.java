package ar.com.plug.examen.domain.exception;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ExerciseApiException{
    public CustomerNotFoundException() {
        super(ErrorConstants.CUSTOMER_NOT_FOUND_ERROR_MESSAGE);
    }

    @Override
    public String getErrorCode() {
        return ErrorConstants.CUSTOMER_NOT_FOUND_ERROR_CODE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
