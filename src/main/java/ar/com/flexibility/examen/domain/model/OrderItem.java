package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *  OrderItem POJO mapped to the order_items table in the Db.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    private int quantity;

    private float price;

    private float total;

    public OrderItem() { }

    public OrderItem(Order order, Product product, int quantity, float price, float total) {
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
