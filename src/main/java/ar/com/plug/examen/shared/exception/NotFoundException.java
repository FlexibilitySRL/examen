package ar.com.plug.examen.shared.exception;

import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class NotFoundException extends RuntimeException {

    private final ResponseDto responseDto;

}
