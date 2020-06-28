package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CellPhone> cellPhones = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCarts = new ArrayList<>();
	
	public Customer(String name, String lastname, String email, List<CellPhone> cellPhones) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.cellPhones = cellPhones;
		this.addresses = new ArrayList<Address>();
	}

	public Customer(String name, String lastname, String email, List<CellPhone> cellPhones, List<Address> addresses) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.cellPhones = cellPhones;
		this.addresses = addresses;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public Customer(String name, String lastname, String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.addresses = new ArrayList<Address>();
		this.cellPhones = new ArrayList<CellPhone>();
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setCustomer(this);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.setCustomer(null);
	}
	
	public void addCellPhone(CellPhone cellPhone) {
		this.cellPhones.add(cellPhone);
		cellPhone.setCustomer(this);
	}

	public void removeCellPhone(CellPhone cellPhone) {
		this.addresses.remove(cellPhone);
		cellPhone.setCustomer(null);
	}
	
	public List<CellPhone> getCellPhones() {
		return cellPhones;
	}
	
	public void setCellPhones(List<CellPhone> cellPhones) {
		this.cellPhones = cellPhones;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	@JsonIgnore
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}
	
	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
