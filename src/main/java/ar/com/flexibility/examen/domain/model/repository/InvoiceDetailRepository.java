package ar.com.flexibility.examen.domain.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.db.Invoice;
import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

	/**
	 * Metodo que permite consultar el detalle de las facturas.
	 * 
	 * @param invoice
	 * @return
	 */
	List<InvoiceDetail> findByInvoice(Invoice invoice);

}
