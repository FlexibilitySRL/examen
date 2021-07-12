package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRquest;
import ar.com.plug.examen.domain.Repository.TransactionRepository;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.TransactionItemMapper;
import ar.com.plug.examen.domain.mappers.TransactionMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.impl.ClientServiceImpl;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import fixtures.ClientFixture;
import fixtures.ProductFixture;
import fixtures.SellerFixture;
import fixtures.TransactionFixture;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {

  @InjectMocks
  private TransactionServiceImpl transactionService;

  @Mock
  private ClientServiceImpl clientService;

  @Mock
  private SellerServiceImpl sellerService;

  @Mock
  private ProductServiceImpl productService;

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private TransactionMapper transactionMapper;

  @Mock
  private TransactionItemMapper transactionItemMapper;

  @Mock
  private Validator validator;

  @Test
  public void findAllTest() {
    List<TransactionApi> lsTransacions = TransactionFixture.getTransactionApiList(TransactionFixture.getTransactionApi(), TransactionFixture.getTransactionApi());
    when(this.transactionMapper.transactionsToListTransactionApi(this.transactionRepository.findAll()))
        .thenReturn(lsTransacions);

    List<TransactionApi> response = this.transactionService.findAll();
    assertEquals(lsTransacions, response);
    verify(this.transactionRepository, times(2)).findAll();
  }

  @Test
  public void findByIdCheckedTest() {
    Transaction transaction  = TransactionFixture.getTransactionWithId(1L);
    when(this.transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

    TransactionApi response = this.transactionService.findByIdChecked(1L);
    assertEquals(this.transactionMapper.transactionToTransactionApi(transaction), response);
    verify(this.transactionRepository, times(1)).findById(1L);
  }

  @Test
  public void findByIdChecked_NotFoundTest() {
    when(this.transactionRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      this.transactionService.findByIdChecked(1L);
    });

    assertTrue(exception.getMessage().contains("Transaction not found"));
    verify(this.transactionRepository, times(1)).findById(1L);
  }

  @Test
  public void save_successTest() {
    TransactionApi transactionMock = TransactionFixture.getTransactionApiWithId(1L);
    Transaction transaction = TransactionFixture.getTransaction();
    TransactionApiRquest transactionToSave = TransactionFixture.getTransactionApiRequest();
    ClientApi client = ClientFixture.getClientApiWithId(1L);
    SellerApi seller = SellerFixture.getSellerApiWithId(2L);
    List<Product> lsProducts = ProductFixture.getProductList(ProductFixture.getProductWithId(1L));
    when(this.clientService.findByIdChecked(1L)).thenReturn(client);
    when(this.sellerService.findByIdChecked(2L)).thenReturn(seller);
    when(this.productService.getProductsWithStock(transactionToSave.getLsProducts())).thenReturn(lsProducts);
    when(this.transactionItemMapper.transactionItemsToListTransactionitemsApi(any())).thenReturn(transactionMock.getTransactionItems());
    when(this.transactionMapper.transactionToTransactionApi(any())).thenReturn(transactionMock);
    when(this.transactionMapper.transactionApiToTransaction(any())).thenReturn(transaction);
    doNothing().when(this.validator).validateTransaction(transactionToSave);

    TransactionApi response = this.transactionService.save(transactionToSave);
    assertEquals(transactionMock, response);
    verify(this.transactionRepository, times(1)).save(transaction);
  }

  @Test
  public void save_BadRequestTest() {
    TransactionApiRquest transactionToSave = TransactionFixture.getTransactionApiRequest();
    GenericBadRequestException badRequestException = new GenericBadRequestException("The clientId is required");
    doThrow(badRequestException).when(this.validator).validateTransaction(transactionToSave);

    Exception exception = assertThrows(GenericBadRequestException.class, () -> {
      this.transactionService.save(transactionToSave);
    });
    assertEquals(exception, badRequestException);
    verify(this.transactionRepository, times(0)).save(any());
  }

  @Test
  public void approveTransactionTest() {
    TransactionApi transactionApi = TransactionFixture.getTransactionApi();
    TransactionStatusEnum newStatus = TransactionStatusEnum.APPROVED;
    Transaction transaction = TransactionFixture.getTransactionWithId(1L);
    doNothing().when(this.validator).validateTransactionStatus(newStatus);
    when(this.transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
    when(this.transactionMapper.transactionToTransactionApi(this.transactionRepository.save(transaction))).thenReturn(transactionApi);

    TransactionApi response = this.transactionService.updateStatus(1L, newStatus);
    assertEquals(transactionApi, response);
    verify(this.transactionRepository, times(2)).save(transaction);
  }

  @Test
  public void approveTransaction_NotFoundTest() {
    TransactionStatusEnum newStatus = TransactionStatusEnum.APPROVED;
    Transaction transaction = TransactionFixture.getTransactionWithId(1L);
    when(this.transactionRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      this.transactionService.updateStatus(1L, newStatus);
    });
    assertTrue(exception.getMessage().contains("Transaction not found"));
    verify(this.transactionRepository, times(0)).save(transaction);
  }

  @Test
  public void approveTransaction_BadRquestTest() {
    Transaction transaction = TransactionFixture.getTransactionWithId(1L);
    GenericBadRequestException badRequestException = new GenericBadRequestException("The status of transaction is required");
    when(this.transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
    doThrow(badRequestException).when(this.validator).validateTransactionStatus(null);

    Exception exception = assertThrows(GenericBadRequestException.class, () -> {
      this.transactionService.updateStatus(1L, null);
    });
    assertEquals(exception, badRequestException);
    verify(this.transactionRepository, times(0)).save(transaction);
  }
}
