package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "transacs")
public class Transac {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Set<Item> items;
    private Seller seller;
    private Customer customer;
    private BigDecimal amount;
    private Boolean approved = false;
    private Boolean active = true;

    public Transac() {
    }

    public Transac(Long id, Set<Item> items, Seller seller, Customer customer, BigDecimal amount, Boolean approved, Boolean active) {
        this.id = id;
        this.items = items;
        this.seller = seller;
        this.customer = customer;
        this.amount = amount;
        this.approved = approved;
        this.active = active;
    }

    public Transac(Set<Item> items, Seller seller, Customer customer, BigDecimal amount, Boolean approved) {
        this.items = items;
        this.seller = seller;
        this.customer = customer;
        this.amount = amount;
        this.approved = approved;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "transac", orphanRemoval = true)
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id")
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
