package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


/**
 *
 * @author Joan Rey
 */
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
	private String code;
    private String iban;
    private String names;
    private String surname;
    private String phone;
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerProduct> products;
    @Transient
    private List<?> transactions;
    
    public Customer() {
    	
    }
    /**
     * Declaro Constructor
     * @param id
     * @param name
     * @param code
     * @param iban
     * @param names
     * @param surname
     * @param phone
     * @param address
     * @param products
     * @param transactions
     */
    public Customer(long id, String name, String code, String iban, String names, String surname, String phone,
			String address, List<CustomerProduct> products, List<?> transactions) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.iban = iban;
		this.names = names;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.products = products;
		this.transactions = transactions;
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<CustomerProduct> getProducts() {
		return products;
	}
	public void setProducts(List<CustomerProduct> products) {
		this.products = products;
	}
	public List<?> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<?> transactions) {
		this.transactions = transactions;
	}
}
