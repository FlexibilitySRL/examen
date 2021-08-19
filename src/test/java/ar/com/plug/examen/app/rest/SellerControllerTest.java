package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.SellerModel;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @Mock
    private SellerService sellerService;

    @Test
    void request_add_seller_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        SellerModel sellerModel = new SellerModel(1L, "diego", 1);
        expectedJsonResponseTransaction.setSellerModel(sellerModel);

        when(sellerService.addSeller(any(SellerModel.class))).thenReturn(expectedJsonResponseTransaction);
        SellerController sellerController = new SellerController(sellerService);

        ResponseEntity<JsonResponseTransaction> result = sellerController.addSeller(sellerModel);

        verify(sellerService).addSeller(sellerModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_update_seller_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        SellerModel sellerModel = new SellerModel(1L, "diego", 1);
        expectedJsonResponseTransaction.setSellerModel(sellerModel);

        when(sellerService.updateSeller(any(SellerModel.class))).thenReturn(expectedJsonResponseTransaction);
        SellerController sellerController = new SellerController(sellerService);

        ResponseEntity<JsonResponseTransaction> result = sellerController.updateSeller(sellerModel);

        verify(sellerService).updateSeller(sellerModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_delete_seller_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setResponseMessage("Seller: "+1L + " eliminated of system");

        when(sellerService.deleteSeller(1L)).thenReturn(expectedJsonResponseTransaction);
        SellerController sellerController = new SellerController(sellerService);

        ResponseEntity<JsonResponseTransaction> result = sellerController.deleteSeller(1L);

        verify(sellerService).deleteSeller(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

}