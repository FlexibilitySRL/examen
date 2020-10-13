package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.model.dao.ISellerDao;
import ar.com.plug.examen.domain.service.IPurchaseService;
import ar.com.plug.examen.domain.service.ISellerService;
import ar.com.plug.examen.domain.utils.MessageSourceProvider;
import ar.com.plug.examen.exception.BusinessException;


@Service
public class SellerServiceImpl implements ISellerService {
	
	@Autowired
	protected MessageSourceProvider messageSource;
	
	@Autowired
	private IPurchaseService purchaseService;

	@Autowired
	private ISellerDao sellerDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Seller> findAll() {
		return (List<Seller>) sellerDAO.findAll();
	}

	@Override
	@Transactional
	public Seller save(Seller seller) {
		return sellerDAO.save(seller);
	}

	@Override
	@Transactional(readOnly = true)
	public Seller findById(Long id) {
		Seller seller = sellerDAO.findById(id).orElse(null);
		if (seller == null) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.SELLER_ENTITY, id.toString()}));
		}
		return seller;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Seller seller = this.findById(id);
		this.validateSellerDelete(seller);
		sellerDAO.delete(seller);
	}
	
	private void validateSellerDelete(Seller seller) {
		List<Purchase> pendingPurchases = purchaseService.findBySellerAndStatus(seller, PurchaseStatus.PENDING);
		if (pendingPurchases != null && pendingPurchases.size() > 0) {
			throw new BusinessException(messageSource.get(BusinessExceptionConstants.CANT_DELETE_SELLER_PENDING_PURCHASES));
		}
	}
	
}
