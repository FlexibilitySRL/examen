package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transacciones")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private String customerId;

    @Column(name = "id_vendedor")
    private Long sellerId;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "estado")
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", insertable = false, updatable = false)
    private Seller seller;

    @OneToMany(mappedBy = "transaction", cascade = {CascadeType.ALL})
    private List<ItemTransaction> itemsTransaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

   public List<ItemTransaction> getItemsTransaction() {
        return itemsTransaction;
    }

    public void setItemsTransaction(List<ItemTransaction> itemsTransaction) {
        this.itemsTransaction = itemsTransaction;
    }



  /*  public String toString(){



        String t = "Id: " + this.idTransaction + ", CustomerId: " + this.customerId
                   + ", SellerId: " + this.getSellerId()
                   + ", Date: " + this.date
                   + ", state: " + this.state;

        StringBuilder dt = new StringBuilder();

        for (ItemTransaction item: this.itemsTransaction) {

            dt.append( "product: " + item.getId().getProductId()
                      + ", amount: " + item.getAmount()
                      + ", total: " + item.getTotal());
        }
        return t + "\n" + dt.toString();
    }*/
}
