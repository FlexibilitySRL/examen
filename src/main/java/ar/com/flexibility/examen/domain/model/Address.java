package ar.com.flexibility.examen.domain.model;

import javax.persistence.Embeddable;

@Embeddable
public final class Address {
	private String street; // Calle
	private int number; // Número
	private String neighbourhood; // Departamento
	private String province; // Provincia
	private String department; // Equivalente a partido
	private String town; // Equivalente a localidad
	private String country; // País
	
	public Address(String street, int number, String neighbourhood, String town, String department, String province, String country) {
		if (
				( street != null ) &&
				( neighbourhood != null ) &&
				( country != null ) &&
				( province != null ) &&
				( department != null ) &&
				( town != null ) ) {
			this.street = street;
			this.number = number;
			this.neighbourhood = neighbourhood;
			this.country = country;
			this.province = province;
			this.department = department;
			this.town = town;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((neighbourhood == null) ? 0 : neighbourhood.hashCode());
		result = prime * result + number;
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (neighbourhood == null) {
			if (other.neighbourhood != null)
				return false;
		} else if (!neighbourhood.equals(other.neighbourhood))
			return false;
		if (number != other.number)
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		return true;
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
