package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurcharseServiceImplTest {

    @Autowired
    private PurcharseServiceImpl purcharseService;
    @Autowired
    private ClientServiceImpl clientService;

    private ShoppingList shoppingList;
    private Client client;
    private Product product1;
    private Product product2;
    private Purcharse purcharse;

    @Before
    public void initializr() {
        shoppingList = new ShoppingList();
        purcharse = new Purcharse();

        client = new Client("Rapaleta", "Centenario");
        shoppingList.add(new Product("Ajedrez", BigDecimal.valueOf(300.99)));
        shoppingList.add(new Product("Ping Pong", BigDecimal.valueOf(200)));

        purcharse.buy(shoppingList, client);
    }

    @Test
    public void addPurcharse_validPurcharse_returnsPurcharse() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        clientService.addClient(client);

        assertEquals(client.getPurcharses().get(0), savedPurcharse);
        assertNotNull(savedPurcharse.getShoppingList());
        assertEquals(2, savedPurcharse.getShoppingList().getProducts().size());
        assertEquals(BigDecimal.valueOf(500.99),savedPurcharse.getCost());
        assertEquals("APPROVED",savedPurcharse.getState());
    }

    @Test
    public void updatePurcharse_validPurcharse_returnsUpdatedPurcharse() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);
        savedPurcharse.getShoppingList().add(new Product("Pan",BigDecimal.valueOf(100)));

        Purcharse updatedPurcharse = purcharseService.updatePurcharse(savedPurcharse);

        assertNotNull(updatedPurcharse);
        assertEquals(3, updatedPurcharse.getShoppingList().getProducts().size());
        assertEquals(savedPurcharse.getShoppingList().getProducts().get(0), updatedPurcharse.getShoppingList().getProducts().get(0));
    }

    @Test
    public void findById_withExistingId_returnsSearchedProduct() {
        Purcharse savedPurcharse = purcharseService.addPurcharse(this.purcharse);

        Purcharse searchedPurcharse = purcharseService.findById(savedPurcharse.getId());

        assertNotNull(searchedPurcharse);
        assertEquals(savedPurcharse.getShoppingList().getProducts().size(),searchedPurcharse.getShoppingList().getProducts().size());
        assertEquals(savedPurcharse.getId(),searchedPurcharse.getId());
        assertEquals(savedPurcharse.getCost(),searchedPurcharse.getCost());
        assertEquals(savedPurcharse.getState(),searchedPurcharse.getState());
    }

    @Test
    public void deleteProduct_withExistingProduct() {
        Purcharse savedPucharse = purcharseService.addPurcharse(this.purcharse);

        purcharseService.deletePurcharse(savedPucharse.getId());

        assertNull(purcharseService.findById(savedPucharse.getId()));
    }
}
