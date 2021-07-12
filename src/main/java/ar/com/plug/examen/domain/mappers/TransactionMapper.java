package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.model.Transaction;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  @Autowired
  private ClientMapper clientMapper;

  @Autowired
  private SellerMapper sellerMapper;

  @Autowired
  private TransactionItemMapper transactionItemMapper;

  public TransactionApi transactionToTransactionApi(Transaction transaction) {
    TransactionApi transactionApi = new TransactionApi();
    transactionApi.setClient(this.clientMapper.clientToClientApi(transaction.getClient()));
    transactionApi.setCreationDate(transaction.getCreationDate());
    transactionApi.setId(transaction.getId());
    transactionApi.setSeller(this.sellerMapper.sellerToSelerApi(transaction.getSeller()));
    transactionApi.setStatus(transaction.getStatus());
    transactionApi.setTransactionItems(this.transactionItemMapper.transactionItemsToListTransactionitemsApi(transaction.getTransactionItems()));
    return transactionApi;
  }

  public Transaction transactionApiToTransaction(TransactionApi transactionApi) {
    Transaction transaction = new Transaction();
    transaction.setClient(this.clientMapper.clientApiToClient(transactionApi.getClient()));
    transaction.setCreationDate(transactionApi.getCreationDate());
    transaction.setId(transactionApi.getId());
    transaction.setSeller(this.sellerMapper.sellerApiToSeller(transactionApi.getSeller()));
    transaction.setStatus(transactionApi.getStatus());
    transaction.setTransactionItems(this.transactionItemMapper.transactionItemsApiToListTransactionitems(transactionApi.getTransactionItems()));
    return transaction;
  }

  public List<TransactionApi> transactionsToListTransactionApi(List<Transaction> transactions) {
    return transactions.stream().map(this::transactionToTransactionApi).collect(Collectors.toList());
  }

}
