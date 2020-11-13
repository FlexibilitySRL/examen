package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class to represent the Product
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name="product")
@ApiModel("Model product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "the product's id", required = true)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(value = "the product's name", required = true)
    private String name;

    @Column(name = "price")
    @ApiModelProperty(value = "the product's price", required = true)
    private Double price;

    @Column(name = "brand")
    @ApiModelProperty(value = "the product's brand", required = true)
    private String brand;

    public Product(){}

    public Product(String name, Double price, String brand) throws EmptyNameException, InvalidPriceException, EmptyBrandException {
        this.validateName(name);
        this.validatePrice(price);
        this.validateBrand(brand);
    }

    private void validateName(String name) throws EmptyNameException {
        if (name.isEmpty()){
            throw new EmptyNameException("The product name should have a name.");
        }
        this.name = name;
    }

    private void validatePrice(Double price) throws InvalidPriceException {
        if (price<=0.0){
            throw new InvalidPriceException("The product price should greater than 0.");
        }
        this.price = price;
    }

    private void validateBrand(String brand) throws EmptyBrandException {
        if (brand.isEmpty()){
            throw new EmptyBrandException("The product name should have a brand.");
        }
        this.brand = brand;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getBrand() {
        return this.brand;
    }

    public Long getId(){ return this.id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        return brand.equals(product.brand);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + brand.hashCode();
        return result;
    }
}
