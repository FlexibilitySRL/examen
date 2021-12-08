package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.PurchaseService;

@Service
public class PurchaseImpl implements PurchaseService{

	@Autowired
	PurchaseRepository purchaseRepository;

	@Override
	public List<Purchase> consultar() {
		// TODO Auto-generated method stub
		return (List<Purchase>) purchaseRepository.findAll();
	}

	@Override
	public void crear(Purchase purchase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aprobarCompra(Long idCompra) {
		List<Purchase> purchaselist = purchaseRepository.findByidCompra(idCompra);
		purchaselist.stream().forEach((p)-> {
			p.setEstado_Transaccion("APROB");
			});
		purchaseRepository.saveAll(purchaselist);
		
	}
	
	
}
