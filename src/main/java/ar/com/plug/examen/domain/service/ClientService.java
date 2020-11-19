package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface ClientService {

	List<ClientApi> listAll();

	ClientApi findById(Long id) throws NotFoundException;

	List<ClientApi> findByName(String name) throws NotFoundException;

	ClientApi save(ClientApi client) throws BadRequestException;

	void deleteById(Long id) throws NotFoundException;

	ClientApi update(ClientApi clientApi) throws NotFoundException, BadRequestException;

}