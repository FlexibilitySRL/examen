package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.api.PurchasesApi;

public interface ProcessPurchasesService {

	PurchasesApi create(PurchasesApi purchasesApi);

	List<PurchasesApi> search();

}
