package ar.com.flexibility.examen.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="PURCHASETRANSACTION")
@Immutable
public class PurchaseTransaction {
	@Id
	@Column(name="PURCHASETRANSACTION_ID")
	@SequenceGenerator(name = "purchaseTransaction_seq_purchaseTransactionId", sequenceName = "seq_purchaseTransactionId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseTransaction_seq_purchaseTransactionId")
	private Long id;
	
	@OneToOne(optional = false)
	@JoinColumn(name="PURCHASEORDER_ID", nullable=false)
	private PurchaseOrder purchaseOrder;
	
	@Column(name="APPROVALDATE")
	private Date approvalDate;
	
	public PurchaseTransaction(PurchaseOrder purchaseOrder, Date approvalDate) {
		if ( ( purchaseOrder != null ) && ( approvalDate != null) ) {
			this.purchaseOrder = purchaseOrder;
			this.approvalDate = approvalDate;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected PurchaseTransaction() {
		
	}

	public Long getId() {
		return id;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	
	public Date getApprovalDate() {
		return this.approvalDate;
	}
}
