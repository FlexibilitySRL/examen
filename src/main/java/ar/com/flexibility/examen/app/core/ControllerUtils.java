package ar.com.flexibility.examen.app.core;

public class ControllerUtils {

	/**
	 * Small helper method to create a generic message for json response
	 * @param message
	 * @return
	 */
	public static String responseMessage(String message) {
		return String.format("{\"message\": \"%s\"}",message);
	}
}
