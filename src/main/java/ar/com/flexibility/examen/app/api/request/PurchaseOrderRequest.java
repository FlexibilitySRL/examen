package ar.com.flexibility.examen.app.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseOrderRequest {

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("amount")
    private Integer amount;






    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
