package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import ar.com.flexibility.examen.domain.base.BaseEntity;

@Entity(name = "purchase_order")
public class PurchaseOrder extends BaseEntity {

	@ManyToMany
	@JoinTable(name = "purchase_order_product", joinColumns = @JoinColumn(name = "purchase_order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@Column(name = "status")
	private PurchaseOrderStatus status;

	@Column(name = "amount")
	private BigDecimal amount;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_datetime", updatable = false)
	private Date creationDateTime;

	public PurchaseOrder() {
	}

	public PurchaseOrder(List<Product> products, Customer customer, PurchaseOrderStatus status, BigDecimal amount) {
		super();
		this.products = products;
		this.customer = customer;
		this.status = status;
		this.amount = amount;
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

	public PurchaseOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseOrderStatus status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

}
