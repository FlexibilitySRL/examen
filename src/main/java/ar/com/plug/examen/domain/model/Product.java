package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class to represent the Product
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "brand")
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
        if (name.isEmpty()){
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
}
