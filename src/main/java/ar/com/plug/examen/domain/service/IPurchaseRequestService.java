package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.PurchaseDTO;
import ar.com.plug.examen.app.dtoResponse.ListPurchaseResponseDTO;
import ar.com.plug.examen.app.dtoResponse.PurchaseResponseDTO;

import java.io.IOException;

public interface IPurchaseRequestService {

    public abstract PurchaseResponseDTO create(PurchaseDTO dto)
            throws IOException;

    public abstract PurchaseResponseDTO approve(Long receiptId)
            throws IOException;

    public abstract PurchaseResponseDTO cancel(Long receiptId)
            throws IOException;

    public abstract ListPurchaseResponseDTO list()
            throws IOException;
}
