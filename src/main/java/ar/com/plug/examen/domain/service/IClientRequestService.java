package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.dtoResponse.ClientResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListClientResponseDTO;

import java.io.IOException;

public interface IClientRequestService {

    public abstract ClientResponseDTO create(ClientDTO dto)
            throws IOException;

    public abstract ClientResponseDTO update(ClientDTO dto)
            throws IOException;

    public abstract ClientResponseDTO delete(Integer document)
            throws IOException;

    public abstract ListClientResponseDTO list()
            throws IOException;
}
