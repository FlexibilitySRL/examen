package ar.com.plug.examen.app.api;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.plug.examen.domain.enums.PurchaseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PurchaseDetailAPI", description = "Detalle de compras para ser visualizada desde el cliente o el vendedor")
@JsonRootName(value = "purchaseDetail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDetailApi {
	
	@ApiModelProperty(value = "Purchase id")
	@JsonProperty
	private Long id;

	@ApiModelProperty(value = "Purchase description", required = true)
	@JsonProperty
	private String description;
	
	@ApiModelProperty(value = "Purchase buyer name")
	@JsonProperty
	private String buyerName;
	
	@ApiModelProperty(value = "Purchase client", required = true)
	@JsonProperty
	@JsonBackReference(value = "purchaseList")
	private ClientApi client;
	
	@ApiModelProperty(value = "Purchase seller name")
	@JsonProperty
	private String sellerName;
	
	@ApiModelProperty(value = "Purchase seller", required = true)
	@JsonProperty
	@JsonBackReference(value = "sellList")
	private SellerApi seller;
	
	@ApiModelProperty(value = "Purchase items", required = true)
	@JsonProperty
	private List<PurchaseDetailItemApi> items;
	
	@ApiModelProperty(value = "Purchase total value", required = true)
	@JsonProperty
	private BigDecimal total;
	
	@ApiModelProperty(value = "Purchase status", required = true)
	@JsonProperty
	@Enumerated(EnumType.STRING)
	private PurchaseStatus status;
	
	public PurchaseDetailApi() {
		items = new ArrayList<PurchaseDetailItemApi>();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ClientApi getClient() {
		return client;
	}

	public void setClient(ClientApi client) {
		this.client = client;
	}

	public SellerApi getSeller() {
		return seller;
	}

	public void setSeller(SellerApi seller) {
		this.seller = seller;
	}

	public void setItems(List<PurchaseDetailItemApi> items) {
		this.items = items;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (PurchaseDetailItemApi item : getItems()) {
			total = total.add(item.getTotal(), MathContext.DECIMAL32);
		}
		return total;
	}

	public void setStatus(PurchaseStatus status) {
		this.status = status;
	}
	
	public PurchaseStatus getStatus() {
		return status;
	}

	public List<PurchaseDetailItemApi> getItems() {
		return items;
	}
	
	public void addItem(PurchaseDetailItemApi item) {
		getItems().add(item);
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	
}
