package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "purchase_orders")
@Getter @Setter
public class PurchaseOrder {

    public enum Status {
        NEW, ACCEPTED, REVOKED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status;


    public PurchaseOrder() {
    }

    public PurchaseOrder(Client client, Product product, Integer amount) {
        this.client = client;
        this.product = product;
        this.amount = amount;

        this.status = Status.NEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return getId().equals(that.getId()) &&
                getClient().equals(that.getClient()) &&
                getProduct().equals(that.getProduct()) &&
                getAmount().equals(that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClient(), getProduct(), getAmount());
    }
}
