package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductApi {

    private Long id;
    private String name;
    private String description;
    private Double price;
}
