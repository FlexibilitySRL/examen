package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.Status;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.app.api.VendorApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.service.TransactionService;
import ar.com.flexibility.examen.domain.service.VendorService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VendorService vendorService;
    @MockBean
    private TransactionService transactionService;
    @Autowired
    private Gson gson;
    private final String URL = "/vendors";

    private VendorApi vendorApi = new VendorApi(30L, "Mexx");
    private VendorApi vendorApi2 = new VendorApi(31L, "Falabella");
    private VendorApi invalidNameVendorApi = new VendorApi(32L, "C0mpu Compras");

    private List<Long> productIds = new ArrayList<>(Arrays.asList(20L, 30L));
    private TransactionApi pendingTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.PENDING);
    private TransactionApi approvedTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.APPROVED);
    private TransactionApi deniedTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.DENIED);

    @Test
    public void okWhenSearchingForAllVendors() throws Exception {
        List<VendorApi> vendorList = new ArrayList<>();
        vendorList.add(vendorApi2);
        vendorList.add(vendorApi);

        when(vendorService.all()).thenReturn(vendorList);

        this.mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(vendorApi2.getId()))
                .andExpect(jsonPath("$.[0].name").value(vendorApi2.getName()))
                .andExpect(jsonPath("$.[1].id").value(vendorApi.getId()))
                .andExpect(jsonPath("$.[1].name").value(vendorApi.getName()));
    }

    @Test
    public void okWhenSearchingForVendor() throws Exception {
        when(vendorService.get(anyLong())).thenReturn(vendorApi);

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(vendorApi.getId()))
                .andExpect(jsonPath("$.name").value(vendorApi.getName()));
    }

    @Test
    public void notFoundWhenSearchingForVendor() throws Exception {
        when(vendorService.get(anyLong())).thenThrow(new NotFoundException("Vendor with id 10"));

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenCreatingANewVendor() throws Exception {
        String json = gson.toJson(vendorApi);

        when(vendorService.create(any())).thenReturn(vendorApi);

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(vendorApi.getName()));
    }

    @Test
    public void badRequestWhenCreatingNewVendor() throws Exception {
        String json = gson.toJson(invalidNameVendorApi);

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestWhenCreatingAnAlreadyExistingVendor() throws Exception {
        String json = gson.toJson(vendorApi);

        when(vendorService.create(any())).thenThrow(new BadRequestException("The vendor already exist"));

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void okWhenUpdatingAnExistingVendor() throws Exception {
        String json = gson.toJson(vendorApi);

        when(vendorService.update(anyLong(), any())).thenReturn(vendorApi);

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(vendorApi.getId()))
                .andExpect(jsonPath("$.name").value(vendorApi.getName()));
    }

    @Test
    public void notFoundWhenUpdatingANoneExistingVendor() throws Exception {
        String json = gson.toJson(vendorApi);

        when(vendorService.update(anyLong(), any())).thenThrow(new NotFoundException("Vendor with id 10"));

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void badRequestWhenUpdatingWithIncorrectName() throws Exception {
        String json = gson.toJson(invalidNameVendorApi);

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void okWhenRemovingAnExistingVendor() throws Exception {
        doNothing().when(vendorService).remove(anyLong());
        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundWhenRemovingANoneExistingVendor() throws Exception {
        doThrow(new NotFoundException("Vendor with id 10")).when(vendorService).remove(anyLong());
        this.mockMvc.perform(delete(URL + "/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenCreatingATransaction() throws Exception {
        String json = gson.toJson(pendingTransactionApi);

        when(transactionService.create(anyLong(), any())).thenReturn(pendingTransactionApi);

        this.mockMvc.perform(post(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pendingTransactionApi.getId()));
    }

    @Test
    public void badRequestWhenCreatingATransactionAndStatusIsNotPending() throws Exception {
        String json = gson.toJson(approvedTransactionApi);

        this.mockMvc.perform(post(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void notFoundWhenCreatingATransactionAndProductIsNotFound() throws Exception {
        String json = gson.toJson(pendingTransactionApi);

        when(transactionService.create(anyLong(), any())).thenThrow(new NotFoundException("The product was not found"));

        this.mockMvc.perform(post(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void notFoundWhenCreatingATransactionAndClientIsNotFound() throws Exception {
        String json = gson.toJson(pendingTransactionApi);

        when(transactionService.create(anyLong(), any())).thenThrow(new NotFoundException("The client was not found"));

        this.mockMvc.perform(post(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void notFoundWhenCreatingATransactionAndVendorIsNotFound() throws Exception {
        String json = gson.toJson(pendingTransactionApi);

        when(transactionService.create(anyLong(), any())).thenThrow(new NotFoundException("The vendor was not found"));

        this.mockMvc.perform(post(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenRetrievingAllTransactionsForAVendor() throws Exception {
        List<TransactionApi> transactionList = new ArrayList<>();
        transactionList.add(pendingTransactionApi);
        transactionList.add(approvedTransactionApi);
        transactionList.add(deniedTransactionApi);

        when(transactionService.allByVendor(anyLong())).thenReturn(transactionList);

        this.mockMvc.perform(get(URL + "/30/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(pendingTransactionApi.getId()))
                .andExpect(jsonPath("$.[0].productId.[0]").value(approvedTransactionApi.getProductId().get(0)))
                .andExpect(jsonPath("$.[0].productId.[1]").value(approvedTransactionApi.getProductId().get(1)))
                .andExpect(jsonPath("$.[0].clientId").value(pendingTransactionApi.getClientId()))
                .andExpect(jsonPath("$.[0].price").value(pendingTransactionApi.getPrice()))
                .andExpect(jsonPath("$.[0].status").value(pendingTransactionApi.getStatus().toString()))
                .andExpect(jsonPath("$.[1].id").value(approvedTransactionApi.getId()))
                .andExpect(jsonPath("$.[1].productId.[0]").value(approvedTransactionApi.getProductId().get(0)))
                .andExpect(jsonPath("$.[1].productId.[1]").value(approvedTransactionApi.getProductId().get(1)))
                .andExpect(jsonPath("$.[1].clientId").value(approvedTransactionApi.getClientId()))
                .andExpect(jsonPath("$.[1].price").value(approvedTransactionApi.getPrice()))
                .andExpect(jsonPath("$.[1].status").value(approvedTransactionApi.getStatus().toString()))
                .andExpect(jsonPath("$.[2].id").value(deniedTransactionApi.getId()))
                .andExpect(jsonPath("$.[2].productId.[0]").value(approvedTransactionApi.getProductId().get(0)))
                .andExpect(jsonPath("$.[2].productId.[1]").value(approvedTransactionApi.getProductId().get(1)))
                .andExpect(jsonPath("$.[2].clientId").value(deniedTransactionApi.getClientId()))
                .andExpect(jsonPath("$.[2].price").value(deniedTransactionApi.getPrice()))
                .andExpect(jsonPath("$.[2].status").value(deniedTransactionApi.getStatus().toString()));
    }

    @Test
    public void notFoundWhenRetrievingAllTransactionsAndVendorIsNotFound() throws Exception {
        when(transactionService.allByVendor(any())).thenThrow(new NotFoundException("The vendor was not found"));

        this.mockMvc.perform(get(URL + "/30/transactions"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenUpdatingATransactionsToApprove() throws Exception {
        String json = gson.toJson(approvedTransactionApi);

        when(transactionService.updateStatus(anyLong(), any())).thenReturn(approvedTransactionApi);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(approvedTransactionApi.getId()))
                .andExpect(jsonPath("$.productId.[0]").value(approvedTransactionApi.getProductId().get(0)))
                .andExpect(jsonPath("$.productId.[1]").value(approvedTransactionApi.getProductId().get(1)))
                .andExpect(jsonPath("$.clientId").value(approvedTransactionApi.getClientId()))
                .andExpect(jsonPath("$.price").value(approvedTransactionApi.getPrice()))
                .andExpect(jsonPath("$.status").value(approvedTransactionApi.getStatus().toString()));
    }

    @Test
    public void okWhenUpdatingATransactionsToDenied() throws Exception {
        String json = gson.toJson(deniedTransactionApi);

        when(transactionService.updateStatus(anyLong(), any())).thenReturn(deniedTransactionApi);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(deniedTransactionApi.getId()))
                .andExpect(jsonPath("$.productId.[0]").value(approvedTransactionApi.getProductId().get(0)))
                .andExpect(jsonPath("$.productId.[1]").value(approvedTransactionApi.getProductId().get(1)))
                .andExpect(jsonPath("$.clientId").value(deniedTransactionApi.getClientId()))
                .andExpect(jsonPath("$.price").value(deniedTransactionApi.getPrice()))
                .andExpect(jsonPath("$.status").value(deniedTransactionApi.getStatus().toString()));
    }

    @Test
    public void badRequestWhenUpdatingATransactionsToPending() throws Exception {
        String json = gson.toJson(pendingTransactionApi);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestWhenUpdatingAValidTransactionsWithNoProduct() throws Exception {
        TransactionApi noProduct = new TransactionApi();
        noProduct.setProductId(null);
        noProduct.setClientId(10L);
        noProduct.setPrice(10.0);
        noProduct.setStatus(Status.APPROVED);
        String json = gson.toJson(noProduct);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestWhenUpdatingAValidTransactionsWithNoClient() throws Exception {
        TransactionApi noClient = new TransactionApi();
        noClient.setProductId(productIds);
        noClient.setClientId(null);
        noClient.setPrice(10.0);
        noClient.setStatus(Status.APPROVED);
        String json = gson.toJson(noClient);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestWhenUpdatingAValidTransactionsWithPrice() throws Exception {
        TransactionApi noPrice = new TransactionApi();
        noPrice.setProductId(productIds);
        noPrice.setClientId(11L);
        noPrice.setPrice(null);
        noPrice.setStatus(Status.APPROVED);
        String json = gson.toJson(noPrice);

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void notFoundWhenUpdatingATransactionAndProductIsNotFound() throws Exception {
        String json = gson.toJson(approvedTransactionApi);
        when(transactionService.updateStatus(anyLong(), any())).thenThrow(new NotFoundException("The product was not found"));

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void notFoundWhenUpdatingATransactionAndClientIsNotFound() throws Exception {
        String json = gson.toJson(approvedTransactionApi);
        when(transactionService.updateStatus(anyLong(), any())).thenThrow(new NotFoundException("The client was not found"));

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void notFoundWhenUpdatingATransactionAndVendorIsNotFound() throws Exception {
        String json = gson.toJson(approvedTransactionApi);
        when(transactionService.updateStatus(anyLong(), any())).thenThrow(new NotFoundException("The vendor was not found"));

        this.mockMvc.perform(put(URL + "/30/transactions").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
