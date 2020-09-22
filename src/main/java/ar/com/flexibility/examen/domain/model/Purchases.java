package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PURCHASES")
public class Purchases {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotEmpty(message = "price_empty")
    @Column(name="PRICE", nullable=false)
	private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    private Customer customer;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="PRODUCT_ID", nullable=false)
    private Product product;

    @NotEmpty(message = "create_empty")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE", nullable=false)
    private Date create;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
    
}
