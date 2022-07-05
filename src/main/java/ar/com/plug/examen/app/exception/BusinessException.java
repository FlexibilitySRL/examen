package ar.com.plug.examen.app.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -987348324732264324L;

    private final String payload;
    private final int httpStatus;

    public BusinessException(String payload, int httpStatus) {
        super(payload, null, true, false);
        this.payload = payload;
        this.httpStatus = httpStatus;
    }
}
