package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.domain.enums.TransactionStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
public class Transaction {

    //simplificar a lista de compras que tuvo un vendedor

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private Long productId;
    @Column
    private Long sellerId;
    @Column
    private ZonedDateTime date;
    @Column
    private Double price;
    @Column
    private String status = TransactionStatus.PENDING.getCode();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", productId=" + productId +
                ", sellerId=" + sellerId +
                ", date=" + date +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
