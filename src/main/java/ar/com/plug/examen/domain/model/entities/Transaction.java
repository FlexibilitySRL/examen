package ar.com.plug.examen.domain.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.plug.examen.domain.model.Status;
import lombok.Data;

@Entity
@Table(name="transactions")
@Data
public class Transaction {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "status")
	private Status status;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> products;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = true)
	private Client client;

	@Column(name = "totalValue")
	private int totalValue;
	

}
