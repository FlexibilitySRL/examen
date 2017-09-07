package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	@Override
	public Invoice createInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

}
