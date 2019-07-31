package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
public class Purcharse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "purcharse_id")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purcharse_id")
    private List<Transaction> transactions;
    private BigDecimal cost;
    private PurcharseEnum status;

    public Purcharse() {
        products = new ArrayList<>();
        transactions = new ArrayList<>();
        cost = BigDecimal.valueOf(0);
        status = PurcharseEnum.valueOf("PENDING");
    }

    public void add(Product product) {
        products.add(product);

        cost = cost.add(product.getPrice());
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void delete(Product product) {
        Iterator<Product> products = this.products.iterator();

        while (products.hasNext()) {
            Product nextProduct = products.next();
            if (product.equals(nextProduct)) {

                cost = cost.subtract(nextProduct.getPrice());

                products.remove();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(PurcharseEnum status) {
        this.status = status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purcharse purcharse = (Purcharse) o;
        return Objects.equals(id, purcharse.id) &&
                Objects.equals(products, purcharse.products) &&
                Objects.equals(transactions, purcharse.transactions) &&
                Objects.equals(cost, purcharse.cost) &&
                status == purcharse.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, transactions, cost, status);
    }


}
