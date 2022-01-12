package ar.com.plug.examen.app.api.transaction;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequestApi {

	@NotNull
	@JsonProperty(required = true)
	private RequestId client;

	@JsonProperty
	private Set<TransactionItemRequestApi> items;

	public RequestId getClient() {
		return client;
	}

	public void setClient(RequestId client) {
		this.client = client;
	}

	public Set<TransactionItemRequestApi> getItems() {
		return items;
	}

	public void setItems(Set<TransactionItemRequestApi> items) {
		this.items = items;
	}

}
