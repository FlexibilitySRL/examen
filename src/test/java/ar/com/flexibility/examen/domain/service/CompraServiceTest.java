package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.flexibility.examen.domain.model.db.Client;
import ar.com.flexibility.examen.domain.model.db.Invoice;
import ar.com.flexibility.examen.domain.model.db.InvoiceDetail;
import ar.com.flexibility.examen.domain.model.db.Product;
import ar.com.flexibility.examen.domain.model.repository.ClientRepository;
import ar.com.flexibility.examen.domain.model.repository.InvoiceDetailRepository;
import ar.com.flexibility.examen.domain.model.repository.InvoiceRepository;
import ar.com.flexibility.examen.domain.service.impl.InvoiceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CompraServiceTest {

	@Mock
	@Autowired
	ClientRepository clientRepository;

	@Mock
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Mock
	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;

	@Mock
	@Autowired
	private ClientService clientService;

	@Mock
	@Autowired
	private ProductService productService;

	@InjectMocks
	private InvoiceServiceImpl invoiceServiceImpl;

	@Test
	public void getInvoiceById() {
		Optional<Invoice> invoiceOld = Optional.of(Invoice.builder().id(Long.valueOf(1))
				.subtotal(new BigDecimal("10000")).total(new BigDecimal("20000")).created(LocalDateTime.now())
				.state(true).client(Client.builder().id(Long.valueOf(1)).build()).build());
		when(invoiceRepository.findById(Long.valueOf(1))).thenReturn(invoiceOld);
		Invoice invoice = invoiceServiceImpl.getInvoiceById(Long.valueOf("1"));
		assertNotNull(invoice);
	}

	@Test
	public void getAllInvoice() {
		Optional<Invoice> invoiceOld = Optional.of(Invoice.builder().id(Long.valueOf(1))
				.subtotal(new BigDecimal("10000")).total(new BigDecimal("20000")).created(LocalDateTime.now())
				.state(true).client(Client.builder().id(Long.valueOf(1)).build()).build());
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices.add(invoiceOld.get());
		when(invoiceRepository.findAll()).thenReturn(invoices);
		List<Invoice> getInvoices = invoiceServiceImpl.getAllInvoice();
		assertNotNull(getInvoices);
	}

	@Test
	public void createBuy() {
		Client client = Client.builder().id(Long.valueOf(1)).build();

		Optional<Product> product = Optional.of(Product.builder().name("Producto 1").decription("Producto 1")
				.cost(new BigDecimal("10000")).salePrice(new BigDecimal("10000")).quantity(Long.valueOf(10))
				.provider("Nutresa").id(Long.valueOf("1")).build());

		InvoiceDetail invoiceDetail = InvoiceDetail.builder().quantity(Long.valueOf(10)).value(new BigDecimal("50000"))
				.product(product.get()).build();

		List<InvoiceDetail> listInvoiceDetail = new ArrayList<InvoiceDetail>();
		listInvoiceDetail.add(invoiceDetail);

		Optional<Invoice> invoiceToCreate = Optional.of(Invoice.builder().id(Long.valueOf(1))
				.subtotal(new BigDecimal("10000")).total(new BigDecimal("20000")).created(LocalDateTime.now())
				.state(true).client(client).invoiceDetail(listInvoiceDetail).build());

		when(invoiceRepository.saveAndFlush(Mockito.any(Invoice.class))).thenReturn(invoiceToCreate.get());

		assertNotNull(invoiceServiceImpl.createBuy(invoiceToCreate.get()));
	}
}
