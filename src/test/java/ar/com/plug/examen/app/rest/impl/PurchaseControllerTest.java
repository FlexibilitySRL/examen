package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.PurchaseDTO;
import ar.com.plug.examen.app.dtoResponse.ListPurchaseResponseDTO;
import ar.com.plug.examen.app.dtoResponse.PurchaseResponseDTO;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.service.IPurchaseRequestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PurchaseControllerTest {

    @InjectMocks
    private PurchaseController purchaseController;

    @Mock
    private IPurchaseRequestService purchaseRequestService;

    private PurchaseResponseDTO purchaseResponseDTO;
    private PurchaseDTO purchaseDTO;
    private Long receiptId;
    private ListPurchaseResponseDTO listPurchaseResponseDTO;

    @BeforeEach
    void setUp() {
        purchaseDTO = new PurchaseDTO();
        purchaseResponseDTO = PurchaseResponseDTO.builder().build();
        listPurchaseResponseDTO = ListPurchaseResponseDTO.builder().build();
        receiptId = 1L;
    }

    @Test
    void addPurchaseRequest() throws ApiException {
        try {
            given(purchaseRequestService.create(purchaseDTO)).willReturn(purchaseResponseDTO);
            ResponseEntity<PurchaseResponseDTO> response = purchaseController.addPurchaseRequest(purchaseDTO);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void updatePurchaseRequest() throws ApiException {
        try {
            given(purchaseRequestService.approve(receiptId)).willReturn(purchaseResponseDTO);
            ResponseEntity<PurchaseResponseDTO> response = purchaseController.approvePurchaseRequest(receiptId);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void removePurchaseRequest() throws ApiException {
        try {
            given(purchaseRequestService.cancel(receiptId)).willReturn(purchaseResponseDTO);
            ResponseEntity<PurchaseResponseDTO> response = purchaseController.cancelPurchaseRequest(receiptId);
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }

    @Test
    void listPurchaseRequest() throws ApiException {
        try {
            given(purchaseRequestService.list()).willReturn(listPurchaseResponseDTO);
            ResponseEntity<ListPurchaseResponseDTO> response = purchaseController.listPurchaseRequest();
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
        } catch (Exception e) {
            Assertions.fail("Should never throw an exception: ", e);
        }
    }
}
