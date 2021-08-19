package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.Customer;
import ar.com.plug.examen.domain.dto.PaymentType;
import ar.com.plug.examen.domain.dto.Seller;
import ar.com.plug.examen.domain.model.CustomerModel;
import ar.com.plug.examen.domain.model.SaleModel;
import ar.com.plug.examen.domain.service.SaleService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
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
class SaleControllerTest {

    @Mock
    private SaleService saleService;


    @Test
    void request_add_sale_model_and_response_a_json_response_transaction(){
        SaleModel saleModel = new SaleModel();

        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setSaleModel(saleModel);

        when(saleService.addSale(any(SaleModel.class))).thenReturn(saleModel);

        SaleController saleController = new SaleController(saleService);

        ResponseEntity<JsonResponseTransaction> result = saleController.addSale(saleModel);

        verify(saleService).addSale(saleModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void request_update_sale_model_and_response_a_json_response_transaction(){
        SaleModel saleModel = new SaleModel();
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setSaleModel(saleModel);

        when(saleService.updateSale(any(SaleModel.class))).thenReturn(saleModel);
        SaleController saleController = new SaleController(saleService);

        ResponseEntity<JsonResponseTransaction> result = saleController.updateSale(saleModel);

        verify(saleService).updateSale(saleModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void request_delete_sale_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setResponseMessage("sale: "+1L + " eliminated of system");

        when(saleService.deleteSale(1L)).thenReturn(StatusTransaction.DELETE);
        SaleController saleController = new SaleController(saleService);

        ResponseEntity<JsonResponseTransaction> result = saleController.deleteSale(1L);

        verify(saleService).deleteSale(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}