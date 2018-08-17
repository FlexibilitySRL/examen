package ar.com.flexibility.examen.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "product"
)
public class Product implements GenericEntity {

    @Id
    private Long id;

    @Column(name = "date_created", nullable = false)
    @Type(type="java.util.Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateCreated;

    @Column(name = "name", nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String name;

    @Column(name = "price", nullable = false)
    @ColumnDefault("false")
    private BigDecimal price;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted = false;

    public Product(){}

    public Product(Long id, Date dateCreated, String name, BigDecimal price) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}