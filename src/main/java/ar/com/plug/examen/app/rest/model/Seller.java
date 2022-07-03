package ar.com.plug.examen.app.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private Boolean active;


    public Seller() {
    }

    public Seller(Long id, String description, Boolean active) {
        this.id = id;
        this.name = description;
        this.active = active;
    }

    public Seller(String description, Boolean active) {
        this.name =description;
        this.active=active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

