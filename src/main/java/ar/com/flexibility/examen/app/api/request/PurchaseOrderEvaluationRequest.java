package ar.com.flexibility.examen.app.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrderEvaluationRequest {

    @JsonProperty("purchase_order_id")
    private String purchaseOrderId;

    @JsonProperty("status")
    private String status;





    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
