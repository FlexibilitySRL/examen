package ar.com.plug.examen.domain.builderPattern;

import java.util.List;

import ar.com.plug.examen.entities.Client;
import ar.com.plug.examen.entities.Transaction;

public class ClientBuilder {
	
	private Client client;
	
	public ClientBuilder() {
		this.reset();
	}
	
	public void reset() {
		this.client = new Client();
	}
	
	public ClientBuilder withID(Long id) {
		this.client.setId(id);
		return this;
	}
	
	public ClientBuilder withFirstname(String name) {
		this.client.setFirstname(name);
		return this;
	}
	
	public ClientBuilder withLastname(String name) {
		this.client.setLastname(name);
		return this;
	}
	
	public ClientBuilder withEmail(String email) {
		this.client.setEmail(email);
		return this;
	}
	
	public ClientBuilder withTransaction(List<Transaction> list) {
		this.client.setTransaction(list);
		return this;
	}
	
	public Client build() {
		return new Client(this.client.getId(), this.client.getFirstname(), this.client.getLastname(), this.client.getEmail(), this.client.getTransaction());
	}

}
