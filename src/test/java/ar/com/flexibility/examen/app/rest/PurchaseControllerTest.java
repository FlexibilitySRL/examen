package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PurchaseControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PurchaseController controller;

    @Mock
    private PurchaseService purchaseService = Mockito.mock(PurchaseService.class);

    @Mock
    private ClientService clientService = Mockito.mock(ClientService.class);

    @Mock
    private ProductService productService = Mockito.mock(ProductService.class);

    @BeforeEach
    void initUseCase(){
        controller = new PurchaseController(purchaseService,clientService,productService);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void getPurchase() throws Exception {

        Client client = new Client();
        client.setId(1L);

        PurchaseItem item = new PurchaseItem(2L,10,100.0);
        item.setId(3L);

        Purchase purchase = new Purchase();
        purchase.setId(100L);
        purchase.setClient(client);
        purchase.setPurchaseItem(item);

        when(purchaseService.findById(100L)).thenReturn(purchase);

        ResultActions result = mockMvc.perform(get("/rest/purchases/{id}",100L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(purchaseService).findById(100L);
        verifyNoMoreInteractions(purchaseService);

        result.andExpect(content().json("{'id' : 100, 'clientId':1,'productId':2,'units':10,'total':1000.0," +
                "'status':IN_PROGRESS}"));
    }

    @Test
    void getPurchaseThatDoesNotExist() throws Exception {

        when(purchaseService.findById(1234567L)).thenReturn(null);

        ResultActions result = mockMvc.perform(get("/rest/purchases/{id}",1234567L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")))
                .andExpect(content().string("La compra no existe"));

        verify(purchaseService).findById(1234567L);
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    void deletePurchase() throws Exception {
        ResultActions result = mockMvc.perform(
                delete("/rest/purchases/{id}", 1))
                .andExpect(status().isNoContent());

        verify(purchaseService, times(1)).deleteById(1L);
        verifyNoMoreInteractions(purchaseService);
    }

    @Test
    void createPurchase() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        Client client = new Client();
        client.setId(2L);

        Product product = new Product();
        product.setId(3L);
        product.setStock(100);
        product.setPrice(50.0);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setClient(client);

        PurchaseItem item = new PurchaseItem(purchaseApi.getProductId(),purchaseApi.getUnits(),product.getPrice());
        purchase.setPurchaseItem(item);

        when(clientService.findById(2L)).thenReturn(client);
        when(productService.findById(3L)).thenReturn(product);
        when(purchaseService.create(any(Purchase.class))).thenReturn(purchase);

        ResultActions result = mockMvc.perform(post("/rest/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(purchaseApi))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(purchaseService).create(any(Purchase.class));
        verifyNoMoreInteractions(purchaseService);
        verify(clientService).findById(2L);
        verifyNoMoreInteractions(clientService);
        verify(productService).findById(3L);
        verify(productService).update(product);
        verifyNoMoreInteractions(purchaseService);

        assertEquals(95,product.getStock());

        result.andExpect(content().json("{'id' : 1, 'clientId':2,'productId':3,'units':5,'total':250.0," +
                                                    "'status':IN_PROGRESS}"));
    }

    @Test
    void createPurchaseWithNoClient() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        when(clientService.findById(2L)).thenReturn(null);

        ResultActions result = mockMvc.perform(post("/rest/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(purchaseApi)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")))
                .andExpect(content().string("El cliente no existe"));

        verify(clientService).findById(2L);
        verifyNoMoreInteractions(clientService);
        verifyZeroInteractions(purchaseService);
        verifyZeroInteractions(productService);
    }

    @Test
    void createPurchaseWithNoProduct() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        Client client = new Client();
        client.setId(2L);

        when(clientService.findById(2L)).thenReturn(client);
        when(productService.findById(3L)).thenReturn(null);


        ResultActions result = mockMvc.perform(post("/rest/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(purchaseApi)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")))
                .andExpect(content().string("El producto no existe"));

        verify(clientService).findById(2L);
        verifyNoMoreInteractions(clientService);
        verify(productService).findById(3L);
        verifyZeroInteractions(purchaseService);
    }

    @Test
    void createPurchaseProductDoesNotHaveStock() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        Client client = new Client();
        client.setId(2L);

        Product product = new Product();
        product.setId(3L);
        product.setStock(2);
        product.setPrice(50.0);

        when(clientService.findById(2L)).thenReturn(client);
        when(productService.findById(3L)).thenReturn(product);

        ResultActions result = mockMvc.perform(post("/rest/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(purchaseApi)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1")))
                .andExpect(content().string("El producto no tiene suficiente stock"));

        verifyZeroInteractions(purchaseService);
        verify(clientService).findById(2L);
        verifyNoMoreInteractions(clientService);
        verify(productService).findById(3L);

    }

    @Test
    void updatePurchase() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        Client client = new Client();
        client.setId(2L);

        Product product = new Product();
        product.setId(3L);
        product.setStock(100);
        product.setPrice(50.0);

        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setClient(client);

        PurchaseItem item = new PurchaseItem(purchaseApi.getProductId(),purchaseApi.getUnits(),product.getPrice());
        purchase.setPurchaseItem(item);

        purchase.done();
        when(purchaseService.update(any(Purchase.class))).thenReturn(purchase);

        ResultActions result = mockMvc.perform(put("/rest/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(purchaseApi))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(purchaseService).update(any(Purchase.class));
        verifyNoMoreInteractions(purchaseService);

        result.andExpect(content().json("{'id' : 1, 'clientId':2,'productId':3,'units':5,'total':250.0," +
                "'status':DONE}"));
    }

    @Test
    void getAllPurchases() throws Exception {
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(1L);
        purchaseApi.setClientId(2L);
        purchaseApi.setProductId(3L);
        purchaseApi.setUnits(5);

        Client client = new Client();
        client.setId(2L);

        Product product = new Product();
        product.setId(3L);
        product.setStock(100);
        product.setPrice(50.0);

        PurchaseItem item = new PurchaseItem(purchaseApi.getProductId(),purchaseApi.getUnits(),product.getPrice());

        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);
        purchase1.setClient(client);
        purchase1.setPurchaseItem(item);

        Purchase purchase2 = new Purchase();
        purchase2.setId(2L);
        purchase2.setClient(client);
        purchase2.setPurchaseItem(item);

        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        when(purchaseService.findAll()).thenReturn(purchases);

        ResultActions result = mockMvc.perform(
                get("/rest/purchases"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(purchaseService).findAll();
        verifyNoMoreInteractions(purchaseService);

        result.andExpect(content().json("[{'id' : 1, 'clientId':2,'productId':3,'units':5,'total':250.0,'status':IN_PROGRESS},"
                + "{'id' : 2, 'clientId':2,'productId':3,'units':5,'total':250.0,'status':IN_PROGRESS}]"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}