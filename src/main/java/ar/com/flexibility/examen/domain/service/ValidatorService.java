package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ServiceException;

public interface ValidatorService {

	void validateStringFields (String ... arg) throws ServiceException;
	
}
