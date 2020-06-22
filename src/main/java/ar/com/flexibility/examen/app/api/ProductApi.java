package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@JsonRootName(value = "product")
public class ProductApi {

    private Long id;
    @NotEmpty
    private String name;
    private String description;

    public ProductApi() {
    }

    public ProductApi(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
