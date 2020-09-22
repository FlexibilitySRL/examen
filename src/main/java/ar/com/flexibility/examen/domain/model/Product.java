package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import ar.com.flexibility.examen.domain.enums.ProductStatus;

@Entity
@Table(name="PRODUCTS")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotEmpty(message = "code_empty")
    @Column(name="CODE", nullable=false,unique=true)
    private String code;
    
    @NotEmpty(message = "description_empty")
    @Column(name="DESCRIPTION", nullable=false)
    private String description;
	
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS",nullable=false)
    private ProductStatus status;
    
    @Column(name="STOCK",nullable=false)
    private Long stock;

    @NotEmpty(message = "price_empty")
    @Column(name="PRICE", nullable=false)
	private BigDecimal price;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
}
