package ar.com.plug.examen.domain.model;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table
public class Client {
	
	@Id
	private Long id;

	@Column
	private String name;
	
	@OneToMany(mappedBy="client", cascade= CascadeType.ALL)
	private List<Transaction> transactions;
	
	public Client () {}
	
	public Client (Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
