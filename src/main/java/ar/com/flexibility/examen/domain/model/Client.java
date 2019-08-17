package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Porque la principal diferencia entre las clases concretas son los datos que almacenan
@Table(name="CLIENTS")
public class Client {
	@Id
	@Column(name="CLIENT_ID")
	@SequenceGenerator(name = "client_seq_clientId", sequenceName = "seq_clientId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_clientId")
	private Long clientId;
	
	@Column(name="CUIT")
	private long cuit;
	
	@Column(name="DESCRIPTION")
	private String description;
}
