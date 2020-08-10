package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "client", indexes = { 
		@Index(name = "idx_client_identifier", columnList = "identifier"), 
	}, uniqueConstraints = {
	    @UniqueConstraint (name = "uqx_client_identifier", columnNames = "identifier"),
	})
public class Client extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy="client", fetch = FetchType.LAZY)
	private List<Sale> purchases = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Sale> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Sale> purchases) {
		this.purchases = purchases;
	}
	
}
