/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import ar.com.flexibility.examen.domain.exception.ProductWithoutStock;

/**
 * @author rosalizaracho
 *
 */
@Entity
@Table(name="product_table")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	private String name;
	
	private Double price;
	
	@Access(AccessType.FIELD)
	@ManyToOne(targetEntity = Seller.class,fetch = FetchType.LAZY)
	@JoinColumn(name="idSeller")
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
		this.stock = stock;
		this.seller = seller;
	}
	
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
		if(!StringUtils.isEmpty(name)) {
			this.name = name;	
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
	  if(price instanceof Double) {
		  this.price = price;
	  }	
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
		if(stock instanceof Integer) {
			this.stock = stock;		
		}
	}

	public boolean isStock(int items) throws ProductWithoutStock {
		if(stock < items) {
			throw new ProductWithoutStock();
		}
		return true;
	}

	public void discountStock(int items) {
		this.stock = this.stock - items;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}
	
	
	
	
	
}
