package ar.com.plug.examen.domain.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.Enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.ValidatorService;

@Service
public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public void validateTransactionStatus(Transaction transaction, TransactionStatusEnum status)
			throws BadRequestException {
		
		if (Objects.isNull(status)) {
			String errorMsg = String.format("Missing required data: %1$s" , "Transaction - status");
			throw new BadRequestException(errorMsg);
		}

		if (transaction.getStatus().equals(status)) {
			String errorMsg = String.format("Provided transaction status is invalid: %1$s", status);
			throw new BadRequestException(errorMsg);
		}
		
	}

	@Override
	public boolean checkCompleteObject(Object object, boolean skipId) throws BadRequestException {
		// TODO implement
		return true;
	}

}
