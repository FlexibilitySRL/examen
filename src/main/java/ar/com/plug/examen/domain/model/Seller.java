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
@DiscriminatorValue(value="SELLER")
public class Seller extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "seller")
	private List<Purchase> sellList;
	
	public Seller() {
		sellList = new ArrayList<Purchase>();
	}
	
	public Seller(Long id, String name, String surname, String email, List<Purchase> sellList) {
		super(id, name, surname, email);
		this.sellList = sellList;
	}

	public List<Purchase> getSellList() {
		return sellList;
	}

	public void setPurchaseList(List<Purchase> sellList) {
		this.sellList = sellList;
	}
	
	public void addPurchase(Purchase purchase) {
		this.getSellList().add(purchase);
	}

}