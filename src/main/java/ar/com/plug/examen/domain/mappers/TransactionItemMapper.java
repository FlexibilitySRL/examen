package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.TransactionItemsApi;
import ar.com.plug.examen.domain.model.TransactionItems;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionItemMapper {

  @Autowired
  private TransactionMapper transactionMapper;

  @Autowired
  private ProductMapper productMapper;

  public TransactionItemsApi transactionItemToTransactionItemApi(TransactionItems transactionItems) {
    TransactionItemsApi transactionItemsApi = new TransactionItemsApi();
    transactionItemsApi.setProduct(productMapper.productToProductApi(transactionItems.getProduct()));
    transactionItemsApi.setQuantity(transactionItems.getQuantity());
    transactionItemsApi.setTransaction(transactionItems.getTransaction());
    return transactionItemsApi;
  }

  public TransactionItems transactionItemApiToTransactionItem(TransactionItemsApi transactionItemApi) {
    TransactionItems transactionItems = new TransactionItems();
    transactionItems.setProduct(productMapper.productApiToProduct(transactionItemApi.getProduct()));
    transactionItems.setQuantity(transactionItemApi.getQuantity());
    transactionItems.setTransaction(transactionItemApi.getTransaction());
    return transactionItems;
  }

  public List<TransactionItemsApi> transactionItemsToListTransactionitemsApi(List<TransactionItems> transactionItems) {
    return transactionItems.stream().map(this::transactionItemToTransactionItemApi).collect(Collectors.toList());
  }

  public List<TransactionItems> transactionItemsApiToListTransactionitems(List<TransactionItemsApi> transactionItems) {
    return transactionItems.stream().map(this::transactionItemApiToTransactionItem).collect(Collectors.toList());
  }
}
