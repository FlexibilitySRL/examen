package ar.com.flexibility.examen.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.flexibility.examen.app.api.ProductApi;

@Entity
@Table(name="products")
public class Product
{
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="product_id", unique=true, nullable=false)
    private Long id;
    
    @Column(name="description", nullable=false)
    private String description;
    
    @Column(name="price", nullable=false)
    private Double price;
    
    @Column(name="stock", nullable=false)
    private Long stock;

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
        if(description != null)
        {
            this.description = description;
        }
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        if(price != null && price > 0D)
        {
            this.price = price;
        }
    }

    public Long getStock()
    {
        return stock;
    }

    public void setStock(Long stock)
    {
        if(stock != null && stock >= 0L)
        {
            this.stock = stock;
        }
    }

    public Product(Long id, String description, Double price, Long stock)
    {
        super();
        this.id = id;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product()
    {
        super();
    }

    public Product(ProductApi productApi)
    {
        if(productApi.getDescription() != null)
        {
            this.description = productApi.getDescription();       
        }
        if(productApi.getPrice() != null && productApi.getPrice() > 0D)
        {
            this.price = productApi.getPrice();       
        }
        if(productApi.getStock() != null && productApi.getStock() >= 0L)
        {
            this.stock = productApi.getStock();       
        }
        
        if(productApi.getId() != null)
        {
            this.id = productApi.getId();       
        }
    }

    @Override
    public String toString()
    {
        return "Product [id=" + id + ", description=" + description + ", price="
                + price + ", stock=" + stock + "]";
    }
    
    
    
    
}
