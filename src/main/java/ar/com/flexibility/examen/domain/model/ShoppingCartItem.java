package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *  ShoppingCartItem POJO mapped to the cart_items table in the Db.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Entity(name = "cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ShoppingCart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    private int quantity;

    private float price;

    private float total;

    public ShoppingCartItem() { }

    public ShoppingCartItem(ShoppingCart cart, Product product, int quantity, float price) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }

    public ShoppingCartItem(ShoppingCart cart, Product product, int quantity, float price, float total) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public ShoppingCartItem(Long id, ShoppingCart cart, Product product, int quantity, float price, float total) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setNewTotal(int quantity) {
        this.quantity += quantity;
        this.total = this.quantity * this.price;
    }
}
