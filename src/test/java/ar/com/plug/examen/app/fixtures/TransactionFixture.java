package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.api.ProductStockApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.app.api.TransactionApiRequest;
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

    public static TransactionApiRequest getTransactionApiRequest() {

        TransactionApiRequest transactionApiRequest = TransactionApiRequest.builder()
                .clientId(1L)
                .sellerId(2L)
                .build();

        ProductStockApi product = ProductStockApi.builder()
                .idProduct(1L)
                .quantity(10L)
                .build();

        List<ProductStockApi> listProducts = new ArrayList<>();
        listProducts.add(product);
        transactionApiRequest.setListProducts(listProducts);

        return transactionApiRequest;
    }

    public static List<TransactionApi> getTransactionApiList(TransactionApi transactionApi1, TransactionApi transactionApi2) {

        List<TransactionApi> listTransactions = new ArrayList<>();
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