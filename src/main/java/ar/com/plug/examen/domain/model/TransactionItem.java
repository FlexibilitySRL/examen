package ar.com.plug.examen.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(name = "tbl_transaction_items")
@AllArgsConstructor
@Builder
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Positive(message = "Quantity must be positive")
    private Double quantity;
    private Double  price;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if(this.price > 0 && this.quantity > 0){
            return this.quantity*this.price;
        } else {
            return (double) 0;
        }
    }

    public TransactionItem(){
        this.quantity = (double) 0;
        this.price = (double) 0;
    }
}
