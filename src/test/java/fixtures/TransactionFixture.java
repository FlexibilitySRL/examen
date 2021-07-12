package fixtures;

import ar.com.plug.examen.app.api.ProductStockApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRquest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItems;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionFixture {

  public static TransactionApi getTransactionApiWithId(Long id) {
    TransactionApi transactionApi = new TransactionApi();
    transactionApi.setId(id);
    return transactionApi;
  }

  public static TransactionApi getTransactionApi() {
    TransactionApi transactionApi = new TransactionApi();
    transactionApi.setStatus(TransactionStatusEnum.PENDING);
    transactionApi.setCreationDate(new Date());
    transactionApi.setClient(ClientFixture.getClientApi());
    transactionApi.setSeller(SellerFixture.getSellerApi());
    return transactionApi;
  }

  public static TransactionApiRquest getTransactionApiRequest() {
    TransactionApiRquest transactionApiRequest = new TransactionApiRquest();
    transactionApiRequest.setClientId(1L);
    transactionApiRequest.setSellerId(2L);
    ProductStockApi product = new ProductStockApi();
    product.setIdProduct(1L);
    product.setQuantity(10L);
    List<ProductStockApi> lsProducts = new ArrayList<>();
    lsProducts.add(product);
    transactionApiRequest.setLsProducts(lsProducts);
    return transactionApiRequest;
  }

  public static List<TransactionApi> getTransactionApiList(TransactionApi transactionApi1, TransactionApi transactionApi2) {
    List<TransactionApi> lsTransactions = new ArrayList<TransactionApi>();
    lsTransactions.add(transactionApi1);
    lsTransactions.add(transactionApi2);
    return lsTransactions;
  }

  public static Transaction getTransactionWithId(Long id) {
    Transaction transaction = new Transaction();
    transaction.setId(id);
    return transaction;
  }

  public static Transaction getTransaction() {
    Transaction transaction = new Transaction();
    transaction.setCreationDate(new Date());
    transaction.setClient(new Client());
    transaction.setSeller(new Seller());
    transaction.setStatus(TransactionStatusEnum.APPROVED);
    List<TransactionItems> lsItems = new ArrayList<>();
    lsItems.add(new TransactionItems());
    transaction.setTransactionItems(lsItems);
    return transaction;
  }

}
