package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
@JsonRootName(value = "client")
public class ClientApi {

    private Long id;
    @Pattern(regexp="[a-zA-Z]+")
    @NotEmpty
    private String name;

    public ClientApi() {
    }

    public ClientApi(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
