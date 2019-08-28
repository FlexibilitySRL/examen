package ar.com.flexibility.examen.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

	private String name;
	private String lastname;
	private String email;
	private String mobile;
	private String currentPassword;
	private String newPassword;

}
