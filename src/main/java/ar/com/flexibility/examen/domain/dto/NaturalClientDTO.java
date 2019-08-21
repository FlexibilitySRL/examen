package ar.com.flexibility.examen.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.LegalClient;
import ar.com.flexibility.examen.domain.model.NaturalClient;
import ar.com.flexibility.examen.domain.service.exceptions.UnexpectedNullValueException;

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
		
		this.updateEntity(client);
		
		return client;
	}
	
	/**
	 * @post Actualiza la entidad
	 */
	public void updateEntity(NaturalClient client) {
		if ( client == null )
			throw new NullPointerException();
		
		client.setCuit(client.getCuit());
		
		if ( this.getCommercialAddress() == null )
			throw new UnexpectedNullValueException();
		
		client.setCommercialAddress( this.getCommercialAddress().toValueObject() );
		
		if ( this.getName() == null )
			throw new UnexpectedNullValueException();
		
		client.setName(client.getName());
		
		if ( this.getSurname() == null )
			throw new UnexpectedNullValueException();
		
		client.setSurname(client.getSurname());
		
		client.setDni(client.getDni());
		
		if ( this.getBirthday() == null )
			throw new UnexpectedNullValueException();
		
		client.setBirthday(client.getBirthday());
		
		if ( this.getResidentialAddress() == null )
			throw new UnexpectedNullValueException();
		
		client.setResidentialAddress(this.residentialAddress.toValueObject());
	}
	
	@Override
	public <R> R accept(ClientDTOVisitor<R> visitor) {
		return visitor.visit(this);
	}

}
