package ar.com.plug.examen.domain.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -39243947736814371L;
    private List<Error> errors;

    public ErrorResponse() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
    }

    public ErrorResponse(HttpStatus status, String code, String title) {
        this();
        addError(status, code, title);
    }

    public ErrorResponse(HttpStatus status) {
        this(status, String.valueOf(status.value()), status.getReasonPhrase());
    }

    public ErrorResponse(HttpStatus status, String title) {
        this(status, String.valueOf(status.value()), title);
    }

    private void addError(Error error) {
        errors.add(error);
    }

    public void addError(HttpStatus status, String title) {
        addError(new Error(status, title));
    }

    private void addError(HttpStatus status, String code, String title) {
        addError(new Error(status, code, title));
    }

    public String getStatus(int index) {
        return errors.get(index).getStatus();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.resolve(Integer.parseInt(getStatus(0)));
    }
}