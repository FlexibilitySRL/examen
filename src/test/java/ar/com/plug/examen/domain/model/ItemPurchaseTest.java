package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.InvalidProductIdException;
import ar.com.plug.examen.domain.exceptions.InvalidQuantityException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemPurchaseTest {

    ItemPurchase anItem;

    @Test
    public void anItemWithQuantityEquals0ShouldRaiseInvalidQuantityException(){
        assertThrows(InvalidQuantityException.class, ()-> {
            anItem = new ItemPurchase(0l, 1l);
        });
    }

    @Test
    public void anItemWithQuantityLowerThan0ShouldRaiseInvalidQuantityException(){
        assertThrows(InvalidQuantityException.class, ()-> {
            anItem = new ItemPurchase(-1l, 1l);
        });
    }

    @Test
    public void anItemWithProductIDEquals0ShouldRaiseInvalidProductIdException(){
        assertThrows(InvalidProductIdException.class, ()-> {
            anItem = new ItemPurchase(1l, 0l);
        });
    }

    @Test
    public void anItemWithProductIDLowerThan0ShouldRaiseInvalidProductIdException(){
        assertThrows(InvalidProductIdException.class, ()-> {
            anItem = new ItemPurchase(1l, -1l);
        });
    }

    @Test
    public void creationalItemPurchaseTest() throws InvalidQuantityException, InvalidProductIdException {
        anItem = new ItemPurchase(5l, 1l);
        assertEquals(anItem.getProductId(), 1l);
        assertEquals(anItem.getQuantity(), 5l);
    }
}
