package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_clientId")
	private Long clientId;
	
	@Column(name="CUIT", unique=true, nullable=false)
	private long cuit;
	
	private Address commercialAddress;
	
	public Long getClientId() {
		return clientId;
	}
	
	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) {
		this.cuit = cuit;
	}

	public void setCommercialAddress(Address commercialAddress) {
		this.commercialAddress = commercialAddress;
	}

	@Override
	public int hashCode() {
		if ( this.clientId != null ) {
			return this.getClientId().hashCode();
		}
		else {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if ( ( other != null ) && ( other instanceof Client ) ) {
			return this.getClientId().equals(((Client) other).getClientId());
		}
		else {
			return false;
		}
	}

	public Address getCommercialAddress() {
		return commercialAddress;
	}
}
