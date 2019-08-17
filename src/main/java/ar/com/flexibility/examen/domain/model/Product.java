package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTS")
public class Product {
	@Id
	@Column(name="PRODUCT_ID")
	@SequenceGenerator(name = "product_seq_productId", sequenceName = "seq_productId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_productId")
	private Long productId;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="UNITPRICE", nullable=false)
	private BigDecimal unitPrice;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		if ( unitPrice != null ) {
			this.unitPrice = unitPrice;
		}
		else {
			throw new NullPointerException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getProductId() {
		return productId;
	}
	
	@Override
	public int hashCode() {
		if ( this.getProductId() != null ) {
			return this.getProductId().hashCode();
		}
		else {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if ( ( other != null ) && ( other instanceof Product ) ) {
			return ( other == this ) || this.getProductId().equals(((Product) other).getProductId());
		}
		else {
			return false;
		}
	}
}
