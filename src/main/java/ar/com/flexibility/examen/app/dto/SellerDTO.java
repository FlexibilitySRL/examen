package ar.com.flexibility.examen.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Seller")
@Getter
@Setter
@NoArgsConstructor
public class SellerDTO {

    @JsonIgnore
    Long id;
    @ApiModelProperty
    private String name;
    @ApiModelProperty
    private String address;
    @ApiModelProperty
    private String email;

    public SellerDTO(Long id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
