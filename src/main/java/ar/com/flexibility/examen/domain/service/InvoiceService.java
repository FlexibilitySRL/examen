package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.db.Invoice;

public interface InvoiceService {

	/**
	 * Metodo que permite obtener la factura por el identificador
	 * 
	 * @param id
	 * @return
	 */
	public Invoice getInvoiceById(Long id);

	/**
	 * Metodo que permite obtener todas las facturas
	 * 
	 * @return
	 */
	public List<Invoice> getAllInvoice();

	/**
	 * Metodo que permite aprobar una compra
	 * 
	 * @param id
	 * @return
	 */
	Invoice approveBuy(Long id);

	/**
	 * Metodo que permite la creacion de la factura de compra
	 * 
	 * @return
	 */
	Invoice createBuy(Invoice invoice);

}
