package ar.com.flexibility.examen.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

	private static final long serialVersionUID = 3874157336070095L;

	@Id
	@GeneratedValue
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

	private String mobile;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();

	public Customer(String name, String lastname, String email, String password, String mobile) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public Customer(String name, String lastname, String email, String password, String mobile, List<Address> addresses) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
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

	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setCustomer(this);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.setCustomer(null);
	}

}
