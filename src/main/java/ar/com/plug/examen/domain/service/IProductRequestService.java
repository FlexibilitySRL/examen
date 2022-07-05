package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.dtoResponse.ProductResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListProductResponseDTO;

import java.io.IOException;
import java.util.List;

public interface IProductRequestService {

    public abstract ProductResponseDTO create(ProductDTO dto)
            throws IOException;

    public abstract ProductResponseDTO update(ProductDTO dto)
            throws IOException;

    public abstract ProductResponseDTO delete(String productCode)
            throws IOException;

    public abstract ListProductResponseDTO list()
            throws IOException;
}
