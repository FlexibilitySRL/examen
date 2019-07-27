package ar.com.flexibility.examen.model;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurcharseTest {
    private Client client;
    private Purcharse purcharse;
    private ShoppingList shoppingList;

    @Before
    public void initializer() {
        client = new Client("Roberto", "Lazarreta");
        purcharse = new Purcharse();
        shoppingList = new ShoppingList();

        shoppingList.add(new Product("lentes",BigDecimal.valueOf(7500.49)));
        shoppingList.add(new Product("casco",BigDecimal.valueOf(7500.50)));

    }

    @Test
    public void calculatePrice_withTwoValidItems_returnPrice() {
        purcharse.buy(shoppingList,client);

        assertEquals(BigDecimal.valueOf(15000.99), purcharse.getCost());
    }

    @Test
    public void buy_withTwoValidItems() {
        purcharse.buy(shoppingList,client);

        assertEquals(purcharse,client.getPurcharses().get(0));
        assertEquals("APPROVED",purcharse.getState());
    }
}
