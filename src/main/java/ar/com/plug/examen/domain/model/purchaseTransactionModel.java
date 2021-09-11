package ar.com.plug.examen.domain.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchaseTransactions")
public class purchaseTransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double total;

    private boolean approval;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private clientModel client;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private productModel product;

    @ManyToOne
    @JoinColumn(name = "idseller")
    private sellerModel seller;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public clientModel getClient() {
        return client;
    }

    public void setClient(clientModel client) {
        this.client = client;
    }

    public productModel getProduct() {
        return product;
    }

    public void setProduct(productModel product) {
        this.product = product;
    }

    public sellerModel getSeller() {
        return seller;
    }

    public void setSeller(sellerModel seller) {
        this.seller = seller;
    }
}
