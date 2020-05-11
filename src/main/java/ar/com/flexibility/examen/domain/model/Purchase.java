package ar.com.flexibility.examen.domain.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="purchases")
public class Purchase {
	
	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	Date creationDate;
    Date checkDate;
	String status;// PENDING | APPROVED | REJECTED
	String observations;
	String reviewer;
	String provider;
	
	@OneToMany(mappedBy="purchase", orphanRemoval=true, cascade={CascadeType.ALL})
	List<PurchaseItem> items;
	
	public Purchase() {
		super();
		this.creationDate = new Date();
		this.status = "PENDING";
		items = new ArrayList<>();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItem> items) {
		this.items = items;
	}
	
	@Transient
	public double getTotalAmount(){
		double total = items.stream().mapToDouble(item -> item.getPrice()).sum();
		return total;
	}
	
	@Transient
	public double getTotalProductCount(){
		int total = items.stream().mapToInt(item -> item.getQuantity()).sum();
		return total;
	}
	
	@Transient
	public boolean isApproved(){		
		return status.equalsIgnoreCase("approved");
	}
	
}