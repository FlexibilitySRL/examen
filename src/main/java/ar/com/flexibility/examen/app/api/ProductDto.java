package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product toDomain() {
        final Product product = new Product();
        product.setId(getId());
        product.setName(getName());
        product.setDescription(getDescription());
        return product;
    }

    public static ProductDto fromDomain(Product product) {
        final ProductDto productDto = new ProductDto();
        if(product != null) {
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
        }
        return productDto;
    }
}
