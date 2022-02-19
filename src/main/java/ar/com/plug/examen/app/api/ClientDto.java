package ar.com.plug.examen.app.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representation of client's data.")
public class ClientDto
{
	@Schema(description = "Legal name of the client", example = "Clark")
	@NotBlank
	@Max(200)
	private String name;

	@Schema(description = "Lastname of the client", example = "Kent")
	@NotBlank
	@Max(200)
	private String lastname;

	@Schema(description = "Document number of the client.", example = "2406769")
	@NotBlank
	@Max(100)
	private String document;

	@Schema(description = "Phone number of the client.", example = "+5 1123789")
	@NotBlank
	@Max(50)
	private String phone;

	@Schema(description = "Email of the client", example = "ckent@kripton.com")
	@NotBlank
	@Email
	@Max(50)
	private String email;

	@Schema(description = "Indicates if the client is active to be used for a purchase", example = "true")
	@NotNull
	Boolean active;
}
