package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.app.api.PurchaseTransactionApi;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;


import java.util.List;

public interface PurchaseUseCase {

    PurchaseApi save(final PurchaseApi purchase) throws ResourceNotFoundException;
    List<PurchaseTransactionApi> getTransaction(final Long idWorker);



}
