package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.PurchaseRequestDTO;

public interface PurchasesService {

	public Boolean buy(PurchaseRequestDTO purchase) throws Exception;
}
