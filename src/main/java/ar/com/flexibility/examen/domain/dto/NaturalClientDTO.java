package ar.com.flexibility.examen.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.NaturalClient;

public final class NaturalClientDTO extends ClientDTO {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String surname;
	
	@JsonProperty
	private long dni;
	
	@JsonProperty
	private Date birthday;

	@JsonProperty
	private AddressDTO residentialAddress;
	
	/**
	 * @post Crea un DTO para el cliente físico especificado
	 */
	public NaturalClientDTO(NaturalClient naturalClient) {
		super(naturalClient);
		
		this.name = naturalClient.getName();
		this.surname = naturalClient.getSurname();
		this.dni = naturalClient.getDni();
		this.birthday = naturalClient.getBirthday();
		this.residentialAddress = new AddressDTO( naturalClient.getResidentialAddress() );
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public long getDni() {
		return dni;
	}

	public Date getBirthday() {
		return birthday;
	}

	public AddressDTO getResidentialAddress() {
		return residentialAddress;
	}
	
	@Override
	public NaturalClient toEntity() {
		NaturalClient client = new NaturalClient();
		
		client.setCuit(client.getCuit());
		client.setCommercialAddress( this.getCommercialAddress().toValueObject() );
		client.setName(client.getName());
		client.setSurname(client.getSurname());
		client.setDni(client.getDni());
		client.setBirthday(client.getBirthday());
		client.setResidentialAddress(this.residentialAddress.toValueObject());
		
		return client;
	}
	
	@Override
	public <R> R accept(ClientDTOVisitor<R> visitor) {
		return visitor.visit(this);
	}

}
