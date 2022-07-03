package ar.com.plug.examen.app.api;


import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.model.Seller;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PurchaseDto
{
    private Long id;

    private BigDecimal total;

    private int quantity;

    private Boolean approved;

    private Long clientId;

    private Long productId;

    private Long sellerId;

    public PurchaseDto() {
    }

    public PurchaseDto(Long id, BigDecimal total, int quantity, Boolean approved, Long clientId, Long productId, Long sellerId) {
        this.id = id;
        this.total = total;
        this.quantity = quantity;
        this.approved = approved;
        this.clientId = clientId;
        this.productId = productId;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
