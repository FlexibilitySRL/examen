package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private List<ProductDto> products;

    @JsonProperty
    private ClientDto client;

    @JsonProperty
    private SellerDto seller;

    @JsonProperty
    private long sellerId;

    @JsonProperty
    private long clientId;

    @JsonProperty
    private List<Long> productsIds;

    @JsonProperty
    private Date date;

    @JsonProperty
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public SellerDto getSeller() {
        return seller;
    }

    public void setSeller(SellerDto seller) {
        this.seller = seller;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static PurchaseDto fromDomain(Purchase purchase) {
        final PurchaseDto purchaseDto = new PurchaseDto();
        if(purchase != null) {
            purchaseDto.setId(purchase.getId());
            purchaseDto.setDate(purchase.getTransactionDate());
            purchaseDto.setClient(new ClientDto(purchase.getClient()));
            purchaseDto.setSeller(new SellerDto(purchase.getSeller()));
            purchaseDto.setProducts(purchase.getProducts()
                    .stream().map(ProductDto::fromDomain).collect(Collectors.toList()));
            purchaseDto.setStatus(purchase.getStatus().toString());
        }
        return purchaseDto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
