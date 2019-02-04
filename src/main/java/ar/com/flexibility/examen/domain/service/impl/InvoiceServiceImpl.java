package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.InvoiceService;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.InvoiceService#findById(java.lang.Long)
	 */
	@Override
	public Invoice findById(Long idInvoice) {
		
		return invoiceRepository.findOne(idInvoice);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.InvoiceService#updateStatus(ar.com.flexibility.examen.domain.model.Invoice)
	 */
	@Override
	public Invoice updateStatus(Invoice invoice) {
		
		// TODO Not implemented yet
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.flexibility.examen.domain.service.InvoiceService#findByCustomer(java.lang.Long)
	 */
	@Override
	public List<Invoice> findByCustomer(Long idCustomer) {
		
		return invoiceRepository.findAllByCustomerId(idCustomer);
	}

}
