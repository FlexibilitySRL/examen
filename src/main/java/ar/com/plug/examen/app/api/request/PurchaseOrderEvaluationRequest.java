package ar.com.plug.examen.app.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseOrderEvaluationRequest {
    @JsonProperty("purchase_order_id")
    private String purchaseOrderId;

    @JsonProperty("status")
    private String status;
}
