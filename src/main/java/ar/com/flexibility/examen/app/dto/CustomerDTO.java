package ar.com.flexibility.examen.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel(value = "CustomerDTO")
@Getter @Setter @NoArgsConstructor
public class CustomerDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String address;

    public CustomerDTO(Long id , String name, String lastName, String email, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }
}
