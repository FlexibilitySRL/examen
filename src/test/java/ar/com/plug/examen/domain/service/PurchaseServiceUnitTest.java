package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.repository.PurchaseRepository;
import ar.com.plug.examen.domain.exceptions.PurchaseDoesNotExistException;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class PurchaseServiceUnitTest {
    @Mock
    private PurchaseRepository repository;
    @Mock
    Purchase aPurchaseMock;

    @InjectMocks
    private PurchaseServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAPurchaseTest(){
        when(repository.save(any(Purchase.class))).thenReturn(aPurchaseMock);
        service.savePurchase(aPurchaseMock);
        verify(repository, times(1)).save(aPurchaseMock);

    }

    @Test
    public void getPurchaseByIdTest() throws PurchaseDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aPurchaseMock));
        service.findById(1l);
        verify(repository, times(1)).findById(1l);
    }

    @Test
    public void updatePurchaseTest() throws PurchaseDoesNotExistException {
        when(repository.findById(1l)).thenReturn(Optional.of(aPurchaseMock));
        when(aPurchaseMock.getId()).thenReturn(1l);

        service.updatePurchase(aPurchaseMock);
        verify(repository, times(1)).findById(1l);
        verify(repository, times(1)).save(aPurchaseMock);
    }

    @Test
    public void deletePurchaseTest(){
        when(repository.findById(1l)).thenReturn(Optional.of(aPurchaseMock));

        service.deletePurchase(1l);
        verify(repository, times(1)).delete(aPurchaseMock);
    }
}
