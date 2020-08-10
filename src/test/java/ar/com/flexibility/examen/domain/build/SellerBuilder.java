package ar.com.flexibility.examen.domain.build;

import ar.com.flexibility.examen.domain.model.Seller;

public class SellerBuilder {
	
	private String identifier;
	private String name;
	private String surname;

	private SellerBuilder () {
		this.identifier = "C123456";
		this.name = "Seller Name";
		this.surname = "Seller Surname";
	}

	public Seller build() {
		Seller seller = new Seller ();
		
		seller.setIdentifier(this.identifier);
		seller.setName(this.name);
		seller.setSurname(this.surname);
		
		return seller;
	}
	
	public static SellerBuilder builder() {
		return new SellerBuilder();
	}
	
}
