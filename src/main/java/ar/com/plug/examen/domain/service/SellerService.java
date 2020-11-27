package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Seller;

public interface SellerService {

	Seller create(Seller seller);
	Seller update(Long id, Seller seller);
	void delete(Long id);
	List<Seller> getSellers();
	Optional<Seller> getSellerById(Long id);
}
