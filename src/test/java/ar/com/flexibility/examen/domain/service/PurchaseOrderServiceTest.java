package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ar.com.flexibility.examen.domain.dto.PurchaseOrderDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.repository.PurchaseOrderRepository;
import ar.com.flexibility.examen.domain.service.impl.PurchaseOrderServiceImpl;

public class PurchaseOrderServiceTest {

	@InjectMocks
	private PurchaseOrderServiceImpl purchaseOrderService;

	@Mock
	private PurchaseOrderRepository purchaseOrderRepository;

	@Test
	public void whenSaveThenReturnPurchaseOrder() {
		// given
		PurchaseOrder newOrder = createTestPurchaseOrder();
		PurchaseOrderDTO orderDTO = purchaseOrderService.entityToDto(newOrder);

		doReturn(newOrder).when(purchaseOrderRepository).save(any(PurchaseOrder.class));

		// when
		PurchaseOrderDTO returnedDTO = purchaseOrderService.save(orderDTO);

		// then
		assertTrue(returnedDTO.getAmount().equals(newOrder.getAmount()));
		assertTrue(returnedDTO.getProducts().get(0).getId().equals(newOrder.getProducts().get(0).getId()));
		assertTrue(returnedDTO.getCustomer().getId().equals(newOrder.getCustomer().getId()));
	}

	private PurchaseOrder createTestPurchaseOrder() {
		PurchaseOrder newOrder = new PurchaseOrder();

		Product newProduct = new Product();
		newProduct.setId(1L);
		newProduct.setName("test-product");
		newProduct.setCode(new Long(11223344));
		newProduct.setPrice(new BigDecimal("788.25"));

		Customer newCustomer = new Customer("juan.perez@tecso.coop", "Juan Perez", "20340252541");
		newCustomer.setId(1L);

		newOrder.setProducts(Arrays.asList(newProduct));
		newOrder.setCustomer(newCustomer);
		
		return newOrder;
	}

}
