package ar.com.flexibility.examen.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;

public class ExistentPurchaseOrderDTO {
	
	@JsonProperty
	private long clientId;
	
	@JsonProperty
	private Date issueDate;
	
	@JsonProperty
	private List<ExistentPurchaseOrderLineDTO> lines;
	
	/**
	 * @post Crea un DTO de órden de compra existente con el id de cliente,
	 * 		 la fecha de elaboración,
	 * 		 y las líneas
	 */
	public ExistentPurchaseOrderDTO(long clientId, Date issueDate, List<ExistentPurchaseOrderLineDTO> lines) {
		this.clientId = clientId;
		
		if ( this.issueDate != null )
			this.issueDate = issueDate;
		else
			throw new NullPointerException();
		
		this.lines = new ArrayList<ExistentPurchaseOrderLineDTO>(lines);
	}
		
	public long getClientId() {
		return this.clientId;
	}
	
	public Date getIssueDate() {
		return this.issueDate;
	}
	
	public List<ExistentPurchaseOrderLineDTO> getLines() {
		return Collections.unmodifiableList(this.lines);
	}
}
