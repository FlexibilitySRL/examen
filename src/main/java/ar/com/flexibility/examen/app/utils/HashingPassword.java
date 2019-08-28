package ar.com.flexibility.examen.app.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashingPassword {

	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public static boolean checkPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

}
