package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325365040953892395L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_customer")
	@SequenceGenerator(sequenceName = "SEQ_CUSTOMER", name = "seq_customer", allocationSize = 1, initialValue = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "customerName")
	@NotNull
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	@NotNull
	private String email;
	
	/*@Column(name = "creditLimit")
	@NotNull
	private Double creditLimit;*/
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}*/
}
