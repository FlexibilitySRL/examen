package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerApi {

    private Long id;
    private String name;
}
