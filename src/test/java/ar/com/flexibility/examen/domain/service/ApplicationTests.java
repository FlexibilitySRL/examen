package ar.com.flexibility.examen.domain.service;

import org.junit.Test;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class ApplicationTests {
	
	Purchase purchase;
	
	@Before
	public void setUp(){
		
		purchase = new Purchase();
		
		purchase.getItems().add(new PurchaseItem(new Product(), 2, 500));
		purchase.getItems().add(new PurchaseItem(new Product(), 8, 100));
		purchase.getItems().add(new PurchaseItem(new Product(), 6, 200));
		purchase.getItems().add(new PurchaseItem(new Product(), 4, 700));
	}

	@Test
	public void purchaseAddItems() {
		assertEquals(purchase.getItems().size(), 4);		
	}
	
	@Test
	public void purchaseTotalAmount() {
		
		assertEquals(purchase.getTotalAmount(), 1500, 0);		
	}
	
	@Test
	public void purchaseTotalProductCount() {
		
		assertEquals(purchase.getTotalProductCount(), 20, 0);		
	}
	
	@Test
	public void purchaseIsApproved() {
		assertFalse(purchase.isApproved());		
	}
	
	@Test
	public void purchaseSetApproved() {
		
		purchase.setStatus("APPROVED");
		
		assertTrue(purchase.isApproved());		
	}
	
	@After
	public void clear(){
		purchase.getItems().clear();
		purchase = null;
	}

}
