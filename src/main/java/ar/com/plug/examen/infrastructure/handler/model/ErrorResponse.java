package ar.com.plug.examen.infrastructure.handler.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    private String message;

    private List<String> details;
}
