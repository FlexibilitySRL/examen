package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.PurchasesApi;
import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.service.impl.ProcessClientServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ProcessProductServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ProcessPurchasesServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ProcessSellerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPurchasesServiceTest {

	@Autowired
	private ProcessPurchasesServiceImpl purchasesService;

	@Autowired
	private ProcessClientServiceImpl clientService;

	@Autowired
	private ProcessProductServiceImpl productService;

	@Autowired
	private ProcessSellerServiceImpl sellerService;

	private ClientApi clientApi;
	private ProductApi productApi;
	private SellerApi sellerApi;
	private PurchasesApi purchasesApi;

	@Before
	public void init() {
		clientApi = new ClientApi();
		clientApi.setFirstName("Larry");
		clientApi.setLastName("Williams");
		clientApi.setCategory("Gold");

		clientApi = clientService.create(clientApi);

		productApi = new ProductApi();
		productApi.setName("SSD");
		productApi.setType("Hardware");

		productApi = productService.create(productApi);

		sellerApi = new SellerApi();
		sellerApi.setFirstName("Amy");
		sellerApi.setLastName("Throller");

		sellerApi = sellerService.create(sellerApi);

		purchasesApi = new PurchasesApi();
		purchasesApi.setClientApi(clientApi);
		purchasesApi.setProductApi(productApi);
		purchasesApi.setSellerApi(sellerApi);
		purchasesApi.setDescription("SSD hardware sold.");
		purchasesApi.setValue(Long.valueOf(250000));
	}

	@Test
	public void shouldApproveAPurchase() {
		purchasesApi = purchasesService.create(purchasesApi);

		assertNotNull(purchasesApi.getId());
		assertEquals(purchasesApi.getValue(), Long.valueOf(250000));
	}

	@Test
	public void shouldSearchAPurchase() {
		purchasesApi = purchasesService.create(purchasesApi);
		
		List<PurchasesApi> purchasesList = purchasesService.search();
		assertEquals(1, purchasesList.size());
	}

}
