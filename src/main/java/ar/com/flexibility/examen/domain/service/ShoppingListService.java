package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.ShoppingList;

import java.util.List;

public interface ShoppingListService {

    ShoppingList addShoppingList(ShoppingList shoppingList);

    ShoppingList updateShoppingList(ShoppingList shoppingList);

    ShoppingList findById(Long id);

    void deleteShoppingList(Long id);

    List<ShoppingList> findAll();
}
