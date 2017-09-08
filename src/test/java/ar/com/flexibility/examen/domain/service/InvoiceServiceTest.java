package ar.com.flexibility.examen.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Invoice;
import ar.com.flexibility.examen.domain.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.impl.InvoiceServiceImpl;

/**
 * 
 * @author hackma
 * @version 0.1
 * Test para prueba unitaria de la clase @see InvoiceServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

	@InjectMocks
	private InvoiceServiceImpl invoiceServiceImpl;

	@Mock
	private InvoiceRepository invoiceRepository;

	@Test
	public void createInvoiceTest() {
		Invoice invoiceDetail = new Invoice();
		invoiceServiceImpl.createInvoice(invoiceDetail);
		when(invoiceServiceImpl.createInvoice(invoiceDetail)).thenReturn(invoiceDetail);
		verify(invoiceRepository).save(invoiceDetail);
	}
	
}
