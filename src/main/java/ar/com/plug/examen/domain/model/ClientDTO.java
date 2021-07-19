package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;

}
