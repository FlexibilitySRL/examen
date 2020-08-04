package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.app.exception.ServiceException;

public interface PersonService<T> {

	void delete (String identifier) throws ServiceException;

	T get (String identifier) throws ServiceException;

	List<T> list() throws ServiceException;

	void save (String identifier, String name, String surname) throws ServiceException;

	void update (String identifier, String newIdentifier, String name, String surname) throws ServiceException;

}
