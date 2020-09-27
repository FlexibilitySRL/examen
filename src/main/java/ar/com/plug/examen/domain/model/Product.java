package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "products")
@Getter @Setter
public class Product {

    @Id
    @Column(unique = true)
    private String cod;

    private String name;
    private String description;
    private Double price;

    private Integer stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getCod().equals(product.getCod()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod(), getName(), getDescription(), getPrice());
    }
}
