package ar.com.flexibility.examen.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "incoming_transaction"
)
public class Transaction implements GenericEntity {

    @Id
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "date_created", nullable = false)
    @Type(type="java.util.Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateCreated;

    @OneToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private BigDecimal amount;

    @Column
    @ColumnDefault("false")
    private Boolean approved = false;

    private Transaction() {
        //Just to avoid public instantiation.
    }

    public Transaction(Long id, Long transactionId, Date dateCreated, Client client, Product product, BigDecimal amount) {
        this.id = id;
        this.transactionId = transactionId;
        this.dateCreated = dateCreated;
        this.client = client;
        this.product = product;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Boolean isDeleted() {
        return false;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        //Just to implement the interface.
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
}
