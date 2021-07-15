package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {

	private int errorCode;
	private String errorMessage;
	private String errorDetails;
}