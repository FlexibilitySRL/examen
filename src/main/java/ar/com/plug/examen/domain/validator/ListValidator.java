package ar.com.plug.examen.domain.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.exception.ValidatorException;


public class ListValidator {

	private final List<Validator> validators;
	
	public ListValidator(List<Validator> validators) {
		this.validators = validators;
	}
	public ListValidator(Validator validator) {
		this.validators = new ArrayList<>();
		this.validators.add(validator);	
	}
	
	public void validate(Object values) throws ValidatorException {
		List<String> errors = new ArrayList<>();
		for(Validator validator : validators) {
			String result = validator.validate(values);
			if(result != null)
				errors.add(result);
		}
		
		if(!errors.isEmpty())
			throw new ValidatorException(errors.toString());
		
	}
	
}
