package ar.com.flexibility.examen.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASETRANSACTION")
public class PurchaseTransaction {
	@Id
	@Column(name="PURCHASETRANSACTION_ID")
	@SequenceGenerator(name = "purchaseTransaction_seq_purchaseTransactionId", sequenceName = "seq_purchaseTransactionId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseTransaction_seq_purchaseTransactionId")
	private Long purchaseTransactionId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID", nullable=false)
	private Client client;
	
	public PurchaseTransaction(Client client) {
		if ( client != null ) {
			this.client = client;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected PurchaseTransaction() {
		
	}
	
	public Long getPurchaseTransactionId() {
		return purchaseTransactionId;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	@Override
	public int hashCode() {
		if ( this.getPurchaseTransactionId() != null ) {
			return this.getPurchaseTransactionId().hashCode();
		}
		else {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if ( ( other != null ) && ( other instanceof PurchaseTransaction ) ) {
			return this.getPurchaseTransactionId().equals(((PurchaseTransaction) other).getPurchaseTransactionId());
		}
		else {
			return false;
		}
	}
}
