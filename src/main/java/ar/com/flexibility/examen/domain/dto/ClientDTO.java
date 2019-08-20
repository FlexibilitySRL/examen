package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ar.com.flexibility.examen.domain.model.Client;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
		)
@JsonSubTypes({
	@Type(value = LegalClientDTO.class, name = "legal"),
	@Type(value = NaturalClientDTO.class, name = "natural")
})
public abstract class ClientDTO {
	@JsonProperty
	private long cuit;
	
	@JsonProperty
	private AddressDTO commercialAddress;

	/**
	 * @post Crea un DTO para el cliente especificado
	 */
	public ClientDTO(Client client) {
		if ( client == null )
			throw new NullPointerException();
		
		this.cuit = client.getCuit();
		this.commercialAddress = new AddressDTO( client.getCommercialAddress() );
	}
	
	protected ClientDTO() {
		
	}
	
	public long getCuit() {
		return cuit;
	}

	public AddressDTO getCommercialAddress() {
		return commercialAddress;
	}
	
	/**
	 * @post Convierte a entidad
	 * @param <R>
	 * @param visitor
	 * @return
	 */
	public abstract Client toEntity();
	
	public abstract <R> R accept(ClientDTOVisitor<R> visitor);
}
