package ar.com.flexibility.examen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:constants.properties")
public class ConstantsProps {

	// ---------------
	// Attributes
	// ---------------
	@Value("${exception.error}")
	private String exceptionError;
	@Value("${product.max.amount}")
	private int productMaxAmount;
	@Value("${product.max.price}")
	private double productMaxPrice;
	@Value("${product.min.amount}")
	private int productMinAmount;
	@Value("${product.min.price}")
	private double productMinPrice;
	
	// ---------------
	// Constructor
	// ---------------

	// ---------------
	// Methods
	// ---------------
	public String getExceptionError() {
		return exceptionError;
	}
	public int getProductMaxAmount() {
		return productMaxAmount;
	}
	public double getProductMaxPrice() {
		return productMaxPrice;
	}
	public int getProductMinAmount() {
		return productMinAmount;
	}
	public double getProductMinPrice() {
		return productMinPrice;
	}
	
}