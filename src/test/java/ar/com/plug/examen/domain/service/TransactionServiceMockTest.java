package ar.com.plug.examen.domain.service;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItem;
import ar.com.plug.examen.domain.repository.TransactionItemsRepository;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class TransactionServiceMockTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    TransactionItemsRepository transactionItemsRepository;

    private TransactionService transactionService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionServiceImpl(transactionRepository, transactionItemsRepository);
        List<TransactionItem> transactionItems = new ArrayList<>();
        transactionItems.add(TransactionItem.builder()
                        .id(1L)
                        .price(15.0)
                        .productId(1L)
                        .quantity(12.0)
                        .build());

        Transaction venta = Transaction.builder()
                .id(1L)
                .type('V')
                .numberTransaction("1234596")
                .description("venta de productos")
                .clientId(1L)
                .items(transactionItems)
                .build();


        Mockito.when(transactionRepository.findById(1L)).thenReturn(Optional.of(venta));
        Mockito.when(transactionRepository.save(venta)).thenReturn(venta);
    }

    @Test
    public void whenValidGetID_ThenReturnTransaction(){
        Transaction found = transactionService.getTransaction(1L);
        Assertions.assertThat(found.getNumberTransaction()).isEqualTo("1234596");
    }


    @Test
    public void whenCreateTransaction_TheReturnTransaction(){

        List<TransactionItem> transactionItems = new ArrayList<>();
        transactionItems.add(TransactionItem.builder()
                .id(1L)
                .price(15.0)
                .productId(1L)
                .quantity(12.0)
                .build());

        Transaction venta = Transaction.builder()
                .id(1L)
                .type('V')
                .numberTransaction("1234596")
                .description("venta de productos")
                .clientId(1L)
                .items(transactionItems)
                .build();

        Mockito.when(transactionService.createTransaction(venta)).thenReturn(venta);
    }

    @Test
    public void whenUpdateTransaction_TheReturnTransactionUpdated(){

        List<TransactionItem> transactionItems = new ArrayList<>();
        transactionItems.add(TransactionItem.builder()
                .id(1L)
                .price(15.0)
                .productId(1L)
                .quantity(12.0)
                .build());

        Transaction venta = Transaction.builder()
                .id(1L)
                .type('V')
                .numberTransaction("1234596")
                .description("venta de productos")
                .clientId(1L)
                .approved(true)
                .items(transactionItems)
                .build();

        Mockito.when(transactionService.updateTransaction(venta)).thenReturn(venta);
    }

    @Test
    public void whenDeleteTransaction_TheReturnTransactionDeleted(){
        List<TransactionItem> transactionItems = new ArrayList<>();
        transactionItems.add(TransactionItem.builder()
                .id(1L)
                .price(15.0)
                .productId(1L)
                .quantity(12.0)
                .build());

        Transaction venta = Transaction.builder()
                .id(1L)
                .type('V')
                .numberTransaction("1234596")
                .description("venta de productos")
                .clientId(1L)
                .items(transactionItems)
                .build();

        Mockito.when(transactionService.deleteTransaction(venta)).thenReturn(venta);
    }

}
