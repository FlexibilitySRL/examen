package ar.com.plug.examen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.domain.ItemProduct;
import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.domain.Transaction;
import ar.com.plug.examen.infrastructure.db.entity.ClientEntity;
import ar.com.plug.examen.infrastructure.db.entity.ItemProductEntity;
import ar.com.plug.examen.infrastructure.db.entity.ProductEntity;
import ar.com.plug.examen.infrastructure.db.entity.TransactionEntity;
import ar.com.plug.examen.shared.config.MenssageResponse;

public class TestUtil {

    public static final String ID_1 = "1";
    public static final String ID_2 = "2";
    public static final String ID_3 = "3";
    public static final String ID_11 = "11";
    public static final String EMAIL_1 = "email1@email.com";
    public static final String EMAIL_2 = "email2@email.com";
    public static final String EMAIL_11 = "email11@email.com";

    public static final double PRICE_PRODUCT = 10;

    public static Map<String, String> buidlMenssageResponse() {
        Map<String, String> messages = new HashMap<>();
        messages.put(MenssageResponse.SP, "");
        messages.put(MenssageResponse.OK, "");
        messages.put(MenssageResponse.BR400, "");
        messages.put(MenssageResponse.C401, "");
        messages.put(MenssageResponse.C402, "");
        messages.put(MenssageResponse.C403, "");
        messages.put(MenssageResponse.C404, "");
        messages.put(MenssageResponse.C405, "");
        messages.put(MenssageResponse.P401, "");
        messages.put(MenssageResponse.P402, "");
        messages.put(MenssageResponse.P403, "");
        messages.put(MenssageResponse.P404, "");
        messages.put(MenssageResponse.P405, "");
        messages.put(MenssageResponse.P406, "");
        messages.put(MenssageResponse.P407, "");
        messages.put(MenssageResponse.P408, "");
        messages.put(MenssageResponse.T401, "");
        messages.put(MenssageResponse.T402, "");
        messages.put(MenssageResponse.T403, "");
        messages.put(MenssageResponse.T404, "");
        return messages;
    }

    public static ClientEntity buidlClientEntity(String id) {
        return ClientEntity.builder()
                .id(id)
                .name("name" + id)
                .lastName("lastName" + id)
                .docNumber(id)
                .email("email" + id + "@email.com")
                .transactions(buildTransactionEntites(id, 1, false))
                .build();
    }

    public static List<ClientEntity> buidlClientEntityList(Integer n) {
        List<ClientEntity> clientEntities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            clientEntities.add(ClientEntity.builder()
                    .id(String.valueOf(i))
                    .name("name" + String.valueOf(i))
                    .lastName("lastName" + String.valueOf(i))
                    .docNumber(String.valueOf(i))
                    .email("email" + String.valueOf(i) + "@email.com")
                    .transactions(buildTransactionEntites(String.valueOf(i), 1, false))
                    .build());
        }
        return clientEntities;
    }

    public static Transaction buildTransaction(String clientId, Integer n) {
        return Transaction.builder()
                .id(ID_1)
                .clientId(clientId)
                .items(buidlItemProduct(n))
                .build();
    }

    public static List<ItemProduct> buidlItemProduct(Integer n) {
        List<ItemProduct> itemProducts = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            itemProducts.add(ItemProduct.builder()
                    .productId(String.valueOf(i))
                    .quantity(i)
                    .price(PRICE_PRODUCT)
                    .build());
        }
        return itemProducts;
    }

    public static Optional<ProductEntity> buidlProductEntity(String id) {
        return Optional.of(ProductEntity.builder()
                .id(id)
                .name("name1")
                .description("description1")
                .inventory(10)
                .price(PRICE_PRODUCT).barCode(id)
                .build());
    }

    public static List<ProductEntity> buidlProductEntityList(Integer n) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            productEntities.add(ProductEntity.builder()
                    .id(String.valueOf(i))
                    .name("name" + String.valueOf(i))
                    .description("description" + String.valueOf(i))
                    .inventory(10)
                    .price(PRICE_PRODUCT).barCode(String.valueOf(i))
                    .build());
        }
        return productEntities;
    }

    public static List<Transaction> buildTransactions(String clientId, Integer n) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            transactions.add(buildTransaction(String.valueOf(i), n));
        }
        return transactions;
    }

    public static List<ItemProductEntity> buidlItemProductEntities(Integer n) {
        List<ItemProductEntity> itemProducts = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            itemProducts.add(ItemProductEntity.builder()
                    .productEntity(buidlProductEntity(String.valueOf(i)).get())
                    .quantity(i)
                    .price(PRICE_PRODUCT)
                    .build());
        }
        return itemProducts;
    }

    public static TransactionEntity buildTransactionEntity(String clientId, Integer n) {
        return TransactionEntity.builder()
                .id(ID_1)
                .approved(false)
                .client(ClientEntity.builder()
                        .id(clientId).build())
                .items(buidlItemProductEntities(n))
                .build();
    }

    public static List<TransactionEntity> buildTransactionEntites(String clientId, Integer n, boolean approved) {
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            transactionEntities.add(TransactionEntity.builder()
                    .id(String.valueOf(i))
                    .approved(approved)
                    .client(ClientEntity.builder()
                            .id(clientId).build())
                    .items(buidlItemProductEntities(n))
                    .build());
        }
        return transactionEntities;
    }

    public static Client buidlClientFilter1() {
        return Client.builder().name("name" + ID_1).lastName("lastName" + ID_1).docNumber(ID_1).email(EMAIL_1)
                .build();
    }

    public static Client buidlClient(String id) {
        return Client.builder().name("name" + id).lastName("lastName" + id).docNumber(id)
                .email("email" + id + "@email.com")
                .build();
    }

    public static Client buidlClientUpDate(String id) {
        return Client.builder().id(id).name("name" + id).lastName("lastName" + id).docNumber(id)
                .email("email" + id + 1 + "@email.com")
                .build();
    }

    public static Product buildProductFilter1() {
        return Product.builder()
                .id(ID_1)
                .name("name" + ID_1)
                .description("description" + ID_1)
                .inventory(10)
                .price(PRICE_PRODUCT).barCode(ID_1)
                .build();
    }

    public static Product buildProduct(String id) {
        return Product.builder()
                .id(id)
                .name("name" + id)
                .description("description" + id)
                .inventory(10)
                .price(PRICE_PRODUCT).barCode(id)
                .build();
    }
}
