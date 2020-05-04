package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart retrieveCartById(Long id);
    ShoppingCart retrieveOpenCartForClient(Client client);

    List<ShoppingCart> retrieveCarts();
    List<ShoppingCart> retrieveCartsByStatus(boolean completed);

    Long processCart(Client client);

    boolean addProductToCart(Client client, Product product, int quantity);
}
