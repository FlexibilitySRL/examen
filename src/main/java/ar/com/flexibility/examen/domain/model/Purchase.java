package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.core.model.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "purchases")
public class Purchase extends AbstractEntity {
    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PurchaseItem> items;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private PurchaseStatus status;

    public Set<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(Set<PurchaseItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }
}
