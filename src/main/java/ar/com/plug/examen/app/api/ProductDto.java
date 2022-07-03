package ar.com.plug.examen.app.api;


import ar.com.plug.examen.app.rest.model.Product;

public class ProductDto
{
    private Long id;

    private String description;

    private Boolean active;

    private Integer stock;

    public ProductDto() {
    }

    public ProductDto(Long id, String description, Boolean active, Integer stock) {
        this.id = id;
        this.description = description;
        this.active = active;
        this.stock = stock;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product toProduct() {
        return new Product(this.getId(),this.getDescription(),this.getActive(),this.getStock());
    }
}
