package ar.com.flexibility.examen.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(
        name = "product"
)
public class Product implements GenericEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_created", nullable = false)
    @Type(type="java.util.Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateCreated;

    @Column(name = "name", nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String name;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted = false;

    public Product(){}

    public Product(Long id, Date dateCreated, @NotNull String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}