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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "type")
    private TransactionType type;

    @Column(name = "status")
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name = "vendorId", nullable = false)
    @JsonIgnore
    private Vendor vendor;

//    @Column(name = "client", insertable = false, updatable = false)
//    private Long client_id;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Set<TransactionProduct> products;

    public Transaction(Date transactionDate, TransactionType type, TransactionStatus status, Client client, Vendor vendor) {
        this.transactionDate = transactionDate;
        this.type = type;
        this.status = status;
        this.client = client;
        this.vendor = vendor;
//        this.client_id = client.getClient_id();
    }
}
