package ar.com.plug.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

	private Long id;
	private String name;
	private String lastname;
	private String email;
}
