package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Purchase;

public interface PurchaseService {
	
	public List<Purchase> consultar();
	public void crear(Purchase purchase);
	public void aprobarCompra(Long idCompra);

}
