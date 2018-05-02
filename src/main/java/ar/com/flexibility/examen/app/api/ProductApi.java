package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.flexibility.examen.domain.model.Product;

@JsonRootName(value = "product") // The @JsonRootName annotation is used – if
                                 // wrapping is enabled – to specify the name of
                                 // the root wrapper to be used.
@JsonIgnoreProperties(ignoreUnknown = false)
public class ProductApi
{
    @JsonProperty
    private Long   id;

    @JsonProperty
    private String description;

    @JsonProperty
    private Double price;

    @JsonProperty
    private Long   stock;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Long getStock()
    {
        return stock;
    }

    public void setStock(Long stock)
    {
        this.stock = stock;
    }

    public ProductApi(Long id, String description, Double price, Long stock)
    {
        super();
        this.id = id;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public ProductApi()
    {
        super();
    }

    public ProductApi(Product product)
    {
        if(product.getDescription() != null) 
        {
            this.description = product.getDescription();
        }
        if(product.getPrice() != null) 
        {
            this.price = product.getPrice();
        }
        if(product.getStock() != null) 
        {
            this.stock = product.getStock();
        }
        if(product.getId() != null) 
        {
            this.id = product.getId();
        }

    }

    @Override
    public String toString()
    {
        return "ProductApi [id=" + id + ", description=" + description
                + ", price=" + price + ", stock=" + stock + "]";
    }
    
    

}
