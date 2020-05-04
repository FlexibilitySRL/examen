package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;

import java.util.List;

/**
 *  Shopping Cart Service contract. It exposes the methods that should be
 *  implemented by the more specific classes.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
public interface ShoppingCartService {

    ShoppingCart retrieveCartById(Long id);
    ShoppingCart retrieveOpenCartForClient(Client client);

    List<ShoppingCart> retrieveCarts();
    List<ShoppingCart> retrieveCartsByStatus(boolean completed);

    Long processCart(Client client);

    boolean addProductToCart(Client client, Product product, int quantity);
}
