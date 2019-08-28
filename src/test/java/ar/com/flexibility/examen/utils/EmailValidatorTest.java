package ar.com.flexibility.examen.utils;

import ar.com.flexibility.examen.app.utils.EmailValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTest {

	@Test
	public void testValidEmailValidator() {
		String validEmail = "idclip666hell@gmail.com";
		assertTrue(EmailValidator.validate(validEmail));
	}

	@Test
	public void testInvalidEmailValidator() {
		String invalidEmail = "123";
		assertFalse(EmailValidator.validate(invalidEmail));
	}
}