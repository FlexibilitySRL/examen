package ar.com.plug.examen.domain.service.impl;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.Messages;
import ar.com.plug.examen.domain.service.ValidatorsService;

/**
 * @author agrauberg
 * Groups various validations for provided APIs upon which there will be CRUD operations 
 */
@Service
@Transactional
public class ValidatorsServiceImpl implements ValidatorsService {

	private final Logger logger = LoggerFactory.getLogger(ValidatorsServiceImpl.class);
	
	@Autowired
	ClientService clienteService;

	/** Validates the status update is valid 
	 * 	Also validates the transactions current state is different **/
	@Override
	public void validateTransactionStatus(Transaction transaction, StatusEnum status) throws BadRequestException {
		if (Objects.isNull(status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, "Transaction - status");
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
		if (Stream.of(StatusEnum.values()).noneMatch(item -> item == status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_INVALID_TRANSACTION_STATUS, status);
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
		
		if (transaction.getStatus().equals(status)) {
			String errorMsg = String.format(Messages.MSG_EXCEPTION_TRANSACTION_STATUS_ALREADY, status);
			logger.error(errorMsg);
			throw new BadRequestException(errorMsg);
		}
	}

	/** Given an specific object, validates that every attribute is completed 
	 * 	If skipId equals true - the "id" fields are not validated  */
	@Override
	public boolean checkCompleteObject(Object object, boolean skipId) throws BadRequestException {
		 for (Field field : object.getClass().getDeclaredFields()) {
        	try {
        		if (skipId && field.getName().contains("id")) {
        			continue;
        		}
        		Field check = object.getClass().getDeclaredField(field.getName());
        		check.setAccessible(true);
	            if (Objects.isNull(check.get(object))) {
	            	throw new BadRequestException(String.format(Messages.MSG_EXCEPTION_DATA_REQUIRED, field.getName()));
	            }
        	} catch (Exception e) {
        		logger.error(e.getMessage());
        		throw new BadRequestException(e.getMessage());
        	}
		 }

		 return true;
	}
}