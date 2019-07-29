package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.repository.ShoppingListRepository;
import ar.com.flexibility.examen.domain.service.ShoppingListService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingListServiceImpl implements ShoppingListService {
    private ShoppingListRepository shoppingListRepository;

    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ShoppingList addShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    @Override
    public ShoppingList addProduct(ShoppingList shoppingList, Product product) {
        checkValidParameters(shoppingList, product);

        shoppingList.add(product);

        return updateShoppingList(shoppingList);
    }

    private void checkValidParameters(ShoppingList shoppingList, Product product) {
        if (shoppingList == null) {
            throw new IllegalArgumentException("The shoppingList cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }
    }

    @Override
    public ShoppingList updateShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    @Override
    public ShoppingList findById(Long id) {
        return shoppingListRepository.findOne(id);
    }

    @Override
    public void deleteShoppingList(Long id) {
        shoppingListRepository.delete(id);
    }

    @Override
    public List<ShoppingList> findAll() {
        return (List<ShoppingList>) shoppingListRepository.findAll();
    }
}
