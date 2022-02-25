package ar.com.plug.examen.domain.exception;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import org.springframework.http.HttpStatus;

public class CustomerFoundException extends ExerciseApiException {

    public CustomerFoundException() {
        super(ErrorConstants.CUSTOMER_FOUND_ERROR_MESSAGE);
    }

    @Override
    public String getErrorCode() {
        return ErrorConstants.CUSTOMER_FOUND_ERROR_CODE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
