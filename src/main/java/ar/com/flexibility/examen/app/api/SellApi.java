package ar.com.flexibility.examen.app.api;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sell entity.
 */
public class SellApi implements Serializable {

    private Long id;

    private Long productId;

    private Long sellerId;

    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SellApi sellApi = (SellApi) o;
        if(sellApi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sellApi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SellApi{" +
            "id=" + getId() +
            "}";
    }
}
