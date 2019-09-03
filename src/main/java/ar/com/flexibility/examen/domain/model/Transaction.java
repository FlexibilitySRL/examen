package ar.com.flexibility.examen.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ar.com.flexibility.examen.domain.base.BaseEntity;

@Entity(name = "transaction")
public class Transaction extends BaseEntity {

	@ManyToMany
	@JoinTable(name = "transaction_product", joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	public Transaction() {
	}

	public Transaction(List<Product> products, Customer customer) {
		super();
		this.products = products;
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
