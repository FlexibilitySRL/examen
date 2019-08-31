package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "purchase")
public class PurchaseApi {

    @JsonProperty
    private Long id;
    @JsonProperty
    private Long clientId;
    @JsonProperty
    private Long productId;
    @JsonProperty
    private Integer units;
    @JsonProperty
    private double total;
    @JsonProperty
    private PurchaseStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    public void setSatus(PurchaseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PurchaseApi{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", units=" + units +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
