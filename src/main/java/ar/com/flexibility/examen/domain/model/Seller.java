package ar.com.flexibility.examen.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 *  Seller POJO mapped to the sellers table in the Db.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Entity(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private float commissionRate;

    public Seller() { }

    public Seller(String firstName, String lastName, float commissionRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.commissionRate = commissionRate;
    }

    public Seller(Long id, String firstName, String lastName, float commissionRate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.commissionRate = commissionRate;
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

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
    }
}
