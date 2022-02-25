package ar.com.plug.examen.domain.exception;

import java.util.List;
import org.springframework.http.HttpStatus;

public abstract class ExerciseApiException extends RuntimeException {

    private List<String> parameters;

    protected ExerciseApiException() {
    }

    protected ExerciseApiException(final String message) {
        super(message);
    }

    public ExerciseApiException(String message, List<String> parameters) {
        super(message);
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public abstract String getErrorCode();

    public abstract HttpStatus getStatus();
}
