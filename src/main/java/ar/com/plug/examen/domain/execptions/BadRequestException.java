package ar.com.plug.examen.domain.execptions;

import ar.com.plug.examen.domain.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequestException extends ChallengeException {

    public BadRequestException(String code, String message) {
        super(code, HttpStatus.BAD_REQUEST.value(), message);
    }

    public BadRequestException(String code, String message, ErrorDTO data) {
        super(code, HttpStatus.BAD_REQUEST.value(), message, Arrays.asList(data));
    }
}

