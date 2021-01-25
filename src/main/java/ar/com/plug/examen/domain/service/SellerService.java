package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Seller;

public interface SellerService {

	public List<Seller> getAllSeller();

	public Seller getSellerById(Long id);

	public void deleteSeller(Long id);

	public Seller create(Seller seller);
	
	public Seller update(Seller seller);
	
}
