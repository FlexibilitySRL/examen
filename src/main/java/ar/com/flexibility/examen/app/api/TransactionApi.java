package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonRootName(value = "transaction")
public class TransactionApi {

    private Long id;
    @NotNull
    private List<Long> productId;
    @NotNull
    private Long clientId;
    @NotNull
    private Double price;
    private Status status;

    public TransactionApi() {
    }

    public TransactionApi(Long id, List<Long> productId, Long clientId, Double price, Status status) {
        this.id = id;
        this.productId = productId;
        this.clientId = clientId;
        this.price = price;
        this.status = status;
    }

    public TransactionApi(List<Long> productId, Long clientId, Double price, Status status) {
        this.productId = productId;
        this.clientId = clientId;
        this.price = price;
        this.status = status;
    }
}
