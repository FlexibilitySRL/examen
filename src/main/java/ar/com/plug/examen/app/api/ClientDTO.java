package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO
{
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String documentType;
	private String documentId;

}
