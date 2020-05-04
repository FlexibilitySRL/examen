package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.*;
import ar.com.flexibility.examen.domain.repository.OrderRepository;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);

    private ShoppingCartRepository shoppingCartRepository;

    private OrderRepository orderRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   OrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ShoppingCart retrieveCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartById(id);

        if (shoppingCart == null) {
            logger.trace(String.format("Could not retrieve cart with id %s", id));
        }

        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> retrieveCarts() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart retrieveOpenCartForClient(Client client) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClient(client);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(client);

            shoppingCartRepository.save(shoppingCart);
        }

        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> retrieveCartsByStatus(boolean completed) {
        return shoppingCartRepository.getShoppingCartsByCompleted(completed);
    }

    @Override
    public Long processCart(Client client) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClient(client);

        if (shoppingCart == null) {
            logger.trace(String.format("Could not retrieve cart for the client with id %s", client.getId()));
            return 0L;
        }

        if (shoppingCart.getItems().size() == 0) {
            return 0L;
        }

        try {
            shoppingCart.setProcessedAt(LocalDateTime.now());
            shoppingCart.setCompleted(true);

            shoppingCartRepository.save(shoppingCart);

            // TODO: Wrap this in a transaction.
            Order newOrder = generateOrderFromShoppingCart(shoppingCart);
            orderRepository.save(newOrder);

            return newOrder.getId();
        } catch (Exception e) {
            logger.trace(String.format(
                    "Could not process the cart %s for the client %s. Exception: %s",
                    shoppingCart.getId(), client.getId(), e.getMessage()));
            return 0L;
        }
    }

    @Override
    public boolean addProductToCart(Client client, Product product, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClient(client);

        if (shoppingCart == null) {
            logger.trace(String.format("Could not retrieve cart for the client with id %s", client.getId()));
            return false;
        }

        if (quantity < 0) {
            logger.trace(String.format(
                    "Client %s attempted to add a negative number to the cart. Check API implementation.",
                    client.getId()));
            return false;
        }

        try {
            for (ShoppingCartItem item : shoppingCart.getItems()) {
                if (item.getProduct().equals(product)) {
                    if (quantity == 0)
                    {
                        shoppingCart.getItems().remove(item);
                    }
                    else {
                        if (item.getQuantity() <= product.getStock()) {
                            int newStock = product.getStock() - quantity;

                            product.setStock(newStock);
                            item.setNewTotal(quantity);
                        }
                    }

                    shoppingCartRepository.save(shoppingCart);
                    return true;
                }
            }

            ShoppingCartItem item = new ShoppingCartItem(shoppingCart, product, quantity, product.getPrice());

            shoppingCart.getItems().add(item);
            shoppingCartRepository.save(shoppingCart);

            // TODO: Wrap this in a transaction.

            return true;
        } catch (Exception e) {
            logger.trace(String.format(
                    "Could not add the product %s to the cart for the client %s. Exception: %s",
                    product.getId(), client.getId(), e.getMessage()));
            return false;
        }
    }

    private Order generateOrderFromShoppingCart(ShoppingCart shoppingCart) {
        Order newOrder = new Order(shoppingCart.getClient(), shoppingCart.getSeller());

        float subtotal = 0;

        for (ShoppingCartItem item : shoppingCart.getItems()) {
            OrderItem orderItem = new OrderItem(newOrder, item.getProduct(),
                    item.getQuantity(), item.getPrice(), item.getTotal());

            subtotal += item.getTotal();

            newOrder.getItems().add(orderItem);
        }

        newOrder.setSubtotal(subtotal);
        newOrder.setTotal(subtotal + subtotal * (newOrder.getCommissionRate() / 100));

        return newOrder;
    }
}
