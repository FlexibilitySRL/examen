/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rosalizaracho
 *
 */
@Entity
@Table(name="seller_table")
public class Seller{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSeller;
	
	@Access(AccessType.FIELD)
	@OneToMany(targetEntity= Product.class,mappedBy="seller",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	List<Product> productList = new ArrayList<>();
	
	Double balance = 0D;
	
	public Seller() {}
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(Long idSeller) {
		this.idSeller = idSeller;
	}

	public void removeProduct(Product product) {
		this.productList.remove(product);
		
	}

	public void addProduct(Product product) {
		this.productList.add(product);
	}

	public Product findProduct(Long idProduct) {
		for(Product product : productList) {
			if(product.getIdProduct().equals(idProduct)) {
				return product;
			}
		}
		return null;
	}

	public void updateBalance(Double amount) {
		this.balance += amount;
		
	}
}
