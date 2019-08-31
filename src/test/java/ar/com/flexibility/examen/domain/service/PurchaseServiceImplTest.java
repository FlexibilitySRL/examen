package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.repository.PurchaseItemRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.impl.PurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
class PurchaseServiceImplTest {

    private PurchaseRepository purchaseRepository = Mockito.mock(PurchaseRepository.class);
    private PurchaseItemRepository itemRepository = Mockito.mock(PurchaseItemRepository.class);

    @InjectMocks
    private PurchaseService service;

    @BeforeEach
    void initUseCase() {
        service = new PurchaseServiceImpl(purchaseRepository,itemRepository);
    }

    @Test
    void findById() {

        Client client = new Client();
        client.setId(31251409L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        PurchaseItem item = new PurchaseItem(3L,10,50.0);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setClient(client);
        purchase.setPurchaseItem(item);

        when(purchaseRepository.findOne(1L)).thenReturn(purchase);

        Purchase purchaseFound = service.findById(1L);

        verify(purchaseRepository).findOne(1L);
        verifyNoMoreInteractions(purchaseRepository);

        assertEquals(purchaseFound,purchase);
        assertEquals(purchaseFound.getId(),purchase.getId());
        assertEquals(purchaseFound.getClientId(),purchase.getClientId());
        assertEquals(purchaseFound.getProductId(),purchase.getProductId());
        assertEquals(purchaseFound.getTotal(),purchase.getTotal(),0.01);
        assertEquals(purchaseFound.getUnits(),purchase.getUnits());
    }

    @Test
    void deleteById() {
        service.deleteById(43L);
        verify(purchaseRepository).delete(43L);
        verifyNoMoreInteractions(purchaseRepository);
    }

    @Test
    void create() {
        Client client = new Client();
        client.setId(31251409L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        PurchaseItem item = new PurchaseItem(3L,10,50.0);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setClient(client);
        purchase.setPurchaseItem(item);

        when(purchaseRepository.save(purchase)).thenReturn(purchase);

        Purchase saved = service.create(purchase);

        verify(itemRepository).save(item);
        verifyNoMoreInteractions(itemRepository);
        verify(purchaseRepository).save(purchase);
        verifyNoMoreInteractions(purchaseRepository);

        assertNotNull(saved);
        assertEquals(saved.getId(),purchase.getId());
        assertEquals(saved.getClientId(),purchase.getClientId());
        assertEquals(saved.getProductId(),purchase.getProductId());
        assertEquals(saved.getTotal(),purchase.getTotal(),0.01);
        assertEquals(saved.getUnits(),purchase.getUnits());
    }

    @Test
    void update() {
        Client client = new Client();
        client.setId(31251409L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        PurchaseItem item = new PurchaseItem(3L,10,50.0);

        Purchase original = new Purchase();
        original.setId(1L);
        original.setClient(client);
        original.setPurchaseItem(item);

        Purchase toUpdate = new Purchase();
        toUpdate.setId(1L);
        toUpdate.setClient(client);
        toUpdate.setPurchaseItem(item);

        when(purchaseRepository.findOne(1L)).thenReturn(original);
        when(purchaseRepository.save(original)).thenReturn(original);

        Purchase updated = service.update(toUpdate);

        verify(purchaseRepository).findOne(1L);
        verify(purchaseRepository).save(original);
        verifyNoMoreInteractions(purchaseRepository);

        assertNotNull(updated);
        assertEquals(updated.getStatus(), PurchaseStatus.DONE);
    }

    @Test
    void updatePurchaseThatDoesNotExist() {
        Purchase toUpdate = new Purchase();
        toUpdate.setId(1L);

        when(purchaseRepository.findOne(1L)).thenReturn(null);

        Purchase updated = service.update(toUpdate);

        verify(purchaseRepository).findOne(1L);
        verifyNoMoreInteractions(purchaseRepository);

        assertNull(updated);
    }

    @Test
    void findAll() {
        Client client = new Client();
        client.setId(31251409L);
        client.setName("alberto");
        client.setEmail("albertok@gmail.com");

        PurchaseItem item = new PurchaseItem(3L,10,50.0);

        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);
        purchase1.setClient(client);
        purchase1.setPurchaseItem(item);

        Purchase purchase2 = new Purchase();
        purchase2.setId(1L);
        purchase2.setClient(client);
        purchase2.setPurchaseItem(item);

        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        when(purchaseRepository.findAll()).thenReturn(purchases);

        List<Purchase> found = service.findAll();

        verify(purchaseRepository).findAll();
        verifyNoMoreInteractions(purchaseRepository);

        assertEquals(found,purchases);
    }
}