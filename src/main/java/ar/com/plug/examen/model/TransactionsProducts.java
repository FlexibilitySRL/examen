package ar.com.plug.examen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class TransactionsProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "long")
    private  Long value;
    @Column(columnDefinition = "long")
    private  Long id_product;
    @Column(columnDefinition = "long")
    private  Long quantity;

    public TransactionsProducts(@JsonProperty("value") Long value,
                                @JsonProperty("id_product") Long id_product,
                                @JsonProperty("quantity")Long quantity) {

        this.value = value;
        this.id_product = id_product;
        this.quantity = quantity;

    }

    public TransactionsProducts() {

    }

    public Integer getId() {
        return id;
    }

    public Long getValue() {
        return value;
    }

    public Long getId_product() {
        return id_product;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "TransactionsProducts{" +
                "id=" + id +
                ", value=" + value +
                ", id_product=" + id_product +
                ", quantity=" + quantity +
                '}';
    }
}
