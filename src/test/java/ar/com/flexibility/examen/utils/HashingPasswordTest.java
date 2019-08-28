package ar.com.flexibility.examen.utils;

import ar.com.flexibility.examen.app.utils.HashingPassword;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HashingPasswordTest {

	@Test
	public void testHashingPassword() {
		String password = "password";
		String hashedPassword = HashingPassword.hashPassword(password);
		assertTrue(HashingPassword.checkPassword(password, hashedPassword));
	}

}