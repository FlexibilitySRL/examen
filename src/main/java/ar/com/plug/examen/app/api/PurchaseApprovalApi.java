package ar.com.plug.examen.app.api;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseApprovalApi {

	private List<ProductApi> product;
	private ClientApi client;
	
	public PurchaseApprovalApi(List<ProductApi> product, ClientApi client) {
		super();
		this.product = product;
		this.client = client;
	}
	
	public PurchaseApprovalApi() {
		super();
	}

	public List<ProductApi> getProduct() {
		return product;
	}
	public void setProduct(List<ProductApi> product) {
		this.product = product;
	}
	public ClientApi getClient() {
		return client;
	}
	public void setClient(ClientApi client) {
		this.client = client;
	}
	
}
