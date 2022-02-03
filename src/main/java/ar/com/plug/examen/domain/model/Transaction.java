package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tlb_transaction")
@AllArgsConstructor
@Builder
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_transaction")
    private String numberTransaction;

    private String description;


    private Character type;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    private boolean approved = false;

    @Transient
    private Double total;


    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private List<TransactionItem> items;

    private String state;

    public Transaction(){
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }



    public Double getTotal(){
        if(this.items.isEmpty()){
            return (double) 0;
        } else {
            return this.items.stream().map(TransactionItem::getSubTotal).reduce(0.0, Double::sum);
        }
    }

}