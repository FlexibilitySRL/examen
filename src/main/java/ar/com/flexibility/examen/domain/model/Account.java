package ar.com.flexibility.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.ALL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = ALL)
    private List<ShippingOrder> shippingOrders;

    public void addItemToCart(LineItem item) {

        if (this.getCart() == null) {
            Cart cart = new Cart();
            cart.getLineItems().add(item);
            this.setCart(cart);
        } else {
            this.getCart().getLineItems().add(item);
        }
    }

    public void addItemToOrder(LineItem item) {

        Optional<ShippingOrder> order = Optional.ofNullable(this.shippingOrders).map(Collection::stream).orElseGet(Stream::empty).filter(order1 -> OrderStatus.NEW == order1.getStatus()).findFirst();

        if (order.isPresent()) {
            order.get().getLineItems().add(item);
        } else {
            ShippingOrder shippingOrder1 = new ShippingOrder();
            shippingOrder1.getLineItems().add(item);
            if (shippingOrders == null) {
                List<ShippingOrder> shippingOrders = new ArrayList<>();
                shippingOrders.add(shippingOrder1);
                this.setShippingOrders(shippingOrders);
            }
            this.getShippingOrders().add(shippingOrder1);
        }

    }

}
