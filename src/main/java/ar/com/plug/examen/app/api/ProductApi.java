package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductApi {

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "price is required")
    private Double price;
}
