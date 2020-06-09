package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Transaction_purchases")
public class TransactionPurchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="purchase_id")
    private List<Product> product = new ArrayList<Product>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private Customer customer;

    private Double total;

    private Date buyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "TransactionPurchases{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                ", total=" + total +
                ", buyDate=" + buyDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionPurchases that = (TransactionPurchases) o;
        return id.equals(that.id) &&
                transactionId.equals(that.transactionId) &&
                product.equals(that.product) &&
                customer.equals(that.customer) &&
                total.equals(that.total) &&
                Objects.equals(buyDate, that.buyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionId, product, customer, total, buyDate);
    }
}
