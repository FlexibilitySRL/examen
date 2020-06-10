package ar.com.flexibility.examen.domain.enums;

import java.util.stream.Stream;

public interface Encodeable {

	String getCode();

	static <E extends Enum<E> & Encodeable> E forCode(Class<E> cls, String code) {
		final String t = code.trim().toUpperCase();

		return Stream.of(cls.getEnumConstants()).filter(e -> e.getCode().equalsIgnoreCase(t)).findFirst().orElseThrow(
				() -> new IllegalArgumentException("Unknown code '" + code + "' for enum " + cls.getName()));
	}
}
