package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="PURCHASEORDERLINES")
@Immutable
public class PurchaseOrderLine {
	@Id
	@Column(name="PURCHASEORDERLINE_ID")
	@SequenceGenerator(name = "purchaseOrderLine_seq_purchaseOrderLineId", sequenceName = "seq_purchaseOrderLineId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseOrderLine_seq_purchaseOrderLineId")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PURCHASORDER_ID", nullable=false)
	private PurchaseOrder purchaseOrder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable=false)
	private Product product;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public PurchaseOrderLine(PurchaseOrder purchaseOrder, Product product, int quantity) {
		if ( ( purchaseOrder != null ) && ( product != null ) ) {
			this.purchaseOrder = purchaseOrder;
			this.product = product;
			this.quantity = quantity;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected PurchaseOrderLine() {
		
	}

	public Long getId() {
		return id;
	}
	
	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
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
		if ( ( other != null ) && ( other instanceof PurchaseOrderLine ) ) {
			return ( other == this ) || this.getId().equals(((PurchaseOrderLine) other).getId());
		}
		else {
			return false;
		}
	}
}
