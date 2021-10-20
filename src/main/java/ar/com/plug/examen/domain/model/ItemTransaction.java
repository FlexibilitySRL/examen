package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "items_transaccion")
public class ItemTransaction {

    @EmbeddedId
    private ItemTransactionPK id;

    @Column(name = "cantidad")
    private Integer amount;

    private Double total;

    @ManyToOne
    @MapsId("transactionId") //  atributo "transactionId de la clase ItemTransactionPK"
    @JoinColumn(name = "id_transaccion", insertable = false, updatable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Product product;

    public ItemTransactionPK getId() {
        return id;
    }

    public void setId(ItemTransactionPK id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
