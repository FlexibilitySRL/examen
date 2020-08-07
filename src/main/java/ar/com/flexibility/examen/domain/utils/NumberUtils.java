package ar.com.flexibility.examen.domain.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {
	
	private static final int STANDARD_DECIMALS = 2;
	
	private NumberUtils () { }

	/**
	 * Rounds a number pasing the value and decimals
	 * 
	 * @param value
	 * @return
	 */
	public static double roundNumber (double value, int decimals) {
		return BigDecimal.valueOf(value)
				.setScale(decimals, RoundingMode.DOWN)
				.doubleValue();
	}
	
	/**
	 * Rounds a number in standard format (TWO decimals)
	 * 
	 * @param value
	 * @return
	 */
	public static double roundNumber (double value) {
		return roundNumber(value, STANDARD_DECIMALS);
	}

}
