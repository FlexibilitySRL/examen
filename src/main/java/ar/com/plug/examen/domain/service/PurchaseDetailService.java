package ar.com.plug.examen.domain.service;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDetailDto;
import ar.com.plug.examen.domain.model.PurchaseDetail;

public interface PurchaseDetailService
{
	PageDto<PurchaseDetail> getDetailsByPurchaseIdPageable(Long purchaseId, int pageNumber, int pageSize);

	PageDto<PurchaseDetail> getDetailsByProductIdPageable(Long productId, int pageNumber, int pageSize);

	PageDto<PurchaseDetail> getAllDetailsPageable(int pageNumber, int pageSize);

	PurchaseDetail getPurchaseDetailById(Long id);

	PurchaseDetail saveDetail(PurchaseDetailDto detailDto) throws ValidationException;

	PurchaseDetail updateDetail(Long id, PurchaseDetailDto detailDto) throws ValidationException;

	Long deletePurchaseDetail(Long id) throws ValidationException;
}
