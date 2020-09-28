package ar.com.plug.examen.app.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseOrderRequest {
    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("amount")
    private Integer amount;
}
