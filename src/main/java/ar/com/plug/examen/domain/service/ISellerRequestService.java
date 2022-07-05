package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.app.dtoResponse.SellerResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListSellerResponseDTO;

import java.io.IOException;

public interface ISellerRequestService {

    public abstract SellerResponseDTO create(SellerDTO dto)
            throws IOException;

    public abstract SellerResponseDTO update(SellerDTO dto)
            throws IOException;

    public abstract SellerResponseDTO delete(Integer document)
            throws IOException;

    public abstract ListSellerResponseDTO list()
            throws IOException;
}
