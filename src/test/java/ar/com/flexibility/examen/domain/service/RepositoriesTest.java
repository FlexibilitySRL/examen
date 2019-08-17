package ar.com.flexibility.examen.domain.service;

import java.math.BigDecimal;
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
import ar.com.flexibility.examen.domain.model.LegalClient;
import ar.com.flexibility.examen.domain.model.NaturalClient;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.model.PurchaseOrderLine;
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
		productA.setUnitPrice(new BigDecimal(10));
		
		productA = this.productRepository.save(productA);
		
		Product productB = new Product();
		
		productB.setName("Product B");
		productB.setDescription("Product B");
		productB.setUnitPrice(new BigDecimal(11));
		
		productB = this.productRepository.save(productB);
		
		Product productC = new Product();
		
		productC.setName("Product C");
		productC.setDescription("Product C description");
		productC.setUnitPrice(new BigDecimal(12));
		
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
				"",
				"",
				"CABA",
				"Argentina"
		) );
		
		santiagoFreireClient.setCommercialAddress(new Address(
				"Av Santa Fe",
				1456,
				"",
				"",
				"",
				"CABA",
				"Argentina"
		) );
		
		santiagoFreireClient = this.naturalClientRepository.save(santiagoFreireClient);
		
		NaturalClient lucasMonteroClient = new NaturalClient();
		
		lucasMonteroClient.setName("Lucas");
		lucasMonteroClient.setSurname("Montero");
		lucasMonteroClient.setCuit(39265124);
		lucasMonteroClient.setDni(20392651249l);
		
		calendar.set(1993, 6, 18);
		santiagoFreireClient.setBirthday(calendar.getTime());
		
		lucasMonteroClient.setResidentialAddress( new Address(
				"Av Maipú",
				3500,
				"",
				"Olivos",
				"Vicente López",
				"Provincia de Buenos Aires",
				"Argentina"
		) );
		
		lucasMonteroClient.setCommercialAddress( new Address(
				"Av Maipú",
				3500,
				"",
				"Olivos",
				"Vicente López",
				"Provincia de Buenos Aires",
				"Argentina"
		) );
		
		lucasMonteroClient = this.naturalClientRepository.save(lucasMonteroClient);
		
		LegalClient esferixisSolutionsClient = new LegalClient();
		
		esferixisSolutionsClient.setName("Esferixis solutions");
		esferixisSolutionsClient.setCuit(30802456781l);
		esferixisSolutionsClient.setCommercialAddress( new Address(
				"Av Maipú",
				1500,
				"",
				"Florida",
				"Vicente López",
				"Provincia de Buenos Aires",
				"Argentina"
		) );
		
		esferixisSolutionsClient = this.legalClientRepository.save(esferixisSolutionsClient);
		
		LegalClient jupiterClient = new LegalClient();
		jupiterClient.setName("Jupiter");
		jupiterClient.setCuit(30302652431l);

		jupiterClient.setCommercialAddress( new Address(
				"Av San Martín",
				6253,
				"",
				"",
				"",
				"CABA",
				"Argentina"
		) );
		
		jupiterClient = this.legalClientRepository.save(jupiterClient);
		
		PurchaseOrder santiagoFreireOrder1 = new PurchaseOrder(santiagoFreireClient);
		calendar.set(2017, 6, 10);
		santiagoFreireOrder1.setIssueDate(calendar.getTime());
		
		santiagoFreireOrder1 = this.purchaseOrderRepository.save(santiagoFreireOrder1);
		
		PurchaseOrderLine santiagoFreireOrder1Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productA, 3, new BigDecimal(10)));
		PurchaseOrderLine santiagoFreireOrder1Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productB, 4, new BigDecimal(11)));
		PurchaseOrderLine santiagoFreireOrder1Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productC, 5, new BigDecimal(12)));
		
		PurchaseOrder santiagoFreireOrder2 = new PurchaseOrder(santiagoFreireClient);
		calendar.set(2018, 8, 15);
		santiagoFreireOrder2.setIssueDate(calendar.getTime());
		
		santiagoFreireOrder2 = this.purchaseOrderRepository.save(santiagoFreireOrder2);
		
		PurchaseOrderLine santiagoFreireOrder2Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder2, productB, 4, new BigDecimal(11)));
		PurchaseOrderLine santiagoFreireOrder2Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder2, productC, 5, new BigDecimal(12)));
	}
}
