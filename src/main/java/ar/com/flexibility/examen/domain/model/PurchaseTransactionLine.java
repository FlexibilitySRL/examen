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

@Entity
@Table(name="PURCHASETRANSACTIONLINES")
public class PurchaseTransactionLine {
	@Id
	@Column(name="PURCHASETRANSACTIONLINE_ID")
	@SequenceGenerator(name = "purchaseTransactionLine_seq_purchaseTransactionLineId", sequenceName = "seq_purchaseTransactionLineId", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseTransactionLine_seq_purchaseTransactionLineId")
	private Long purchaseTransactionLineId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="purchaseTransaction_id", nullable=false)
	private PurchaseTransaction purchaseTransaction;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable=false)
	private Product product;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	public PurchaseTransactionLine(PurchaseTransaction purchaseTransaction) {
		if ( purchaseTransaction != null ) {
			this.purchaseTransaction = purchaseTransaction;
		}
		else {
			throw new NullPointerException();
		}
	}
	
	protected PurchaseTransactionLine() {
		
	}

	public Long getPurchaseTransactionLineId() {
		return purchaseTransactionLineId;
	}
	
	public PurchaseTransaction getPurchaseTransaction() {
		return this.purchaseTransaction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if ( product != null ) {
			this.product = product;
		}
		else {
			throw new NullPointerException();
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if ( quantity > 0 ) {
			this.quantity = quantity;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public int hashCode() {
		if ( this.getPurchaseTransactionLineId() != null ) {
			return this.getPurchaseTransactionLineId().hashCode();
		}
		else {
			return super.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if ( ( other != null ) && ( other instanceof PurchaseTransactionLine ) ) {
			return this.getPurchaseTransactionLineId().equals(((PurchaseTransactionLine) other).getPurchaseTransactionLineId());
		}
		else {
			return false;
		}
	}
}
