package ar.com.flexibility.examen.domain.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.ValidatorService;

@Service ("validatorService")
public class ValidatorServiceImpl implements ValidatorService {

	// ---------------
	// Logger
	// ---------------
	private final Logger logger = LogManager.getLogger(ValidatorServiceImpl.class);

	// ---------------
	// Attributes
	// ---------------
	@Autowired
	private MessagesProps messages;

	// ---------------
	// Methods
	// ---------------
	@Override
	public void validateStringFields (String ... fields) throws ServiceException {
		logger.warn("Validating String fields");
		for (String field : fields) {
			if (Strings.isNullOrEmpty(field)) {
				logger.error("Invalid String fields");
				throw new ServiceException(this.messages.getIncompleteFields());
			}
		}
	}
}
