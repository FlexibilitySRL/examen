package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PURCHASETRANSACTION")
public class PurchaseTransaction {
	@Id
	@Column(name="PURCHASETRANSACTION_ID")
	@SequenceGenerator(name = "purchaseTransaction_seq_purchaseTransactionId", sequenceName = "seq_purchaseTransactionId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseTransaction_seq_purchaseTransactionId")
	private Long id;
	
	@OneToOne(optional = false)
	@JoinColumn(name="PURCHASEORDER_ID", nullable=false)
	private PurchaseOrder purchaseOrder;

	public Long getId() {
		return id;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
}
