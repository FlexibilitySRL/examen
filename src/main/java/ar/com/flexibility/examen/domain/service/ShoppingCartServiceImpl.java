package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.model.ShoppingCartItem;
import ar.com.flexibility.examen.domain.repository.ShoppingCartRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);

    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
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
    public ShoppingCart retrieveOpenCartForClient(Client client) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClientId(client);

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
    public boolean processCart(Client client) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClientId(client);

        if (shoppingCart == null) {
            logger.trace(String.format("Could not retrieve cart for the client with id %s", client.getId()));
            return false;
        }

        if (shoppingCart.getItems().size() == 0) {
            return false;
        }

        shoppingCart.setProcessedAt(LocalDateTime.now());
        shoppingCart.setCompleted(true);

        shoppingCartRepository.save(shoppingCart);

        // TODO: Wrap this in a transaction.
        // TODO: Generate an order ID.

        return true;
    }

    @Override
    public boolean addProductToCart(Client client, Product product, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.getOpenShoppingCartByClientId(client);

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
}
