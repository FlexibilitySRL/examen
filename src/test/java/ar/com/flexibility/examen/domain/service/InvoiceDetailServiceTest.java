package ar.com.flexibility.examen.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.model.InvoiceDetail;
import ar.com.flexibility.examen.domain.repository.InvoiceDetailRepository;
import ar.com.flexibility.examen.domain.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.impl.InvoiceDetailServiceImpl;

/**
 * 
 * @author hackma
 * @version 0.1
 * Test para prueba unitaria de la clase @see InvoiceDetailServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceDetailServiceTest {

	@InjectMocks
	private InvoiceDetailServiceImpl invoiceDetailServiceImpl;
	
	@Mock
	private InvoiceRepository invoiceRepository;
	
	@Mock
	private InvoiceDetailRepository invoiceDetailRepository;

	@Mock
	private InvoiceDetail invoiceDetail;
	
	@Mock
	private Invoice invoice;
	
	@Test
	public void createInvoiceDetailTest() {
		when(invoiceDetail.getInvoice()).thenReturn(invoice);
		invoiceDetailServiceImpl.createInvoice(invoiceDetail);
		when(invoiceDetailServiceImpl.createInvoice(invoiceDetail)).thenReturn(invoiceDetail);
		verify(invoiceDetailRepository).save(invoiceDetail);
	}
	
	@Test
	public void findInvoiceTest() {
		invoiceDetailRepository.findAll();
		when(invoiceDetailServiceImpl.findInvoice(invoiceDetail)).thenReturn(new ArrayList<>());
		verify(invoiceDetailRepository).findAll();
	}
}
