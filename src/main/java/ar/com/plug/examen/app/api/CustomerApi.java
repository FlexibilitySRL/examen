package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@JsonRootName(value = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CustomerApi {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull
    @NotBlank
    private String name;

    @JsonProperty
    @NotNull
    @NotBlank
    private String lastName;

    @JsonProperty
    @NotNull
    @NotBlank
    @Size(max = 1,min = 1)
    private String sex;

    @JsonProperty
    @NotNull
    @NotBlank
    private String age;

    @JsonProperty
    @NotNull
    @NotBlank
    private String document;

    @JsonProperty
    @NotNull
    @NotBlank
    private String address;

    @JsonProperty
    @NotNull
    @NotBlank
    @Email
    private String email;


}
