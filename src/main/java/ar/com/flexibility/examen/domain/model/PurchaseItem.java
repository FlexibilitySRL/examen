package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private Long id;

    private Long productId;
    private Integer units;
    private double price;

    public PurchaseItem(Long productId, Integer units, double price) {
        this.productId = productId;
        this.units = units;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getUnits(){
        return units;
    }

    public Long getProductId(){
        return productId;
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", units=" + units +
                ", price=" + price +
                '}';
    }

    @Transient
    public double getTotal(){
        return units * price;
    }
}
