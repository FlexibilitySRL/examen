package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.FlexibilityException;
import ar.com.flexibility.examen.app.exception.FlexibilityNotFoundException;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.service.dto.PurchaseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

    private static final Long DEFAULT_ID = 1L;

    @Mock
    private PurchaseService purchaseServiceMock;

    @Mock
    private PurchaseDTO purchaseDTOMock;

    @Test
    public void findAll() {
        ArrayList<PurchaseDTO> list = new ArrayList<>();
        list.add(purchaseDTOMock);
        when(purchaseServiceMock.findAll()).thenReturn(list);
        assertFalse(ObjectUtils.isEmpty(purchaseServiceMock.findAll()));
    }

    @Test
    public void findAllWithoutResults() {
        when(purchaseServiceMock.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, purchaseServiceMock.findAll().size());
    }

    @Test
    public void findOne() {
        when(purchaseServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.of(purchaseDTOMock));
        assertTrue(purchaseServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void findOneNotFound() {
        when(purchaseServiceMock.findOne(DEFAULT_ID)).thenReturn(Optional.empty());
        assertFalse(purchaseServiceMock.findOne(DEFAULT_ID).isPresent());
    }

    @Test
    public void approve() {
        when(purchaseDTOMock.getPurchaseStatus()).thenReturn(PurchaseStatus.APPROVED);
        when(purchaseServiceMock.approve(DEFAULT_ID)).thenReturn(purchaseDTOMock);

        PurchaseDTO purchaseDTO = purchaseServiceMock.approve(DEFAULT_ID);

        assertEquals(purchaseDTO.getPurchaseStatus(), PurchaseStatus.APPROVED);
    }

    @Test(expected = FlexibilityException.class)
    public void approveAlready() {
        when(purchaseServiceMock.approve(DEFAULT_ID)).
                thenThrow(new FlexibilityException("Purchase with id".concat(DEFAULT_ID.toString()).concat(" already approved")));
        purchaseServiceMock.approve(DEFAULT_ID);
    }

    @Test(expected = FlexibilityNotFoundException.class)
    public void approveNotFound() {
        when(purchaseServiceMock.approve(DEFAULT_ID)).
                thenThrow(new FlexibilityNotFoundException("Purchase with id ".concat(DEFAULT_ID.toString()).concat(" not found")));
        purchaseServiceMock.approve(DEFAULT_ID);
    }

}