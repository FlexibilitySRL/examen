package ar.com.flexibility.examen.domain.dto;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonRootName(value = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionPurchasesDTO {
    @JsonProperty
    @NotNull
    private String transactionId;
    @JsonProperty
    @Valid
    @NotNull
    private List<ProductDTO> product;
    @JsonProperty
    @Valid
    @NotNull
    private CustomerDTO customer;
    @JsonProperty
    @NotNull
    private Double total;
    @JsonProperty
    private Date buyDate;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<ProductDTO> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDTO> product) {
        this.product = product;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
