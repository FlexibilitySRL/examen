package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Purchase;

public interface IPurchaseService extends IProcessGenericEntityService<Purchase>{
	public Purchase checkPurchase(long idPurchase, String status, String reviewer, String observations);
	public List<Purchase> getByStatus(String status);
}
