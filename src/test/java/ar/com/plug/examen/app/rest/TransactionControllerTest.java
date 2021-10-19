package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.ItemTransactionDTO;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String BASE_URL = "/transactions/";

    private TransactionDTO transactionDTO;


    private TransactionDTO getTransactionDTO(){

        // Crear una transaccion
        TransactionDTO transaction = new TransactionDTO();
        transaction.setCustomerId("1129865421");
        transaction.setSellerId(2L);

        //Crear items para la transaccion
        ItemTransactionDTO itemTransaction = new ItemTransactionDTO();
        itemTransaction.setProductId(1L);
        itemTransaction.setAmount(3);
        itemTransaction.setTotal(450000D);

        ItemTransactionDTO itemTransaction2 = new ItemTransactionDTO();
        itemTransaction2.setProductId(2L);
        itemTransaction2.setAmount(4);
        itemTransaction2.setTotal(300000D);

        //Asignar items a la transacciń
        transaction.setItemsTransaction(Arrays.asList(itemTransaction,itemTransaction2));

        return transaction;
    }


    @Before
    public void setUp() throws Exception {
        // Setup to can use Http PATCH method
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void registerTransaction() {

        transactionDTO = getTransactionDTO();
        Mockito.when(this.transactionService.save(transactionDTO))
                 .thenReturn(transactionDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TransactionDTO> request = new HttpEntity<>(transactionDTO,headers);
        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity(BASE_URL + "register", request, TransactionDTO.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());


    }

    @Test
    public void approveTransacction() {

        transactionDTO = getTransactionDTO();
        transactionDTO.setTransantionId(1L);

        Mockito.when(this.transactionService.findTransactionById(1L))
                .thenReturn(Optional.of(transactionDTO));

        Mockito.when(this.transactionService.approveTransaction(transactionDTO))
                .thenReturn(transactionDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TransactionDTO> request = new HttpEntity<>(transactionDTO, headers);
        ResponseEntity<TransactionDTO> response = testRestTemplate.exchange(BASE_URL + "approve/"+1, HttpMethod.PATCH, request, TransactionDTO.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());


    }

    @Test
    public void findTransactionById() {

        transactionDTO = getTransactionDTO();
        transactionDTO.setTransantionId(1L);

        Mockito.when(this.transactionService.findTransactionById(1L))
                .thenReturn(Optional.of(transactionDTO));

        ResponseEntity<TransactionDTO> response = testRestTemplate.getForEntity(BASE_URL + "1", TransactionDTO.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(transactionDTO.getTransantionId() == response.getBody().getTransantionId());

    }

    @Test
    public void findApprovedTransaction() {

        transactionDTO = getTransactionDTO();
        transactionDTO.setTransantionId(1L);
        transactionDTO.setState("A");

        Mockito.when(this.transactionService.findApprovedTransactions())
                .thenReturn(Optional.of(Arrays.asList(transactionDTO)));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "approved", List.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().size() > 0);


    }
}