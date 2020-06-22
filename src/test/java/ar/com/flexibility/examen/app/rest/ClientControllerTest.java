package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.Status;
import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.TransactionService;
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
@ContextConfiguration(classes = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;
    @MockBean
    private TransactionService transactionService;
    @Autowired
    private Gson gson;
    private final String URL = "/clients";

    private ClientApi clientApi = new ClientApi(10L,"Martin");
    private ClientApi clientApi2 = new ClientApi(11L, "Marton");
    private ClientApi invalidNameClientApi = new ClientApi(12L, "Mart0n");

    private List<Long> productIds = new ArrayList<>(Arrays.asList(20L, 30L));
    private TransactionApi pendingTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.PENDING);
    private TransactionApi approvedTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.APPROVED);
    private TransactionApi deniedTransactionApi = new TransactionApi(100L, productIds, 10L, 10.4D, Status.DENIED);

    @Test
    public void okWhenSearchingForAllClients() throws Exception {
        List<ClientApi> clientList = new ArrayList<>();
        clientList.add(clientApi2);
        clientList.add(clientApi);

        when(clientService.all()).thenReturn(clientList);

        this.mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(clientApi2.getId()))
                .andExpect(jsonPath("$.[0].name").value(clientApi2.getName()))
                .andExpect(jsonPath("$.[1].id").value(clientApi.getId()))
                .andExpect(jsonPath("$.[1].name").value(clientApi.getName()));
    }

    @Test
    public void okWhenSearchingForClient() throws Exception {
        when(clientService.get(anyLong())).thenReturn(clientApi);

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientApi.getId()))
                .andExpect(jsonPath("$.name").value(clientApi.getName()));
    }

    @Test
    public void okWhenRetrievingAllTransactionsForAVendor() throws Exception {
        List<TransactionApi> transactionList = new ArrayList<>();
        transactionList.add(pendingTransactionApi);
        transactionList.add(approvedTransactionApi);
        transactionList.add(deniedTransactionApi);

        when(transactionService.allByClient(anyLong())).thenReturn(transactionList);

        this.mockMvc.perform(get(URL + "/10/transactions"))
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
        when(transactionService.allByClient(any())).thenThrow(new NotFoundException("The client was not found"));

        this.mockMvc.perform(get(URL + "/10/transactions"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void notFoundWhenSearchingForClient() throws Exception {
        when(clientService.get(anyLong())).thenThrow(new NotFoundException("Client with id 10"));

        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void okWhenCreatingANewClient() throws Exception {
        String json = gson.toJson(clientApi);

        when(clientService.create(any())).thenReturn(clientApi);

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(clientApi.getName()));
    }

    @Test
    public void badRequestWhenCreatingNewClient() throws Exception {
        String json = gson.toJson(invalidNameClientApi);

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestWhenCreatingAnAlreadyExistingClient() throws Exception {
        String json = gson.toJson(clientApi);

        when(clientService.create(any())).thenThrow(new BadRequestException("The client already exist"));

        this.mockMvc.perform(post(URL).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void okWhenUpdatingAnExistingClient() throws Exception {
        String json = gson.toJson(clientApi);

        when(clientService.update(anyLong(), any())).thenReturn(clientApi);

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientApi.getId()))
                .andExpect(jsonPath("$.name").value(clientApi.getName()));
    }

    @Test
    public void notFoundWhenUpdatingANoneExistingClient() throws Exception {
        String json = gson.toJson(clientApi);

        when(clientService.update(anyLong(), any())).thenThrow(new NotFoundException("Client with id 10"));

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void badRequestWhenUpdatingWithIncorrectName() throws Exception {
        String json = gson.toJson(invalidNameClientApi);

        this.mockMvc.perform(put(URL + "/10").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void okWhenRemovingAnExistingClient() throws Exception {
        doNothing().when(clientService).remove(anyLong());
        this.mockMvc.perform(get(URL + "/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundWhenRemovingANoneExistingClient() throws Exception {
        doThrow(new NotFoundException("Client with id 10")).when(clientService).remove(anyLong());
        this.mockMvc.perform(delete(URL + "/10"))
                .andExpect(status().isNotFound());
    }
}