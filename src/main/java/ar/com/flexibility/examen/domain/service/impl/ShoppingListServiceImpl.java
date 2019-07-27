package ar.com.flexibility.examen.domain.service.impl;

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
