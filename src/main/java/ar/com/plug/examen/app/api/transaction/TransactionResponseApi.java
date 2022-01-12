package ar.com.plug.examen.app.api.transaction;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.plug.examen.app.api.client.ClientResponseApi;
import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseApi {

	@JsonProperty
	private Long id;

	@JsonProperty
	private ClientResponseApi client;

	@JsonProperty
	private Set<TransactionItemResponseApi> items;

	@JsonProperty
	private Date creationDate;

	@JsonProperty
	private TransactionStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientResponseApi getClient() {
		return client;
	}

	public void setClient(ClientResponseApi client) {
		this.client = client;
	}

	public Set<TransactionItemResponseApi> getItems() {
		return items;
	}

	public void setItems(Set<TransactionItemResponseApi> items) {
		this.items = items;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

}
