package ar.com.flexibility.examen.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;

public final class NewPurchaseOrderDTO {
	@JsonProperty
	private long clientId;
	
	@JsonProperty
	private List<NewPurchaseOrderLineDTO> lines;
		
	public long getClientId() {
		return this.clientId;
	}
	
	public List<NewPurchaseOrderLineDTO> getLines() {
		return Collections.unmodifiableList(this.lines);
	}
}
