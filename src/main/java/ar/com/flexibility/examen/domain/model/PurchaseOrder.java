package ar.com.flexibility.examen.domain.model;


import javax.persistence.*;



@Entity(name = "purchase_orders")
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





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
