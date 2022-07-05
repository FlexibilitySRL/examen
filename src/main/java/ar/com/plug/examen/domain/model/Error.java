package ar.com.plug.examen.domain.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error implements Serializable {

    private static final long serialVersionUID = 7650611080506011916L;
    private String status;
    private String code;
    private String title;

    public static final String PREFIX = "ACCOUNT-";

    public Error(HttpStatus status, String code, String title) {
        this.status = getStatusAsString(status);
        this.code = code;
        this.title = title;
    }

    public Error(HttpStatus status, String title) {
        this(status, getStatusAsString(status), title);
    }

    private static String getStatusAsString(HttpStatus status) {
        return String.valueOf(status.value());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}