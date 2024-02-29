package ar.com.plug.examen.domain.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "sale_products",
            joinColumns = {@JoinColumn(name = "sale_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @Fetch(FetchMode.JOIN)
    private List<Product> products;

    private BigDecimal amount;

    private Boolean approved;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", products=" + products +
                ", amount=" + amount +
                ", approved=" + approved +
                ", client=" + client +
                '}';
    }
}
