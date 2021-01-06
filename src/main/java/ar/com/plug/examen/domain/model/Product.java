package ar.com.plug.examen.domain.model;


import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product")
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Date created;
    private Integer discount;
    private Boolean deleteProduct = false;

    private Product(){}

    public static class Builder {

        private Product product;

        public Builder() {
            product = new Product();
        }

        public Builder withId(final Long id) {
            product.id = id;
            return this;
        }

        public Builder withName(final String name){
            product.name = name;
            return this;
        }

        public Builder withBrand(final String brand) {
            product.brand = brand;
            return this;
        }

        public Builder withPrice(final Double price) {
            product.price = price;
            return this;
        }

        public Builder withDiscount(final Integer discount){
            product.discount = discount;
            return this;
        }

        public Builder withCreated(final Date created) {
            product.created = created;
            return this;
        }


        public Product build(){
            return product;
        }
    }


}
