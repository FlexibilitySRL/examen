package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value="CLIENT")
public class Client extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "client")
	private List<Purchase> purchaseList;
	
	public Client() {
		purchaseList = new ArrayList<Purchase>();
	}
	
	public Client(Long id, String name, String surname, String email, List<Purchase> purchaseList) {
		super(id, name, surname, email);
		this.purchaseList = purchaseList;
	}

	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}
	
	public void addPurchase(Purchase purchase) {
		this.getPurchaseList().add(purchase);
	}
	
}