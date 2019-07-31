package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime transactionTime;

    public Transaction() {}

    public void authorize(Purcharse purcharse) {
        purcharse.setStatus(PurcharseEnum.valueOf("APPROVED"));

        transactionTime = LocalDateTime.now();

        purcharse.addTransaction(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
