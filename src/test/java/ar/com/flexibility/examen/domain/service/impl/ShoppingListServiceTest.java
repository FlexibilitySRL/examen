package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.service.ShoppingListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingListServiceTest {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ShoppingListServiceImpl shoppingListService;
    private ShoppingList shoppingList;
    private Product product1;
    private Product product2;

    @Before
    public void initializer() {
        shoppingList = new ShoppingList();
        product1 = new Product("Cheddar", BigDecimal.valueOf(150.25));
        product2 = new Product("Shampoo", BigDecimal.valueOf(130));

        shoppingList.add(product1);
        shoppingList.add(product2);
    }

    @Test
    public void addShoppingList_withValidsProducs_returnsShoppingList() {
        ShoppingList savedShoppingList = shoppingListService.addShoppingList(this.shoppingList);

        assertNotNull(savedShoppingList);
        assertEquals(2, savedShoppingList.getProducts().size());
        assertEquals("Cheddar", savedShoppingList.getProducts().get(0).getName());
    }

    @Test
    public void updateShoppingList_wihtValidProducProducs_returnsUpdatedShoppingList() {
        ShoppingList savedShoppingList = shoppingListService.addShoppingList(this.shoppingList);
        savedShoppingList.getProducts().get(0).setName("Mayonesa");
        savedShoppingList.getProducts().get(1).setPrice(BigDecimal.valueOf(120));
        ShoppingList updatedShoppingList = shoppingListService.updateShoppingList(savedShoppingList);

        assertNotNull(updatedShoppingList);
        assertEquals(2, updatedShoppingList.getProducts().size());
        assertEquals("Mayonesa", updatedShoppingList.getProducts().get(0).getName());
        assertEquals(BigDecimal.valueOf(120), updatedShoppingList.getProducts().get(1).getPrice());
    }

    @Test
    public void findById_withExistingId_returnsSearchedShoppingList() {
        ShoppingList savedShoppingList = shoppingListService.addShoppingList(this.shoppingList);

        ShoppingList searchedShoppingList = shoppingListService.findById(savedShoppingList.getId());

        assertNotNull(searchedShoppingList);
        assertEquals(2, searchedShoppingList.getProducts().size());
        assertEquals("Cheddar", searchedShoppingList.getProducts().get(0).getName());
    }

    @Test
    public void deleteShoppingList_withExistingProduct() {
        ShoppingList savedShoppingList = shoppingListService.addShoppingList(this.shoppingList);

        shoppingListService.deleteShoppingList(savedShoppingList.getId());

        assertNull(shoppingListService.findById(savedShoppingList.getId()));
    }

}
