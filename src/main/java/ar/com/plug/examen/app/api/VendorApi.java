package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorApi {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "ssn is required")
    private String ssn;
}
