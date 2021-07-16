package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NotNull(message = "FirstName may not be null")
    private String firstName;

    @Column(name = "lastName")
    @NotNull(message = "LastName may not be null")
    private String lastName;

    @Column(name = "active")
    private Boolean active = true;

    public Seller() {
    }

    public Seller(Long id, @NotNull(message = "FirstName may not be null") String firstName, @NotNull(message = "LastName may not be null") String lastName, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
