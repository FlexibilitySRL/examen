package ar.com.flexibility.examen.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Address {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "country cannot be null")
	private String country;

	@NotBlank(message = "province cannot be null")
	private String province;

	@NotBlank(message = "city cannot be null")
	private String city;

	@NotBlank(message = "direction cannot be null")
	private String direction;
	
	@NotBlank(message = "number cannot be null")
	private Integer number;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

	public Address(String country, String province, String city, String direction, Integer number) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.direction = direction;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
