package ar.com.flexibility.examen.domain.build;

import ar.com.flexibility.examen.domain.model.Client;

public class ClientBuilder {
	
	private String identifier;
	private String name;
	private String surname;

	private ClientBuilder () {
		this.identifier = "C123456";
		this.name = "Client Name";
		this.surname = "Client Surname";
	}

	public Client build() {
		Client client = new Client ();
		
		client.setIdentifier(this.identifier);
		client.setName(this.name);
		client.setSurname(this.surname);
		
		return client;
	}
	
	public static ClientBuilder builder() {
		return new ClientBuilder();
	}

}
