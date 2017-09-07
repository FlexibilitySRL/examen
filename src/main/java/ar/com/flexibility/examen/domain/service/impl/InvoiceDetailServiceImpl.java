package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.model.InvoiceDetail;
import ar.com.flexibility.examen.domain.repository.InvoiceDetailRepository;
import ar.com.flexibility.examen.domain.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.InvoiceDetailService;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public InvoiceDetail createInvoice(InvoiceDetail invoiceDetail) {
		InvoiceDetail invoiceDetailSaved = null;
		if(invoiceDetail.getInvoice() != null) {
			Invoice invoiceSaved = invoiceRepository.save(invoiceDetail.getInvoice());
			invoiceDetail.setInvoice(invoiceSaved);
			System.out.println("guardado exitoso de factura");
			invoiceDetailSaved = invoiceDetailRepository.save(invoiceDetail);
		}
		return invoiceDetailSaved;
	}

	@Override
	public List<InvoiceDetail> findInvoice(InvoiceDetail invoice) {
		if(invoice.getId() != null) {
			InvoiceDetail invoiceDetailFound = invoiceDetailRepository.findOne(invoice.getId());
			
			List<InvoiceDetail> listInvoice = new ArrayList<>();
			listInvoice.add(invoiceDetailFound);
			return listInvoice;
		}
		return (List<InvoiceDetail>) invoiceDetailRepository.findAll();
	}

}