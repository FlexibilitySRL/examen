package ar.com.flexibility.examen.domain.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Sell.
 */
@Entity
@Table(name = "sell")
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public Sell product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Seller getSeller() {
        return seller;
    }

    public Sell seller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Client getClient() {
        return client;
    }

    public Sell client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sell sell = (Sell) o;
        if (sell.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sell.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sell{" +
            "id=" + getId() +
            "}";
    }
}
