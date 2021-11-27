package ar.com.plug.examen.app.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ar.com.plug.examen.Application;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRequest;
import ar.com.plug.examen.app.fixtures.TransactionFixture;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class TransactionControllerTest {

    private final String URL = "/transactions";

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TransactionServiceImpl transactionService;

    @Test
    public void getTransactions() {
        List<TransactionApi> lsTransactions = TransactionFixture
                .getTransactionApiList(TransactionFixture.getTransactionApi(),
                        TransactionFixture.getTransactionApi());
        when(this.transactionService.findAll()).thenReturn(lsTransactions);
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetTransactionsById() {
        TransactionApi transactionApi = TransactionFixture.getTransactionApi();
        when(this.transactionService.findByIdChecked(1L)).thenReturn(transactionApi);

        ResponseEntity<TransactionApi> responseEntity = restTemplate
                .getForEntity(URL + "/findById/" + 1L, TransactionApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionApi, responseEntity.getBody());
        verify(this.transactionService, times(1)).findByIdChecked(1L);
    }

    @Test
    public void testSave() {
        TransactionApi transactionApi = TransactionFixture.getTransactionApi();
        TransactionApiRequest transactionApiRquest = TransactionFixture.getTransactionApiRequest();

        ResponseEntity<TransactionApi> response = restTemplate
                .postForEntity(URL, transactionApiRquest, TransactionApi.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(this.transactionService, times(1)).save(transactionApiRquest);
    }

    @Test
    public void testApproveTransaction() {
        TransactionApi transactionApi = TransactionFixture.getTransactionApi();
        TransactionStatusEnum newStatus = TransactionStatusEnum.APPROVED;
        when(this.transactionService.updateStatus(1L, newStatus)).thenReturn(transactionApi);

        ResponseEntity<TransactionApi> responseEntity = restTemplate
                .exchange(URL + "/" + 1L + "?status=" + newStatus, HttpMethod.PUT, null,
                        TransactionApi.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(this.transactionService, times(1)).updateStatus(1L, newStatus);
    }
}