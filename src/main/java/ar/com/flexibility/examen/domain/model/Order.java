package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Seller seller;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private float subtotal;

    private float commissionRate;

    private float total;

    private LocalDateTime createdAt;

    public Order() { }

    public Order(Client client, Seller seller) {
        this.client = client;
        this.seller = seller;
        this.items = new ArrayList<>();
        this.commissionRate = seller.getCommissionRate();
        this.createdAt = LocalDateTime.now();
    }

    public Order(Client client, Seller seller, List<OrderItem> items, float subtotal, float commissionRate, float total, LocalDateTime createdAt) {
        this.client = client;
        this.seller = seller;
        this.items = items;
        this.subtotal = subtotal;
        this.commissionRate = commissionRate;
        this.total = total;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
