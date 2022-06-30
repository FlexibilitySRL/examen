package ar.com.plug.examen.domain.execptions;

import ar.com.plug.examen.domain.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InternalServerErrorException extends ChallengeException {

    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(String code, String message, ErrorDTO data) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
    }
}

