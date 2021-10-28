package ar.com.plug.examen.dao.entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.com.plug.examen.domain.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transactions")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
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
