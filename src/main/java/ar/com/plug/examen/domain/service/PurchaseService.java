package ar.com.plug.examen.domain.service;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.domain.model.Purchase;

public interface PurchaseService
{

	PageDto<Purchase> getApprovedPurchasesPageable(int pageNumber, int pageSize);

	PageDto<Purchase> getAllPurchasesPageable(int pageNumber, int pageSize);

	Purchase getPurchaseById(Long id);

	Purchase getPurchaseByReceiptNumber(String receiptNumber);

	Purchase savePurchase(PurchaseDto purchaseDto) throws ValidationException;

	Purchase updatePurchase(Long id, PurchaseDto purchaseDto) throws ValidationException;

	Purchase approvePurchase(Long id) throws ValidationException;

	Long deletePurchase(Long id) throws ValidationException;
}
