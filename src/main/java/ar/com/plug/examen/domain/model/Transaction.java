package ar.com.plug.examen.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany (fetch = FetchType.LAZY )
	private List<Product> product;
	
	@OneToOne (fetch = FetchType.LAZY,cascade= CascadeType.MERGE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Client client;
	
	private LocalDateTime date;
	
	public Transaction() {
		super();
	}
	public Transaction(Long id, List<Product> product, Client client, LocalDateTime date) {
		super();
		this.id = id;
		this.product = product;
		this.client = client;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
