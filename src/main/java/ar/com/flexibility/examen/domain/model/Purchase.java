package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private Long id;

    @ManyToOne
    private Client client;

    @OneToOne
    private PurchaseItem purchaseItem;

    private PurchaseStatus status;

    public double getTotal(){
        return purchaseItem.getTotal();
    }

    public Purchase() {
        this.status = PurchaseStatus.IN_PROGRESS;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId(){
        return client.getId();
    }

    public void done() {
        status = PurchaseStatus.DONE;
    }

    public Long getProductId() {
        return purchaseItem.getProductId();
    }

    public Integer getUnits() {
        return purchaseItem.getUnits();
    }

    public PurchaseStatus getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", client=" + client +
                ", purchaseItem=" + purchaseItem +
                ", status=" + status +
                '}';
    }

    public PurchaseItem getItem() {
        return purchaseItem;
    }
}
