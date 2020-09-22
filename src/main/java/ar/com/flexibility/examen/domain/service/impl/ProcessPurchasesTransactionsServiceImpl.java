package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.IPurchasesTransactionsDao;
import ar.com.flexibility.examen.domain.model.PurchasesTransactions;
import ar.com.flexibility.examen.domain.service.ProcessPurchasesTransactionsService;

/**
 * 
 * @author Daniel Camilo 
 * Date 20-09-2020
 */
@Service
@Qualifier("ProcessPurchasesTransactionsServiceImpl")
public class ProcessPurchasesTransactionsServiceImpl implements ProcessPurchasesTransactionsService{
	
	private final Logger LOG = LoggerFactory.getLogger(ProcessPurchasesTransactionsServiceImpl.class);

	@Autowired
	private IPurchasesTransactionsDao transactionDao;
	
	@Override
	public PurchasesTransactions getPurchasesTransactionsById(Long id) {
		Optional<PurchasesTransactions> purchasesTransactions = null;
		try {
			purchasesTransactions = transactionDao.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return purchasesTransactions.get();
	}

	@Override
	public List<PurchasesTransactions> getPurchasesTransactions() {
		return (List<PurchasesTransactions>) transactionDao.findAll();
	}

	@Override
	public Boolean savePurchasesTransactions(PurchasesTransactions product) {
		Boolean resp = false;
		try {
			transactionDao.save(product);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean updatePurchasesTransactions(PurchasesTransactions product, Long id) {
		Boolean resp = false;
		try {
			product.setId(id);
			transactionDao.save(product);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}

	@Override
	public Boolean deletePurchasesTransactions(Long id) {
		Boolean resp = false;
		try {
			transactionDao.deleteById(id);
			resp = true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return resp;
	}



}
