package ar.com.flexibility.examen.domain.model;

import java.util.Date;
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
@Table(name="PURCHASEORDER")
public class PurchaseOrder {
	@Id
	@Column(name="PURCHASEORDER_ID")
	@SequenceGenerator(name = "purchaseOrder_seq_purchaseOrderId", sequenceName = "seq_purchaseOrderId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseOrder_seq_purchaseOrderId")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CLIENT_ID", nullable=false)
	private Client client;
	
	@Column(name="ISSUEDATE", nullable=false)
	private Date issueDate;

	public PurchaseOrder(Client client) {
		if ( client != null ) {
			this.client = client;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected PurchaseOrder() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Override
	public int hashCode() {
		if ( this.getId() != null ) {
			return this.getId().hashCode();
		}
		else {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if ( ( other != null ) && ( other instanceof PurchaseOrder ) ) {
			return ( other == this ) || this.getId().equals(((PurchaseOrder) other).getId());
		}
		else {
			return false;
		}
	}
}
