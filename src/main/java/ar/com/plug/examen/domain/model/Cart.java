package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a shopping cart.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cart {

    @Id
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;
    @OneToOne
    private Customer customer;
    @OneToOne
    @Setter
    private Purchase purchase;

    /**
     * Constructs a new cart with the given items, customer, and purchase.
     */
    public Cart(List<CartItem> items, Customer customer, Purchase purchase) {
        this.id = UUID.randomUUID();
        this.items = (items != null) ? items : new ArrayList<>();
        this.customer = customer;
        this.purchase = purchase;
    }


    /**
     * Adds a new item to the cart with the given product and quantity.
     */
    public void addItem(Product product, Integer quantity) {
        for (CartItem existingItem : items) {
            if (existingItem.getProduct().equals(product)) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }


    public void updateItem(Product product, Integer quantity) {
        for (Cart.CartItem existingItem : items) {
            if (existingItem.product.equals(product)) {
                existingItem.quantity = quantity;
                return;
            }
        }

        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);
    }

    public void DeleteItem(Product product) {
        for (Cart.CartItem existingItem : items) {
            if (existingItem.product.equals(product)) {
                items.remove(existingItem);
                return;
            }
        }
    }

    /**
     * Represents an item in the cart.
     */
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        @OneToOne
        private Product product;
        @Setter
        private Integer quantity;

        public CartItem(Product product, Integer quantity) {
            this.id = UUID.randomUUID();
            this.product = product;
            this.quantity = quantity;
        }
    }

}
