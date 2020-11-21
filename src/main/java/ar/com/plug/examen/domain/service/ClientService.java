package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface ClientService {

	List<ClientApi> listAll();

	ClientApi findById(long id) throws NotFoundException;

	List<ClientApi> findByName(String name);

	ClientApi save(ClientApi client) throws BadRequestException;

	void deleteById(long id) throws NotFoundException, BadRequestException;

	ClientApi update(ClientApi clientApi) throws NotFoundException, BadRequestException;

}