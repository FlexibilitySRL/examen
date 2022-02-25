package ar.com.plug.examen.domain.exception;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import org.springframework.http.HttpStatus;

public class SellerInvalidDeleteException extends ExerciseApiException{
    public SellerInvalidDeleteException() {
        super(ErrorConstants.SELLER_FOUND_RELATION_ERROR_MESSAGE);
    }
    @Override
    public String getErrorCode() {
        return ErrorConstants.SELLER_FOUND_RELATION_ERROR_CODE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}