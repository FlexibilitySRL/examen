package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "transaction_product")
@NoArgsConstructor
public class TransactionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productTransactionId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "transactionId", nullable = false)
    @JsonIgnore
    private Transaction transaction;

    public TransactionProduct(Product product, Double amount, Transaction transaction) {
        this.product = product;
        this.amount = amount;
        this.transaction = transaction;
    }
}
