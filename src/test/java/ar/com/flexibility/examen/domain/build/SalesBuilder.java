package ar.com.flexibility.examen.domain.build;

import java.util.Date;

import ar.com.flexibility.examen.domain.enu.SaleStatus;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Sale;
import ar.com.flexibility.examen.domain.model.Seller;

public class SalesBuilder {

	private int amount;
	private String code;
    private Date date;
    private Date dateApproved;
	private SaleStatus status;
	private double value;
	private Client client;
	private Product product;
	private Seller seller;
	
	private SalesBuilder () {
		this.amount = 5;
		this.code = "SALE500";
		this.date = new Date();
		this.dateApproved = null;
		this.status = SaleStatus.PENDIENTE;
		this.value = 50.0;
		this.client = ClientBuilder.builder().build();
		this.product = ProductBuilder.builder().build();
		this.seller = SellerBuilder.builder().build();
	}
	
	public Sale build () {
		Sale sale = new Sale();
		
		sale.setAmount(this.amount);
		sale.setClient(this.client);
		sale.setCode(this.code);
		sale.setDate(this.date);
		sale.setDateApproved(this.dateApproved);
		sale.setProduct(this.product);
		sale.setSeller(this.seller);
		sale.setStatus(this.status);
		sale.setValue(this.value);
		
		return sale;
	}
	
	public static SalesBuilder builder () {
		return new SalesBuilder();
	}
	
	
}
