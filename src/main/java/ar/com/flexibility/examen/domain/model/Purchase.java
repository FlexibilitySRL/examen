/**
 * 
 */
package ar.com.flexibility.examen.domain.model;

import java.sql.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rosalizaracho
 *
 */
@Entity
@Table(name="purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPurchase;

	List<Product> productList = new ArrayList<Product>();

	Client client; 
	
	private Date dateOfPurchase;
	
	public Purchase() {}
	
	@Access(AccessType.PROPERTY)
	@OneToMany(targetEntity= Product.class, mappedBy="purchase",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	@Access(AccessType.PROPERTY)
	@ManyToOne(targetEntity= Client.class, fetch = FetchType.LAZY)
	@JoinColumn(name="idClient")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}
	
}
