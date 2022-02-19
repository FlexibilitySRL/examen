package ar.com.plug.examen.app.api;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response in case of and error")
public class ApiError {
	@Schema(description = "Http status code of the operation", example = "400")
	private int status;
	@Schema(description = "Pretty message for final user", example = "The value of code cannot be null")
	private String message;
	@Schema(description = "Detailed message for developers and debug purpose", example = "ConstraintKeyViolation: the field 'code' is required.")
	private String debugMessage;
}
