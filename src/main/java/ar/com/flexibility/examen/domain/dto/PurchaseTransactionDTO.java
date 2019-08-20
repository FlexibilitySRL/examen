package ar.com.flexibility.examen.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.PurchaseTransaction;

public final class PurchaseTransactionDTO {
	@JsonProperty
	private long purchaseOrderId;
	
	@JsonProperty
	private Date approvalDate;
	
	public PurchaseTransactionDTO(PurchaseTransaction purchaseTransaction) {
		if ( purchaseTransaction == null )
			throw new NullPointerException();
		
		this.purchaseOrderId = purchaseTransaction.getPurchaseOrder().getId();
		this.approvalDate = purchaseTransaction.getApprovalDate();
		
	}

	public long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}
	
}
