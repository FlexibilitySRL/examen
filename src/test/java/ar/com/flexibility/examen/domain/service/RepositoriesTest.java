package ar.com.flexibility.examen.domain.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
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
import ar.com.flexibility.examen.domain.model.PurchaseTransaction;
import ar.com.flexibility.examen.domain.repositories.LegalClientRepository;
import ar.com.flexibility.examen.domain.repositories.NaturalClientRepository;
import ar.com.flexibility.examen.domain.repositories.ProductRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderLineRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.repositories.PurchaseTransactionRepository;
import org.junit.Assert;

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
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private long productA_id;
	private long productB_id;
	private long productC_id;
	private long santiagoFreireClient_id;
	private long lucasMonteroClient_id;
	private long esferixisSolutionsClient_id;
	private long jupiterClient_id;
	private long santiagoFreireOrder1_id;
	private long santiagoFreireOrder1Line1_id;
	private long santiagoFreireOrder1Line2_id;
	private long santiagoFreireOrder1Line3_id;
	private long santiagoFreireOrder2_id;
	private long santiagoFreireOrder2Line1_id;
	private long santiagoFreireOrder2Line2_id;
	private long santiagoFreireOrder2Transaction_id;
	private long santiagoFreireOrder3_id;
	private long santiagoFreireOrder3Line1_id;
	private long santiagoFreireOrder3Line2_id;
	private long santiagoFreireOrder3Transaction_id;
	private long lucasMonteroOrder1_id;
	private long lucasMonteroOrder1Line1_id;
	private long lucasMonteroOrder1Line2_id;
	private long lucasMonteroOrder1Transaction_id;
	private long lucasMonteroOrder2_id;
	private long lucasMonteroOrder2Line1_id;
	private long lucasMonteroOrder2Line2_id;
	private long lucasMonteroOrder3_id;
	private long lucasMonteroOrder3Line1_id;
	private long lucasMonteroOrder3Line2_id;
	private long esferixisSolutionsOrder1_id;
	private long esferixisSolutionsOrder1Line1_id;
	private long esferixisSolutionsOrder1Line2_id;
	private long esferixisSolutionsOrder1Line3_id;
	private long esferixisSolutionsOrder2_id;
	private long esferixisSolutionsOrder2Line1_id;
	private long esferixisSolutionsOrder2Line2_id;
	private long esferixisSolutionsOrder2Line3_id;
	private long esferixisSolutionsOrder2Transaction_id;
	private long esferixisSolutionsOrder3_id;
	private long esferixisSolutionsOrder3Line1_id;
	private long esferixisSolutionsOrder3Line2_id;
	private long esferixisSolutionsOrder3Line3_id;
	private long jupiterOrder1_id;
	private long jupiterOrder1Line1_id;
	private long jupiterOrder1Line2_id;
	private long jupiterOrder1Line3_id;
	private long jupiterOrder2_id;
	private long jupiterOrder2Line1_id;
	private long jupiterOrder2Line2_id;
	private long jupiterOrder2Line3_id;
	private long jupiterOrder3_id;
	private long jupiterOrder3Line1_id;
	private long jupiterOrder3Line2_id;
	private long jupiterOrder3Line3_id;
	private long jupiterOrder3Transaction_id;
	
	@Before
	public void setUp() throws Exception {
		Calendar calendar = new GregorianCalendar();
		
		Product productA = new Product();
		
		productA.setName("Product A");
		productA.setDescription("Product A description");
		productA.setUnitPrice(new BigDecimal(10));
		
		productA = this.productRepository.save(productA);
		this.productA_id = productA.getProductId();
		
		Product productB = new Product();
		
		productB.setName("Product B");
		productB.setDescription("Product B");
		productB.setUnitPrice(new BigDecimal(11));
		
		productB = this.productRepository.save(productB);
		this.productB_id = productB.getProductId();
		
		Product productC = new Product();
		
		productC.setName("Product C");
		productC.setDescription("Product C description");
		productC.setUnitPrice(new BigDecimal(12));
		
		productC = this.productRepository.save(productC);
		this.productC_id = productC.getProductId();
		
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
		this.santiagoFreireClient_id = santiagoFreireClient.getId();
		
		NaturalClient lucasMonteroClient = new NaturalClient();
		
		lucasMonteroClient.setName("Lucas");
		lucasMonteroClient.setSurname("Montero");
		lucasMonteroClient.setCuit(39265124);
		lucasMonteroClient.setDni(20392651249l);
		
		calendar.set(1991, 7, 18);
		lucasMonteroClient.setBirthday(calendar.getTime());
		
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
		this.lucasMonteroClient_id = lucasMonteroClient.getId();
		
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
		this.esferixisSolutionsClient_id = esferixisSolutionsClient.getId();
		
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
		this.jupiterClient_id = jupiterClient.getId();
		
		calendar.set(2017, 6, 10);
		PurchaseOrder santiagoFreireOrder1 = new PurchaseOrder(santiagoFreireClient, calendar.getTime());
		
		santiagoFreireOrder1 = this.purchaseOrderRepository.save(santiagoFreireOrder1);
		this.santiagoFreireOrder1_id = santiagoFreireOrder1.getId();
		
		PurchaseOrderLine santiagoFreireOrder1Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productA, 3, new BigDecimal(10)));
		this.santiagoFreireOrder1Line1_id = santiagoFreireOrder1Line1.getId();
		PurchaseOrderLine santiagoFreireOrder1Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productB, 4, new BigDecimal(11)));
		this.santiagoFreireOrder1Line2_id = santiagoFreireOrder1Line2.getId();
		PurchaseOrderLine santiagoFreireOrder1Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder1, productC, 5, new BigDecimal(12)));
		this.santiagoFreireOrder1Line3_id = santiagoFreireOrder1Line3.getId();
		
		calendar.set(2018, 8, 15);
		PurchaseOrder santiagoFreireOrder2 = new PurchaseOrder(santiagoFreireClient, calendar.getTime());
		
		santiagoFreireOrder2 = this.purchaseOrderRepository.save(santiagoFreireOrder2);
		this.santiagoFreireOrder2_id = santiagoFreireOrder2.getId();
		
		PurchaseOrderLine santiagoFreireOrder2Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder2, productB, 6, new BigDecimal(11)));
		this.santiagoFreireOrder2Line1_id = santiagoFreireOrder2Line1.getId();
		PurchaseOrderLine santiagoFreireOrder2Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder2, productC, 3, new BigDecimal(12)));
		this.santiagoFreireOrder2Line2_id = santiagoFreireOrder2Line2.getId();
		
		calendar.set(2018, 8, 17);
		PurchaseTransaction santiagoFreireOrder2Transaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(santiagoFreireOrder2, calendar.getTime()));
		this.santiagoFreireOrder2Transaction_id = santiagoFreireOrder2Transaction.getId();
		
		calendar.set(2018, 8, 19);
		PurchaseOrder santiagoFreireOrder3 = new PurchaseOrder(santiagoFreireClient, calendar.getTime());
		
		santiagoFreireOrder3 = this.purchaseOrderRepository.save(santiagoFreireOrder3);
		this.santiagoFreireOrder3_id = santiagoFreireOrder3.getId();
		
		PurchaseOrderLine santiagoFreireOrder3Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder3, productA, 1, new BigDecimal(10)));
		this.santiagoFreireOrder3Line1_id = santiagoFreireOrder3Line1.getId();
		PurchaseOrderLine santiagoFreireOrder3Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(santiagoFreireOrder3, productB, 3, new BigDecimal(11)));
		this.santiagoFreireOrder3Line2_id = santiagoFreireOrder3Line2.getId();
		
		calendar.set(2018, 8, 22);
		PurchaseTransaction santiagoFreireOrder3Transaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(santiagoFreireOrder3, calendar.getTime()));
		this.santiagoFreireOrder3Transaction_id = santiagoFreireOrder3Transaction.getId();
		
		calendar.set(2018, 10, 13);
		PurchaseOrder lucasMonteroOrder1 = new PurchaseOrder(lucasMonteroClient, calendar.getTime());
		
		lucasMonteroOrder1 = this.purchaseOrderRepository.save(lucasMonteroOrder1);
		this.lucasMonteroOrder1_id = lucasMonteroOrder1.getId();
		
		PurchaseOrderLine lucasMonteroOrder1Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder1, productC, 8, new BigDecimal(12)));
		this.lucasMonteroOrder1Line1_id = lucasMonteroOrder1Line1.getId();
		PurchaseOrderLine lucasMonteroOrder1Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder1, productA, 9, new BigDecimal(10)));
		this.lucasMonteroOrder1Line2_id = lucasMonteroOrder1Line2.getId();
		
		calendar.set(2018, 10, 15);
		PurchaseTransaction lucasMonteroOrder1Transaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(lucasMonteroOrder1, calendar.getTime()));
		this.lucasMonteroOrder1Transaction_id = lucasMonteroOrder1Transaction.getId();
		
		calendar.set(2018, 11, 8);
		PurchaseOrder lucasMonteroOrder2 = new PurchaseOrder(lucasMonteroClient, calendar.getTime());
		
		lucasMonteroOrder2 = this.purchaseOrderRepository.save(lucasMonteroOrder2);
		this.lucasMonteroOrder2_id = lucasMonteroOrder2.getId();
		
		PurchaseOrderLine lucasMonteroOrder2Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder2, productC, 2, new BigDecimal(12)));
		this.lucasMonteroOrder2Line1_id = lucasMonteroOrder2Line1.getId();
		PurchaseOrderLine lucasMonteroOrder2Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder2, productB, 7, new BigDecimal(11)));
		this.lucasMonteroOrder2Line2_id = lucasMonteroOrder2Line2.getId();
		
		calendar.set(2018, 11, 13);
		PurchaseOrder lucasMonteroOrder3 = new PurchaseOrder(lucasMonteroClient, calendar.getTime());
		
		lucasMonteroOrder3 = this.purchaseOrderRepository.save(lucasMonteroOrder3);
		this.lucasMonteroOrder3_id = lucasMonteroOrder3.getId();
		
		PurchaseOrderLine lucasMonteroOrder3Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder3, productC, 2, new BigDecimal(12)));
		this.lucasMonteroOrder3Line1_id = lucasMonteroOrder3Line1.getId();
		PurchaseOrderLine lucasMonteroOrder3Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(lucasMonteroOrder3, productA, 3, new BigDecimal(10)));
		this.lucasMonteroOrder3Line2_id = lucasMonteroOrder3Line2.getId();
		
		calendar.set(2019, 2, 18);
		PurchaseOrder esferixisSolutionsOrder1 = new PurchaseOrder(esferixisSolutionsClient, calendar.getTime());
		
		esferixisSolutionsOrder1 = this.purchaseOrderRepository.save(esferixisSolutionsOrder1);
		this.esferixisSolutionsOrder1_id = esferixisSolutionsOrder1.getId();
		
		PurchaseOrderLine esferixisSolutionsOrder1Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder1, productC, 3, new BigDecimal(12)));
		this.esferixisSolutionsOrder1Line1_id = esferixisSolutionsOrder1Line1.getId();
		PurchaseOrderLine esferixisSolutionsOrder1Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder1, productA, 5, new BigDecimal(10)));
		this.esferixisSolutionsOrder1Line2_id = esferixisSolutionsOrder1Line2.getId();
		PurchaseOrderLine esferixisSolutionsOrder1Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder1, productB, 13, new BigDecimal(11)));
		this.esferixisSolutionsOrder1Line3_id = esferixisSolutionsOrder1Line3.getId();
		
		calendar.set(2019, 3, 15);
		PurchaseOrder esferixisSolutionsOrder2 = new PurchaseOrder(esferixisSolutionsClient, calendar.getTime());
		
		esferixisSolutionsOrder2 = this.purchaseOrderRepository.save(esferixisSolutionsOrder2);
		this.esferixisSolutionsOrder2_id = esferixisSolutionsOrder2.getId();
		
		PurchaseOrderLine esferixisSolutionsOrder2Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder2, productA, 3, new BigDecimal(10)));
		this.esferixisSolutionsOrder2Line1_id = esferixisSolutionsOrder2Line1.getId();
		PurchaseOrderLine esferixisSolutionsOrder2Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder2, productC, 5, new BigDecimal(12)));
		this.esferixisSolutionsOrder2Line2_id = esferixisSolutionsOrder2Line2.getId();
		PurchaseOrderLine esferixisSolutionsOrder2Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder2, productB, 8, new BigDecimal(11)));
		this.esferixisSolutionsOrder2Line3_id = esferixisSolutionsOrder2Line3.getId();
		
		calendar.set(2019, 3, 18);
		PurchaseTransaction esferixisSolutionsOrder2Transaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(esferixisSolutionsOrder2, calendar.getTime()));
		this.esferixisSolutionsOrder2Transaction_id = esferixisSolutionsOrder2Transaction.getId();
		
		calendar.set(2019, 3, 20);
		PurchaseOrder esferixisSolutionsOrder3 = new PurchaseOrder(esferixisSolutionsClient, calendar.getTime());
		
		esferixisSolutionsOrder3 = this.purchaseOrderRepository.save(esferixisSolutionsOrder3);
		this.esferixisSolutionsOrder3_id = esferixisSolutionsOrder3.getId();
		
		PurchaseOrderLine esferixisSolutionsOrder3Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder3, productA, 3, new BigDecimal(10)));
		this.esferixisSolutionsOrder3Line1_id = esferixisSolutionsOrder3Line1.getId();
		PurchaseOrderLine esferixisSolutionsOrder3Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder3, productB, 7, new BigDecimal(11)));
		this.esferixisSolutionsOrder3Line2_id = esferixisSolutionsOrder3Line2.getId();
		PurchaseOrderLine esferixisSolutionsOrder3Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(esferixisSolutionsOrder3, productC, 13, new BigDecimal(12)));
		this.esferixisSolutionsOrder3Line3_id = esferixisSolutionsOrder3Line3.getId();
		
		calendar.set(2019, 4, 15);
		PurchaseOrder jupiterOrder1 = new PurchaseOrder(jupiterClient, calendar.getTime());
		
		jupiterOrder1 = this.purchaseOrderRepository.save(jupiterOrder1);
		this.jupiterOrder1_id = jupiterOrder1.getId();
		
		PurchaseOrderLine jupiterOrder1Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder1, productC, 3, new BigDecimal(12)));
		this.jupiterOrder1Line1_id = jupiterOrder1Line1.getId();
		PurchaseOrderLine jupiterOrder1Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder1, productA, 5, new BigDecimal(10)));
		this.jupiterOrder1Line2_id = jupiterOrder1Line2.getId();
		PurchaseOrderLine jupiterOrder1Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder1, productB, 13, new BigDecimal(11)));
		this.jupiterOrder1Line3_id = jupiterOrder1Line3.getId();
		
		calendar.set(2019, 4, 18);
		PurchaseOrder jupiterOrder2 = new PurchaseOrder(jupiterClient, calendar.getTime());
		
		jupiterOrder2 = this.purchaseOrderRepository.save(jupiterOrder2);
		this.jupiterOrder2_id = jupiterOrder2.getId();
		
		PurchaseOrderLine jupiterOrder2Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder2, productC, 6, new BigDecimal(10)));
		this.jupiterOrder2Line1_id = jupiterOrder2Line1.getId();
		PurchaseOrderLine jupiterOrder2Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder2, productB, 7, new BigDecimal(12)));
		this.jupiterOrder2Line2_id = jupiterOrder2Line2.getId();
		PurchaseOrderLine jupiterOrder2Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder2, productA, 4, new BigDecimal(11)));
		this.jupiterOrder2Line3_id = jupiterOrder2Line3.getId();
		
		calendar.set(2019, 4, 21);
		PurchaseOrder jupiterOrder3 = new PurchaseOrder(jupiterClient, calendar.getTime());
		
		jupiterOrder3 = this.purchaseOrderRepository.save(jupiterOrder3);
		this.jupiterOrder3_id = jupiterOrder3.getId();
		
		PurchaseOrderLine jupiterOrder3Line1 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder3, productC, 5, new BigDecimal(12)));
		this.jupiterOrder3Line1_id = jupiterOrder3Line1.getId();
		PurchaseOrderLine jupiterOrder3Line2 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder3, productA, 2, new BigDecimal(10)));
		this.jupiterOrder3Line2_id = jupiterOrder3Line2.getId();
		PurchaseOrderLine jupiterOrder3Line3 = this.purchaseOrderLineRepository.save(new PurchaseOrderLine(jupiterOrder3, productB, 1, new BigDecimal(11)));
		this.jupiterOrder3Line3_id = jupiterOrder3Line3.getId();
		
		calendar.set(2019, 4, 23);
		PurchaseTransaction jupiterOrder3Transaction = this.purchaseTransactionRepository.save(new PurchaseTransaction(jupiterOrder3, calendar.getTime()));
		this.jupiterOrder3Transaction_id = jupiterOrder3Transaction.getId();
		
		this.entityManager.flush();
	}
	
	@Test
	public void testSantiagoFreireOrder1_SantiagoFreireClientRelation() {
		PurchaseOrder santiagoFreireOrder1 = this.purchaseOrderRepository.findOne(this.santiagoFreireOrder1_id);
		
		Assert.assertEquals(this.santiagoFreireClient_id, (long) santiagoFreireOrder1.getClient().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder1TransactionDoesNotExist() {
		Assert.assertNull(this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.santiagoFreireOrder1_id) ) );
	}
	
	@Test
	public void testSantiagoFreireOrder1Line1_SantiagoFreireOrder1Relation() {
		Assert.assertEquals(this.santiagoFreireOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder1Line2_SantiagoFreireOrder1Relation() {
		Assert.assertEquals(this.santiagoFreireOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder1Line3_SantiagoFreireOrder1Relation() {
		Assert.assertEquals(this.santiagoFreireOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder1Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line1_id),
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line2_id),
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder1Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.santiagoFreireOrder1_id)
				)
			)
		);
	}
	
	@Test
	public void testSantiagoFreireOrder2_SantiagoFreireClientRelation() {
		PurchaseOrder santiagoFreireOrder2 = this.purchaseOrderRepository.findOne(this.santiagoFreireOrder2_id);
		
		Assert.assertEquals(this.santiagoFreireClient_id, (long) santiagoFreireOrder2.getClient().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder2_SantiagoFreireOrder2Transaction_Relation() {
		Assert.assertEquals(this.santiagoFreireOrder2Transaction_id, (long) this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.santiagoFreireOrder2_id) ).getId() );
	}
	
	@Test
	public void testSantiagoFreireOrder2Line1_SantiagoFreireOrder1Relation() {
		Assert.assertEquals(this.santiagoFreireOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder2Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder2Line2_SantiagoFreireOrder1Relation() {
		Assert.assertEquals(this.santiagoFreireOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder2Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder2Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder2Line1_id),
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder2Line2_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.santiagoFreireOrder2_id)
				)
			)
		);
	}
	
	@Test
	public void testSantiagoFreireOrder3_SantiagoFreireClientRelation() {
		PurchaseOrder santiagoFreireOrder3 = this.purchaseOrderRepository.findOne(this.santiagoFreireOrder3_id);
		
		Assert.assertEquals(this.santiagoFreireClient_id, (long) santiagoFreireOrder3.getClient().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder2_SantiagoFreireOrder3Transaction_Relation() {
		Assert.assertEquals(this.santiagoFreireOrder3Transaction_id, (long) this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.santiagoFreireOrder3_id) ).getId() );
	}
	
	@Test
	public void testSantiagoFreireOrder3Line1_SantiagoFreireOrder3Relation() {
		Assert.assertEquals(this.santiagoFreireOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder3Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder3Line2_SantiagoFreireOrder3Relation() {
		Assert.assertEquals(this.santiagoFreireOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder3Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testSantiagoFreireOrder3Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder3Line1_id),
				this.purchaseOrderLineRepository.findOne(this.santiagoFreireOrder3Line2_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.santiagoFreireOrder3_id)
				)
			)
		);
	}
	
	@Test
	public void testLucasMonteroOrder1_LucasMonteroClientRelation() {
		PurchaseOrder lucasMonteroOrder1 = this.purchaseOrderRepository.findOne(this.lucasMonteroOrder1_id);
		
		Assert.assertEquals(this.lucasMonteroClient_id, (long) lucasMonteroOrder1.getClient().getId());
	}
	
	@Test
	public void testLucasMonteroOrder1_LucasMonteroOrder1Transaction_Relation() {
		Assert.assertEquals(this.lucasMonteroOrder1Transaction_id, (long) this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.lucasMonteroOrder1_id) ).getId() );
	}
	
	@Test
	public void testLucasMonteroOrder1Line1_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder1Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder1Line2_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder1Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder1Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder1Line1_id),
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder1Line2_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.lucasMonteroOrder1_id)
				)
			)
		);
	}
	
	@Test
	public void testLucasMonteroOrder2_LucasMonteroClientRelation() {
		PurchaseOrder lucasMonteroOrder2 = this.purchaseOrderRepository.findOne(this.lucasMonteroOrder2_id);
		
		Assert.assertEquals(this.lucasMonteroClient_id, (long) lucasMonteroOrder2.getClient().getId());
	}
	
	@Test
	public void testLucasMonteroOrder2TransactionDoesNotExist() {
		Assert.assertNull(this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.lucasMonteroOrder2_id) ) );
	}
	
	@Test
	public void testLucasMonteroOrder2Line1_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder2Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder2Line2_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder2Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder2Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder2Line1_id),
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder2Line2_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.lucasMonteroOrder2_id)
				)
			)
		);
	}
	
	@Test
	public void testLucasMonteroOrder3_LucasMonteroClientRelation() {
		PurchaseOrder lucasMonteroOrder3 = this.purchaseOrderRepository.findOne(this.lucasMonteroOrder3_id);
		
		Assert.assertEquals(this.lucasMonteroClient_id, (long) lucasMonteroOrder3.getClient().getId());
	}
	
	@Test
	public void testLucasMonteroOrder3TransactionDoesNotExist() {
		Assert.assertNull(this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.lucasMonteroOrder3_id) ) );
	}
	
	@Test
	public void testLucasMonteroOrder3Line1_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder3Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder3Line2_LucasMonteroOrder1Relation() {
		Assert.assertEquals(this.lucasMonteroOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder3Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testLucasMonteroOrder3Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder3Line1_id),
				this.purchaseOrderLineRepository.findOne(this.lucasMonteroOrder3Line2_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.lucasMonteroOrder3_id)
				)
			)
		);
	}
	
	@Test
	public void testEsferixisSolutionsOrder1_EsferixisSolutionsClientRelation() {
		Assert.assertEquals(this.esferixisSolutionsClient_id, (long) this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder1_id).getClient().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder1TransactionDoesNotExist() {
		Assert.assertNull( this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder1_id) ) );
	}
	
	@Test
	public void testEsferixisSolutionsOrder1Line1_EsferixisSolutionsOrder1Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder1Line2_EsferixisSolutionsOrder1Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder1Line3_EsferixisSolutionsOrder1Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder1Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line1_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line2_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder1Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder1_id)
				)
			)
		);
	}
	
	@Test
	public void testEsferixisSolutionsOrder2_EsferixisSolutionsClientRelation() {
		Assert.assertEquals(this.esferixisSolutionsClient_id, (long) this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder2_id).getClient().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder2_EsferixisSolutionsOrder2Transaction_Relation() {
		Assert.assertEquals( this.esferixisSolutionsOrder2Transaction_id, (long) this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder2_id) ).getId() );
	}
	
	@Test
	public void testEsferixisSolutionsOrder2Line1_EsferixisSolutionsOrder2Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder2Line2_EsferixisSolutionsOrder2Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder2Line3_EsferixisSolutionsOrder2Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder2Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line1_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line2_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder2Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder2_id)
				)
			)
		);
	}
	
	@Test
	public void testEsferixisSolutionsOrder3_EsferixisSolutionsClientRelation() {
		Assert.assertEquals(this.esferixisSolutionsClient_id, (long) this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder3_id).getClient().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder3TransactionDoesNotExist() {
		Assert.assertNull( this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder3_id) ) );
	}
	
	@Test
	public void testEsferixisSolutionsOrder3Line1_EsferixisSolutionsOrder3Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder3Line2_EsferixisSolutionsOrder3Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder3Line3_EsferixisSolutionsOrder3Relation() {
		Assert.assertEquals(this.esferixisSolutionsOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testEsferixisSolutionsOrder3Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line1_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line2_id),
				this.purchaseOrderLineRepository.findOne(this.esferixisSolutionsOrder3Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.esferixisSolutionsOrder3_id)
				)
			)
		);
	}
	
	@Test
	public void testJupiterOrder1_JupiterClientRelation() {
		Assert.assertEquals(this.jupiterClient_id, (long) this.purchaseOrderRepository.findOne(this.jupiterOrder1_id).getClient().getId());
	}
	
	@Test
	public void testJupiterOrder1TransactionDoesNotExist() {
		Assert.assertNull( this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.jupiterOrder1_id) ) );
	}
	
	@Test
	public void testJupiterOrder1Line1_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder1Line2_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder1Line3_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder1_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder1Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line1_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line2_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder1Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.jupiterOrder1_id)
				)
			)
		);
	}
	
	@Test
	public void testJupiterOrder2_JupiterClientRelation() {
		Assert.assertEquals(this.jupiterClient_id, (long) this.purchaseOrderRepository.findOne(this.jupiterOrder2_id).getClient().getId());
	}
	
	@Test
	public void testJupiterOrder2TransactionDoesNotExist() {
		Assert.assertNull( this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.jupiterOrder2_id) ) );
	}
	
	@Test
	public void testJupiterOrder2Line1_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder2Line2_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder2Line3_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder2_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder2Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line1_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line2_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder2Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.jupiterOrder2_id)
				)
			)
		);
	}
	
	@Test
	public void testJupiterOrder3_JupiterClientRelation() {
		Assert.assertEquals(this.jupiterClient_id, (long) this.purchaseOrderRepository.findOne(this.jupiterOrder3_id).getClient().getId());
	}
	
	@Test
	public void testJupiterOrder3_JupiterOrder3Transaction_Relation() {
		Assert.assertEquals( this.jupiterOrder3Transaction_id, (long) this.purchaseTransactionRepository.findByPurchaseOrder( this.purchaseOrderRepository.findOne(this.jupiterOrder3_id) ).getId() );
	}
	
	@Test
	public void testJupiterOrder3Line1_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line1_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder3Line2_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line2_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder3Line3_JupiterOrder1Relation() {
		Assert.assertEquals(this.jupiterOrder3_id, (long) this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line3_id).getPurchaseOrder().getId());
	}
	
	@Test
	public void testJupiterOrder3Lines() {
		Assert.assertEquals(
			new HashSet<PurchaseOrderLine>(Arrays.asList(
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line1_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line2_id),
				this.purchaseOrderLineRepository.findOne(this.jupiterOrder3Line3_id)
			)),
			new HashSet<PurchaseOrderLine>(
				this.purchaseOrderLineRepository.findByPurchaseOrder(
					this.purchaseOrderRepository.findOne(this.jupiterOrder3_id)
				)
			)
		);
	}
}
