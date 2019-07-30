package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ShoppingListApi;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ShoppingListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    @Autowired
    private ShoppingListServiceImpl shoppingListService;
    @Autowired
    private ProductServiceImpl productService;


    @GetMapping
    public List<ShoppingList> showShoppingLists() {
        return shoppingListService.findAll();
    }

    @PostMapping
    private void addShopingList(@RequestBody ShoppingList shoppingList) {
        ShoppingList updatedShoppingList = shoppingListService.addShoppingList(shoppingList);
    }



    @PostMapping("/product")
    private void addProductToShoppingList(@RequestBody ShoppingListApi shoppingListApi) {
        String shoppingListId = shoppingListApi.getShoppingListId();
        ShoppingList searchedShoppingList = shoppingListService.findById(Long.parseLong(shoppingListId));

        Long productId = Long.parseLong(shoppingListApi.getProductId());
        Product searchedProduct = productService.findById(productId);

        ShoppingList updatedShoppingList = shoppingListService.addProduct(searchedShoppingList, searchedProduct);
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
