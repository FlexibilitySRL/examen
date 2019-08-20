package ar.com.flexibility.examen.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Address;

public class AddressDTO {
	@JsonProperty
	private String street; // Calle
	
	@JsonProperty
	private int number; // Número
	
	@JsonProperty
	private String neighbourhood; // Departamento
	
	@JsonProperty
	private String province; // Provincia
	
	@JsonProperty
	private String department; // Equivalente a partido
	
	@JsonProperty
	private String town; // Equivalente a localidad

	@JsonProperty
	private String country; // País
	
	/**
	 * @post Crea un DTO de la dirección especificada
	 */
	public AddressDTO(Address address) {
		if ( address == null )
			throw new NullPointerException();
		
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.neighbourhood = address.getNeighbourhood();
		this.province = address.getProvince();
		this.department = address.getDepartment();
		this.town = address.getTown();
		this.country = address.getCountry();
	}
	
	protected AddressDTO() {
		
	}
	
	/**
	 * @post Convierte a 'value object'
	 * @return
	 */
	public Address toValueObject() {
		return new Address(
			this.street,
			this.number,
			this.neighbourhood,
			this.town,
			this.department,
			this.province,
			this.country
		);
	}
	
	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public String getProvince() {
		return province;
	}

	public String getDepartment() {
		return department;
	}

	public String getTown() {
		return town;
	}

	public String getCountry() {
		return country;
	}
}
