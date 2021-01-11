package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.SellerApi;

public interface SellerService {
	
	public SellerApi createSeller(SellerApi sellerApi);

	public SellerApi getSellerById(Long id);
	
	public List<SellerApi> listAllSellers();
	
	void removeSeller(Long id);
	
	public SellerApi updateSeller(Long id, SellerApi sellerApi);

}
