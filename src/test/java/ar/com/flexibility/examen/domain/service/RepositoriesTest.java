package ar.com.flexibility.examen.domain.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.NaturalClient;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repositories.LegalClientRepository;
import ar.com.flexibility.examen.domain.repositories.NaturalClientRepository;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseTransactionRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
public class RepositoriesTest {
	@Autowired
	LegalClientRepository legalClientRepository;
	
	@Autowired
	NaturalClientRepository naturalClientRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseOrderLineRepository purchaseOrderLineRepository;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	PurchaseTransactionRepository purchaseTransactionRepository;
	
	@Before
	public void setUp() throws Exception {
		Calendar calendar = new GregorianCalendar();
		
		Product productA = new Product();
		
		productA.setName("Product A");
		productA.setDescription("Product A description");
		
		productA = this.productRepository.save(productA);
		
		Product productB = new Product();
		
		productB.setName("Product B");
		productB.setDescription("Product B");
		
		productB = this.productRepository.save(productB);
		
		Product productC = new Product();
		
		productC.setName("Product C");
		productC.setDescription("Product C description");
		
		productC = this.productRepository.save(productC);
		
		NaturalClient santiagoFreireClient = new NaturalClient();
		
		santiagoFreireClient.setName("Santiago");
		santiagoFreireClient.setSurname("Freire");
		santiagoFreireClient.setCuit(39268227);
		santiagoFreireClient.setDni(20392682279l);
		
		calendar.set(1990, 7, 15);
		santiagoFreireClient.setBirthday(calendar.getTime());
		
		santiagoFreireClient.setResidentialAddress( new Address(
				"Av Santa Fe",
				1456,
				"",
				"CABA",
				"",
				"",
				"Argentina"
		) );
		
		santiagoFreireClient.setCommercialAddress(new Address(
				"Av Santa Fe",
				1456,
				"",
				"CABA",
				"",
				"",
				"Argentina"
		) );
		
		santiagoFreireClient = this.naturalClientRepository.save(santiagoFreireClient);
	}
}
