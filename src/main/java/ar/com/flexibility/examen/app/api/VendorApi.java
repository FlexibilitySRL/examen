package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@JsonRootName(value = "vendor")
public class VendorApi {

    private Long id;
    @Pattern(regexp="[a-zA-Z]+")
    private String name;

    public VendorApi() {
    }

    public VendorApi(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
