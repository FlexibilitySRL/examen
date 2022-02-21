package ar.com.plug.examen.domain.exception;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PurchaseParamException extends ExerciseApiException{
    public PurchaseParamException() {
        super();
    }

    public PurchaseParamException(String message, List<String> parameters) {
        super(message, parameters);
    }

    @Override
    public String getErrorCode() {
        return ErrorConstants.INVALID_PRODUCT_PARAMETERS_ERROR_CODE;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
