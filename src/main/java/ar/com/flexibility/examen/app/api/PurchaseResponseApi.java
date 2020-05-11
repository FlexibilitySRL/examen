package ar.com.flexibility.examen.app.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.flexibility.examen.domain.model.Purchase;

public class PurchaseResponseApi {
	
	long id;
	Date creationDate;
    Date checkDate;
	String status;// PENDING | APPROVED | REJECTED
	String observations;
	String reviewer;
	String provider;
	
	public PurchaseResponseApi() {
	}
	
	public PurchaseResponseApi(Purchase purchaseOrder) {
		
		this.id = purchaseOrder.getId();
		
		this.creationDate = purchaseOrder.getCreationDate();
		this.checkDate    = purchaseOrder.getCheckDate();
		this.status       = purchaseOrder.getStatus();
		this.observations = purchaseOrder.getObservations();
		this.reviewer     = purchaseOrder.getReviewer();
		this.provider     = purchaseOrder.getProvider();
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getReviewDate() {
		return checkDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.checkDate = reviewDate;
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
	public String getProveedor() {
		return provider;
	}
	public void setProveedor(String proveedor) {
		this.provider = proveedor;
	}

}
