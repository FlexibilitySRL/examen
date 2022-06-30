package ar.com.plug.examen.domain.service.controller;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    public static final ProductApi PRODUCT_API = new ProductApi();
    public static final Long PRODUCT_ID = 1L;
    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String CREATE_CODE = "201 CREATED";
    private static final String OK = "OK";

    @Before
    public void init() throws ChallengeException {
        MockitoAnnotations.initMocks(this);
        PRODUCT_API.setId(PRODUCT_ID);
        PRODUCT_API.setName("EJEMPLO");
        PRODUCT_API.setDescription("EJEMPLO");
        PRODUCT_API.setPrice(123.0);
    }

    @Test
    public void getProductById() throws ChallengeException {
        when(productService.getProductById(1L)).thenReturn(PRODUCT_API);
        final ChallengeResponse<ProductApi> response = productController.getProductById(PRODUCT_ID);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), PRODUCT_API);
    }

    @Test
    public void getCreateProduct() throws ChallengeException {
        when(productService.createProduct(PRODUCT_API)).thenReturn(PRODUCT_API);
        final ChallengeResponse<ProductApi> response = productController.createProduct(PRODUCT_API);
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), CREATE_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), PRODUCT_API);
    }

    @Test
    public void getAllProduct() throws ChallengeException {
        List<ProductApi> productApiList = new ArrayList<>();
        productApiList.add(PRODUCT_API);
        when(productService.listAllProducts()).thenReturn(productApiList);
        final ChallengeResponse<List<ProductApi>> response = productController.listAllProducts();
        assertEquals(response.getStatus(), SUCCESS_STATUS);
        assertEquals(response.getCode(), SUCCESS_CODE);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), productApiList);
    }
}
