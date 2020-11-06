package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    @Column(columnDefinition = "varchar(255)")
    private  String state_order;
    @Column(columnDefinition = "varchar(255)")
    private  String dateOrder;
    @Column(columnDefinition = "long")
    private  Long idClient;
    @Column(columnDefinition = "long")
    private  Long idSeller;
    @OneToMany(targetEntity = TransactionsProducts.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_fk", referencedColumnName = "id")
    private List<TransactionsProducts> transactionsProducts;

    public Transactions(@JsonProperty("state_order") State state_order,
                        @JsonProperty("dateOrder") String dateOrder,
                        @JsonProperty("idClient") Long idClient,
                        @JsonProperty("idSeller") Long idSeller,
                        @JsonProperty("transactionsProducts") List<TransactionsProducts> transactionsProducts) {
        this.state_order = state_order.toString();
        this.dateOrder = dateOrder;
        this.idClient = idClient;
        this.idSeller = idSeller;
        this.transactionsProducts = transactionsProducts;
    }

    public Transactions() {

    }

    public Integer getId() {
        return id;
    }

    public String getState_order() {
        return state_order;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public Long getIdClient() {
        return idClient;
    }

    public Long getIdSeller() {
        return idSeller;
    }

    public List<TransactionsProducts> getTransactionsProducts() {
        return transactionsProducts;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", state_order=" + state_order +
                ", dateOrder='" + dateOrder + '\'' +
                ", idClient=" + idClient +
                ", idSeller=" + idSeller +
                ", transactionsProducts=" + transactionsProducts +
                '}';
    }

    public enum State{
        INPROCESS,
        APPROVED,
        REJECTED
    }
}
