package ar.com.plug.examen.app.exception;

import ar.com.plug.examen.app.dtoResponse.ErrorDTO;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -8687529854598474737L;

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

    public ApiException(ErrorDTO errorDTO) {
        super(errorDTO.getCode(), null, true, false);
        this.code = errorDTO.getCode();
        this.httpStatus = HttpStatus.valueOf(Integer.parseInt(errorDTO.getStatus()));
        this.description = errorDTO.getTitle();
    }
}