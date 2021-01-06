package ar.com.plug.examen.app.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductApi {


    @JsonProperty
    private Long id;
    @JsonProperty
    @NotNull
    @NotBlank
    private String name;
    @JsonProperty
    @NotNull
    @NotBlank
    private String brand;
    @JsonProperty
    @NotNull
    private Double price;
    @JsonProperty
    @NotNull
    private Integer discount;

}
