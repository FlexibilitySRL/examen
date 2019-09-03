package ar.com.flexibility.examen.domain.dto;

import ar.com.flexibility.examen.domain.base.BaseDTO;

public class CustomerDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String name;
	private String cuit;

	public CustomerDTO(String email, String name, String cuit) {
		super();
		this.email = email;
		this.name = name;
		this.cuit = cuit;
	}

	public CustomerDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
