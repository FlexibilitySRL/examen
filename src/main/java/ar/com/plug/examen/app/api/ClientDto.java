package ar.com.plug.examen.app.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto
{
	@NotBlank
	@Max(200)
	private String name;

	@NotBlank
	@Max(200)
	private String lastname;

	@NotBlank
	@Max(100)
	private String document;

	@NotBlank
	@Max(50)
	private String phone;

	@NotBlank
	@Email
	@Max(50)
	private String email;

	@NotNull
	Boolean active;
}
