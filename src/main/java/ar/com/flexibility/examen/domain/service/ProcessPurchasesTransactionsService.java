package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.PurchasesTransactions;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
public interface ProcessPurchasesTransactionsService {
	
	/**
	 * This method return information about a purchases transaction by id.
	 * 
	 * @param id
	 * @return Clients.
	 */
	public PurchasesTransactions getPurchasesTransactionsById(Long id);
	
	
	/**
	 * This method return all information about purchases transaction.
	 * 
	 * @param id
	 * @return List<Clients>.
	 */
	public List<PurchasesTransactions> getPurchasesTransactions();
	
	/**
	 * This method save a new purchases transaction in database.
	 * 
	 * @return Boolean
	 */
	public Boolean savePurchasesTransactions(PurchasesTransactions purchasesTransactions);
	
	/**
	 * This method update a new purchases transaction in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean updatePurchasesTransactions(PurchasesTransactions purchasesTransactions, Long id);
	
	/**
	 * This method delete a new purchases transaction in database by id.
	 * 
	 * @return Boolean
	 */
	public Boolean deletePurchasesTransactions(Long id);

}
