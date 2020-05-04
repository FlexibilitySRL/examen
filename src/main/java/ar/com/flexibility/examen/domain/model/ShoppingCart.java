package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  ShoppingCart POJO mapped to the carts table in the Db.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Entity(name = "carts")
public class ShoppingCart {

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
    private List<ShoppingCartItem> items;

    private LocalDateTime createdAt;

    private LocalDateTime processedAt;

    private boolean completed;

    public ShoppingCart() { }

    public ShoppingCart(Client client) {
        this.client = client;
        this.seller = client.getSeller();
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.completed = false;
    }

    public ShoppingCart(Client client, Seller seller, List<ShoppingCartItem> items,
                        LocalDateTime createdAt, LocalDateTime processedAt, boolean completed) {
        this.client = client;
        this.seller = seller;
        this.items = items;
        this.createdAt = createdAt;
        this.processedAt = processedAt;
        this.completed = completed;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }
}
