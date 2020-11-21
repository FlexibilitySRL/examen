package ar.com.plug.examen.domain.enums;

import java.util.Arrays;

public enum StatusEnum {

	PENDING,
	APPROVED, 
	REJECTED;

	public static boolean exists(String status) {	
		return Arrays.asList(PENDING, APPROVED, REJECTED).stream()
				.map(item -> item.toString())
				.anyMatch(item -> item.equals(status));
	}
	
}