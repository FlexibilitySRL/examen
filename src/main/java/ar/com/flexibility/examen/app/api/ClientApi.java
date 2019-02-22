/**
 * 
 */
package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Client;

/**
 * @author ro
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientApi {
	
	@JsonProperty
	private Double balance;
	
	@JsonProperty
	private Long idClient;
	
	public ClientApi(Client client) {
		this.setBalance(client.getBalance());
		this.setIdClient(client.getIdClient());
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

}
