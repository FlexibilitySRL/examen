package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.repository.ShoppingListRepository;
import ar.com.flexibility.examen.domain.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingListServiceImpl implements ShoppingListService {
    private Logger logger = LoggerFactory.getLogger("ar.com.flexibility.examen.domain.service.impl.ShoppingListServiceImpl");
    private ShoppingListRepository shoppingListRepository;

    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ShoppingList addShoppingList(ShoppingList shoppingList) {
        ShoppingList savedShoppingList = shoppingListRepository.save(shoppingList);

        checkServiceStatus(savedShoppingList,"The shopping list was added succesfully.","An error ocurred while trying to add the shopping list.");

        return savedShoppingList;
    }

    private void checkServiceStatus(ShoppingList updatedShoppingList, String infoMessage, String warningMessage) {
        if (updatedShoppingList != null) {
            logger.info(infoMessage);
        } else {
            logger.warn(warningMessage);
        }
    }

    @Override
    public ShoppingList addProduct(ShoppingList shoppingList, Product product) {
        checkValidParameters(shoppingList, product);

        shoppingList.add(product);
        ShoppingList updatedShoppingList = updateShoppingList(shoppingList);

        checkServiceStatus(updatedShoppingList,"The product was added successfully", "An error occurred while trying to add product to the list");

        return updatedShoppingList;
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
        ShoppingList updatedShoppingList = shoppingListRepository.save(shoppingList);

        checkServiceStatus(updatedShoppingList, "The shopping list was updated successfully", "An error ocurred while trying to update the shopping list");

        return updatedShoppingList;
    }

    @Override
    public ShoppingList findById(Long id) {
        ShoppingList searchedShoppingList = shoppingListRepository.findOne(id);

        checkServiceStatus(searchedShoppingList, "The shopping list was searched succsessfully", "An error ocurred while trying to search the shopping list");

        return searchedShoppingList;
    }

    @Override
    public void deleteShoppingList(Long id) {
        shoppingListRepository.delete(id);

        ShoppingList searchedShoppingList = shoppingListRepository.findOne(id);

        checkSuccessfullyDelete(searchedShoppingList);
    }

    private void checkSuccessfullyDelete(ShoppingList searchedShoppingList) {
        if (searchedShoppingList == null) {
            logger.info("The shopping list was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the shopping list");
        }
    }

    @Override
    public List<ShoppingList> findAll() {
        List<ShoppingList> allShoppingLists = (List<ShoppingList>) shoppingListRepository.findAll();

        checkSuccessfullyFindAll(allShoppingLists);

        return allShoppingLists;
    }

    private void checkSuccessfullyFindAll(List<ShoppingList> allShoppingLists) {
        if (allShoppingLists == null) {
            logger.info("The shopping lists was find successfully");
        } else {
            logger.warn("An error ocurred while searching the shopping lists");
        }
    }
}
