package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.InvoiceDetail;

public interface InvoiceDetailService {

	public InvoiceDetail createInvoice(InvoiceDetail invoice);
	
	public List<InvoiceDetail> findInvoice(InvoiceDetail invoice);
	
}