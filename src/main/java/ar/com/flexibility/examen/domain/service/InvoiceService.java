package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Invoice;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface InvoiceService {
	
	/**
	 * Returns a Invoice by ID
	 * 
	 * @param idInvoice
	 * @return Invoice
	 */
	public Invoice findById(Long idInvoice);
	
	/**
	 * Update status of invoice
	 * 
	 * @param invoice
	 * @return Invoice
	 */
	public Invoice updateStatus(Invoice invoice);
	
	/**
	 * Returns all of invoices of a customer
	 * 
	 * @param idCustomer
	 * @return List<Invoice> 
	 */
	public List<Invoice> findByCustomer(Long idCustomer);
}
