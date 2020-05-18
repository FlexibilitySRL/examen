package ar.com.flexibility.examen.domain.dto;

import lombok.Data;
import lombok.ToString;

/**
 * A DTO for {@link ar.com.flexibility.examen.domain.service.impl.CustomerService} requests body
 */
@Data
@ToString(callSuper = true)
public class CustomerDTO {

	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String phoneNumber;
	private String email;

}
