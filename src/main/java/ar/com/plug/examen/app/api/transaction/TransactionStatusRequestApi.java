package ar.com.plug.examen.app.api.transaction;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.plug.examen.domain.model.Transaction.TransactionStatusEnum;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionStatusRequestApi {
	
	@NotNull
	@JsonProperty(required = true)
	private Long id;
	
	@NotNull
	@JsonProperty(required = true)
	private TransactionStatusEnum status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TransactionStatusEnum getStatus() {
		return status;
	}
	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}
	
	

}
