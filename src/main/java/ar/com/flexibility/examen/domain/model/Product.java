package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", indexes = { 
		@Index(name = "idx_product_code", columnList = "code"), 
	}, uniqueConstraints = {
	    @UniqueConstraint (name = "uqx_product_code", columnNames = "code"),
	})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column (name = "amount", nullable = false, precision = 3)
	private int amount;
    @Column (name = "code", nullable = false, length = 50)
	private String code;
    @Column (name = "name", nullable = false, length = 150)
	private String name;
    @Column (name = "price", nullable = false, precision = 6, scale = 2)
	private double price;

	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	private List<Sale> sales = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Sale> getSales() {
		return sales;
	}
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
}
