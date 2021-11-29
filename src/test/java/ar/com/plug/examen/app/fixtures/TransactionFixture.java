package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.dto.ProductStockDto;
import ar.com.plug.examen.app.dto.TransactionDto;
import ar.com.plug.examen.app.dto.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItems;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TransactionFixture {

    public static TransactionDto getTransactionApiWithId(Long id) {

        TransactionDto transactionApi = new TransactionDto();
        transactionApi.setId(id);

        return transactionApi;
    }

    public static TransactionDto getTransactionApi() {

        TransactionDto transactionApi = new TransactionDto();
        transactionApi.setStatus(TransactionStatusEnum.PENDING);
        transactionApi.setCreationDate(new Date());
        transactionApi.setClient(ClientFixture.getClientApi());
        transactionApi.setSeller(SellerFixture.getSellerApi());

        return transactionApi;
    }

    public static TransactionApiRequest getTransactionApiRequest() {

        TransactionApiRequest transactionApiRequest = TransactionApiRequest.builder()
                .clientId(1L)
                .sellerId(2L)
                .build();

        ProductStockDto product = ProductStockDto.builder()
                .idProduct(1L)
                .quantity(10L)
                .build();

        List<ProductStockDto> listProducts = new ArrayList<>();
        listProducts.add(product);
        transactionApiRequest.setListProducts(listProducts);

        return transactionApiRequest;
    }

    public static List<TransactionDto> getTransactionApiList(TransactionDto transactionApi1, TransactionDto transactionApi2) {

        List<TransactionDto> listTransactions = new ArrayList<>();
        listTransactions.add(transactionApi1);
        listTransactions.add(transactionApi2);

        return listTransactions;
    }

    public static Transaction getTransactionWithId(Long id) {

        Transaction transaction = Transaction.builder()
                .id(id)
                .build();

        return transaction;
    }

    public static Transaction getTransaction() {

        List<TransactionItems> listItems = new ArrayList<>();
        listItems.add(TransactionItems.builder().build());

        Transaction transaction = Transaction.builder()
                .creationDate(new Date())
                .client(Client.builder().build())
                .seller(Seller.builder().build())
                .status(TransactionStatusEnum.APPROVED)
                .transactionItems(listItems)
                .build();

        return transaction;
    }
}