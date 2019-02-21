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
@Table(name="client_table")
public class Client{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	@Access(AccessType.FIELD)
	@OneToMany(targetEntity=Purchase.class, mappedBy="client",fetch = FetchType.LAZY)
	List<Purchase> purchaseList = new ArrayList<>();
	
	Double balance = 0D;

	public Client() {}
	
	
	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}
	
	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getIdClient() {
		return idClient;
	}
	
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	} 
	
	
	
}
