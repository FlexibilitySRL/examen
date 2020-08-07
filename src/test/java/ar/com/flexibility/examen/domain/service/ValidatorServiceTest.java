package ar.com.flexibility.examen.domain.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.app.exception.ServiceException;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.service.impl.ValidatorServiceImpl;

@RunWith(SpringRunner.class)
public class ValidatorServiceTest {

	// ---------------
	// Mocks
	// ---------------
	@Mock
	private MessagesProps messages;
	
	@InjectMocks
	private ValidatorServiceImpl validatorService;

	// ---------------
	// Methods
	// ---------------	
	@Test (expected = ServiceException.class)
	public void validateStringFieldsError () throws ServiceException {
		// Arrange 
		String fieldOne = "TEST";
		String fieldTwo = "";
		
		// Action
		this.validatorService.validateStringFields(fieldOne, fieldTwo);
		
		// Assert
	}
	
	@Test
	public void validateStringFieldsSuccess () throws ServiceException {
		// Arrange 
		String fieldOne = "TEST";
		String fieldTwo = "FLEX";
		
		// Action
		this.validatorService.validateStringFields(fieldOne, fieldTwo);
		
		// Assert
		verify (this.messages, times(0)).getIncompleteFields();
	}
	
}