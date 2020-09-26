package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.db.Invoice;
import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;
import ar.com.flexibility.examen.domain.model.repository.InvoiceDetailRepository;
import ar.com.flexibility.examen.domain.model.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.InvoiceService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.InvoiceException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductService productService;

	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;

	@Override
	public Invoice getInvoiceById(Long id) {
		Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
		if (optionalInvoice.isPresent()) {
			Invoice invoice = optionalInvoice.get();
			invoice.setInvoiceDetail(invoiceDetailRepository.findByInvoice(invoice));
			return invoice;
		}
		throw new InvoiceException("No existe factura");
	}

	@Override
	public List<Invoice> getAllInvoice() {
		List<Invoice> invoices = invoiceRepository.findAll();
		if (invoices == null || invoices.size() == 0) {
			throw new InvoiceException("No se encontraron facturas");
		}

		for (Invoice invoice : invoices) {
			invoice.setInvoiceDetail(invoiceDetailRepository.findByInvoice(invoice));
		}
		return invoices;
	}

	@Override
	public Invoice approveBuy(Long id) {
		Invoice invoice = getInvoiceById(id);
		invoice.setState(true);
		return invoiceRepository.saveAndFlush(invoice);
	}

	@Override
	@Transactional
	public Invoice createBuy(Invoice invoiceToCreate) {
		clientService.getClienteById(invoiceToCreate.getClient().getId());
		invoiceToCreate.setState(false);
		Invoice newInvoice = invoiceRepository.saveAndFlush(invoiceToCreate);
		saveDetailInvoice(invoiceToCreate.getInvoiceDetail(), newInvoice);
		return newInvoice;
	}

	/**
	 * Metodo que permite guardar el detalle de una factura
	 * 
	 * @param invoiceDetails
	 * @param invoice
	 */

	private void saveDetailInvoice(List<InvoiceDetail> invoiceDetails, Invoice invoice) {
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			productService.getProductById(invoiceDetail.getProduct().getId());
			invoiceDetail.setInvoice(invoice);
			invoiceDetailRepository.saveAndFlush(invoiceDetail);
		}
	}

}
