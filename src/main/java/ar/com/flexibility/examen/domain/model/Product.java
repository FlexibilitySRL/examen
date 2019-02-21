/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rosalizaracho
 *
 */
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	private String name;
	
	private Double price;
	
	private Seller seller;
	
	private Integer stock;
	
	public Product() {}

	public Product(long id, String name, Double price, Seller seller, int stock) {
		this.idProduct = id;
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.stock = stock;
	}

	public Product(String name, double price, Seller seller, int stock) {
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.stock = stock;
	}
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(targetEntity = Seller.class,fetch = FetchType.LAZY)
	@JoinColumn(name="idSeller")
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	

}
