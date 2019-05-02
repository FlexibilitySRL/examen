package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@JsonRootName(value = "Product")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Datos del Producto")
public class ProductApi {

    @JsonProperty
    @ApiModelProperty(value = "ID", position = 0)
    private Long id;

    @JsonProperty
    @ApiModelProperty(value = "Descripción", position = 1)
    private String description;

    @JsonProperty
    @ApiModelProperty(value = "Precio", position = 2)
    private BigDecimal price;


    public ProductApi(){}

    public ProductApi(Product product){
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
