package ar.com.plug.examen.infrastructure.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.plug.examen.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer inventory;
    private Double price;
    @Column(unique = true)
    private String barCode;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .inventory(inventory)
                .price(price)
                .barCode(barCode)
                .build();
    }

    public ProductEntity(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.inventory = product.getInventory();
        this.price = product.getPrice();
        this.barCode = product.getBarCode();
    }

    public ProductEntity upDate(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.inventory = product.getInventory();
        this.price = product.getPrice();
        this.barCode = product.getBarCode();
        return this;
    }

}
