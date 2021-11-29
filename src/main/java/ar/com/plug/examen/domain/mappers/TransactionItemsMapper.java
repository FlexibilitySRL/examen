package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.dto.TransactionItemsDto;
import ar.com.plug.examen.domain.model.TransactionItems;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionItemsMapper {

    @Autowired
    ProductMapper productMapper;

    public TransactionItemsDto transactionItemsToTransactionItemsApi(TransactionItems transactionItems) {

        TransactionItemsDto transactionItemsApi = TransactionItemsDto
                .builder()
                .product(productMapper.productToProductApi(transactionItems.getProduct()))
                .quantity(transactionItems.getQuantity())
                .transaction(transactionItems.getTransaction())
                .build();

        return transactionItemsApi;
    }

    public TransactionItems transactionItemsApiToTransactionItems(TransactionItemsDto transactionItemsApi) {

        TransactionItems transactionItems = TransactionItems
                .builder()
                .product(productMapper.productApiToProduct(transactionItemsApi.getProduct()))
                .quantity(transactionItemsApi.getQuantity())
                .transaction(transactionItemsApi.getTransaction())
                .build();

        return transactionItems;
    }

    public List<TransactionItemsDto> transactionItemsToListTransactionitemsApi(List<TransactionItems> transactionItems) {
        return transactionItems.stream().map(this::transactionItemsToTransactionItemsApi).collect(Collectors.toList());
    }

    public List<TransactionItems> transactionItemsApiToListTransactionitems(List<TransactionItemsDto> transactionItems) {
        return transactionItems.stream().map(this::transactionItemsApiToTransactionItems).collect(Collectors.toList());
    }

}
