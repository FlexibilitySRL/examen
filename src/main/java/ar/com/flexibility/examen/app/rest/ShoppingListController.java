package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ShoppingListApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ShoppingListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    @Autowired
    private ShoppingListServiceImpl shoppingListService;
    @Autowired
    private ProductServiceImpl productService;
    private Logger logger = Logger.getLogger("ar.com.flexibility.examen.app.rest.ShoppingListController");

    @GetMapping
    public List<ShoppingList> showShoppingLists() {
        return shoppingListService.findAll();
    }

    @PostMapping
    private void addShopingList(@RequestBody ShoppingList shoppingList) {
        ShoppingList updatedShoppingList = shoppingListService.addShoppingList(shoppingList);

       checkServiceStatus(updatedShoppingList,"The shopping list was added succesfully.","An error ocurred while trying to add the shopping list.");
    }

    private void checkServiceStatus(ShoppingList updatedShoppingList, String infoMessage, String warningMessage) {
        if (updatedShoppingList != null) {
            logger.log(Level.INFO, infoMessage);
        } else {
            logger.log(Level.WARNING, warningMessage);
        }
    }

    @PostMapping("/product")
    private void addProductToShoppingList(@RequestBody ShoppingListApi shoppingListApi) {
        String shoppingListId = shoppingListApi.getShoppingListId();
        ShoppingList searchedShoppingList = shoppingListService.findById(Long.parseLong(shoppingListId));

        Long productId = Long.parseLong(shoppingListApi.getProductId());
        Product searchedProduct = productService.findById(productId);

        ShoppingList updatedShoppingList = shoppingListService.addProduct(searchedShoppingList, searchedProduct);
        checkServiceStatus(updatedShoppingList,"The product was added successfully", "An error occurred while trying to add product to the list");
    }


    @PutMapping
    private void updateShoppingList(@RequestBody ShoppingList shoppingList) {
        shoppingListService.updateShoppingList(shoppingList);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteShoppingList(@PathVariable Long id) {
        shoppingListService.deleteShoppingList(id);
    }

}
