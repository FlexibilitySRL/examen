/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author msulbara
 */
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

//    @ManyToMany(mappedBy = "products")
//    private Set<Transaction> trasactions = new HashSet<>();

    public Product() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

//    public Set<Transaction> getTrasactions() {
//        return trasactions;
//    }
//
//    public void setTrasactions(Set<Transaction> trasactions) {
//        this.trasactions = trasactions;
//    }

}
