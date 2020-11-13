package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.InvalidTotalPurhcaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class PurchaseTest {
    @Mock
    private Client aClientMock;

    private Purchase aPurchase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aClientMock = mock(Client.class);
    }

    @Test
    public void aPurchaseWithTotalEqualsTo0ShouldRaiseInvalidTotalPurchaseException(){
        assertThrows(InvalidTotalPurhcaseException.class, ()-> {
            aPurchase = new Purchase(0d,aClientMock);
        });
    }

    @Test
    public void aPurchaseWithTotalLowerThan0ShouldRaiseInvalidTotalPuchcaseException(){
        assertThrows(InvalidTotalPurhcaseException.class, ()-> {
            aPurchase = new Purchase(-2d,aClientMock);
        });
    }

    @Test
    public void creationalPurchaseTest() throws InvalidTotalPurhcaseException {
        aPurchase = new Purchase(150.0, aClientMock);

        assertEquals(aPurchase.getTotal(), 150.0);
        assertEquals(aPurchase.getClient(), aClientMock);
    }

    @Test
    public void whenCreateANewPurchaseHisItemListMustBeEmpty() throws InvalidTotalPurhcaseException {
        aPurchase = new Purchase(150.0, aClientMock);

        assertEquals(aPurchase.getItems().size(), 0);
    }
}
