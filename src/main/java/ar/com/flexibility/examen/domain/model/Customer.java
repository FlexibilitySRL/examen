package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "name cannot be null")
	private String name;

	@NotBlank(message = "lastname cannot be null")
	private String lastname;

	@NotBlank(message = "email cannot be null")
	@Email(message = "Bad email format")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "password cannot be null")
	private String password;

	private String cellPhone;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

//	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//	private List<Order> orders = new ArrayList<>();
	
	public Customer(String name, String lastname, String email, String password, String cellPhone) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.cellPhone = cellPhone;
	}

	public Customer(String name, String lastname, String email, String password, String cellPhone, List<Address> addresses) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.cellPhone = cellPhone;
		this.addresses = addresses;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

//	@JsonIgnore
//	public List<Order> getOrders() {
//		return orders;
//	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setCustomer(this);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.setCustomer(null);
	}
}
