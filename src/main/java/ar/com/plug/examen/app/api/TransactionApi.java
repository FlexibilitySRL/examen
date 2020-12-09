package ar.com.plug.examen.app.api;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionApi {
	private List<ProductApi> product;
	private ClientApi client;
	private LocalDateTime date;

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
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
