package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.flexibility.examen.domain.base.BaseEntity;

@Entity(name = "customer")
public class Customer extends BaseEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "cuit")
	private String cuit;

	public Customer(String email, String name, String cuit) {
		super();
		this.email = email;
		this.name = name;
		this.cuit = cuit;
	}

	public Customer() {
		super();
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
