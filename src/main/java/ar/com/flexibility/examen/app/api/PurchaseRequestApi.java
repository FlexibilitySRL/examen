package ar.com.flexibility.examen.app.api;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRequestApi {
	
	String provider;
	List<PurchaseItemRequestApi> items;
	
	public PurchaseRequestApi() {
		super();
		items = new ArrayList<PurchaseItemRequestApi>();
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<PurchaseItemRequestApi> getItems() {
		return items;
	}
	public void setItems(List<PurchaseItemRequestApi> items) {
		this.items = items;
	}

}
