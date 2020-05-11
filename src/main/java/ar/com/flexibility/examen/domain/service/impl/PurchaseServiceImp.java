package ar.com.flexibility.examen.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repositories.IPurchaseRepository;
import ar.com.flexibility.examen.domain.service.IPurchaseService;

@Service
public class PurchaseServiceImp  extends ProcessGenericEntityService<Purchase> implements IPurchaseService{
	
	@Autowired
	private IPurchaseRepository repository;

	@Override
	public JpaRepository<Purchase, Long> getRepository() {
		return repository;
	}

	@Override
	public Class<PurchaseServiceImp> getClazz() {
		return PurchaseServiceImp.class;
	}

	@Override
	public String getEntityName() {
		return "PurchaseOrder";
	}

	@Override
	public Purchase checkPurchase(long idPurchase, String status, String reviewer, String observations) {
		
		Purchase purchase = repository.findOne(idPurchase);
		
		if(purchase != null){
			
			purchase.setStatus(status);
			purchase.setCheckDate(new Date());
			purchase.setReviewer(reviewer);
			purchase.setObservations(observations);
			
			repository.save(purchase);
		}
		
		return purchase;
		
	}

	@Override
	public List<Purchase> getByStatus(String status) {		
		return repository.getByStatus(status);
	}
}
