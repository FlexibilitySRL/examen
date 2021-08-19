package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.service.ProductService;
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
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Test
    void request_add_product_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        ProductModel productModel = new ProductModel(1L, "Zapato Nike", "zapatos", 7000.0, 10000.0, 1 , 1);
        expectedJsonResponseTransaction.setProductModel(productModel);

        when(productService.addProduct(any(ProductModel.class))).thenReturn(expectedJsonResponseTransaction);
        ProductController productController = new ProductController(productService);

        ResponseEntity<JsonResponseTransaction> result = productController.addProduct(productModel);

        verify(productService).addProduct(productModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_update_product_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        ProductModel productModel = new ProductModel(1L, "Zapato Nike", "zapatos", 7000.0, 10000.0, 1 , 1);
        expectedJsonResponseTransaction.setProductModel(productModel);

        when(productService.updateProduct(any(ProductModel.class))).thenReturn(expectedJsonResponseTransaction);
        ProductController productController = new ProductController(productService);

        ResponseEntity<JsonResponseTransaction> result = productController.updateProduct(productModel);

        verify(productService).updateProduct(productModel);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }

    @Test
    void request_delete_product_model_and_response_a_json_response_transaction(){
        JsonResponseTransaction expectedJsonResponseTransaction = new JsonResponseTransaction();
        expectedJsonResponseTransaction.setResponseMessage("Product: "+1L + " eliminated of system");

        when(productService.deleteProduct(1L)).thenReturn(expectedJsonResponseTransaction);
        ProductController productController = new ProductController(productService);

        ResponseEntity<JsonResponseTransaction> result = productController.deleteProduct(1L);

        verify(productService).deleteProduct(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedJsonResponseTransaction, result.getBody());
    }
}