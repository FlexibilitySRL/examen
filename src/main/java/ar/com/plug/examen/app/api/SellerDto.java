package ar.com.plug.examen.app.api;

import javax.persistence.Column;
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
public class SellerDto
{
	@NotBlank
	@Max(100)
	private String code;

	@NotBlank
	@Max(100)
	private String document;

	@NotBlank
	@Max(300)
	private String description;

	@NotNull
	private Boolean active;
}
