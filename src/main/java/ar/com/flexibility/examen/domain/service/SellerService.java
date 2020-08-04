package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerService extends PersonService<Seller>{

	void cleanSales (Seller entity);
}
