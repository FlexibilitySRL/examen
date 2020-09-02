package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.TransactionApi;
import ar.com.flexibility.examen.domain.model.TransactionStatus;
import ar.com.flexibility.examen.domain.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	
	@InjectMocks
    private TransactionServiceImpl transactionService;
    
    @Test
    public void createTransaction()
    {
    	TransactionApi transactionApi = new TransactionApi();
    	transactionApi.setNumber(001l);
    	transactionApi.setClientId(001l);
    	transactionApi.setProductId(001l);
    	TransactionApi transactionApiNew = transactionService.create(transactionApi);

        assertNotNull(transactionApiNew);
        assertNotNull(transactionApiNew.getId());
    }
    
    @Test
    public void getTransaction()
    {
    	TransactionApi transactionApi = transactionService.get(1l);
        assertNotNull(transactionApi);
    }
    
    @Test
    public void getTransactions()
    {
        List<TransactionApi> transactions = transactionService.getTransactions();
        assertNotNull(transactions);
    }
    
    @Test
    public void updateTransaction()
    {
    	TransactionApi transactionApi = new TransactionApi();
    	transactionApi.setNumber(002l);
    	transactionApi.setClientId(002l);
    	transactionApi.setProductId(002l);
    	TransactionApi transactionApiNew = transactionService.update(1l, transactionApi);

        assertNotNull(transactionApiNew);
        assertEquals(transactionApiNew.getStatus(), TransactionStatus.CREATED);
    }

    @Test
    public void approveTransaction()
    {
    	TransactionApi transactionApi = transactionService.approveTransaction(1l);

        assertNotNull(transactionApi);
        assertEquals(transactionApi.getStatus(), TransactionStatus.APPROVED);
    }
    
    @Test
    public void deleteTransaction()
    {
    	transactionService.delete(1l);
    	TransactionApi transactionApi = transactionService.get(1l);
        assertNull(transactionApi);
    }
}
