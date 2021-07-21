package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item implements Serializable {
    private Long id;
    private Transac transac;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal sub;

    public Item() {
    }

    public Item(Long id, Transac transac, Product product, Integer quantity, BigDecimal price, BigDecimal sub) {
        this.id = id;
        this.transac = transac;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.sub = sub;
    }

    public Item(Product product, Integer quantity, BigDecimal price, BigDecimal sub) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.sub = sub;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "transac_id")
    public Transac getTransac() {
        return transac;
    }

    public void setTransac(Transac transac) {
        this.transac = transac;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSub() {
        return sub;
    }

    public void setSub(BigDecimal sub) {
        this.sub = sub;
    }
}
