package ar.com.flexibility.examen.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "ProductDTO")
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    @JsonIgnore
    private Long id;
    @ApiModelProperty
    private String description;
    @ApiModelProperty
    private String model;
    @ApiModelProperty
    private String brand;
    @ApiModelProperty
    private int stock;


    public ProductDTO(Long id, String description, String model, String brand, int stock) {
        this.id = id;
        this.description = description;
        this.model = model;
        this.brand = brand;
        this.stock = stock;
    }
}


