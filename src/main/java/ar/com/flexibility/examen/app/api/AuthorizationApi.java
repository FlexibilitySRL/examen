package ar.com.flexibility.examen.app.api;

public class AuthorizationApi {
	long idPurchase;
	String status, reviewer, observations;
	public long getIdPurchase() {
		return idPurchase;
	}
	public void setIdPurchase(long idPurchase) {
		this.idPurchase = idPurchase;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	

}
