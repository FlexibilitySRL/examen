package ar.com.plug.examen.domain.exception;

import java.util.NoSuchElementException;

import ar.com.plug.examen.app.api.ApiError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	private final String BAD_REQUEST_VALUE = "400";
	private final String NOT_FOUND_VALUE = "404";

	public CustomExceptionHandler() {
	}

	@ApiResponse(
		responseCode = BAD_REQUEST_VALUE,
		content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
		schema = @Schema(implementation = ApiError.class))})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	protected ApiError handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		return responseBody(HttpStatus.BAD_REQUEST, ex);
	}

	@ApiResponse(responseCode = BAD_REQUEST_VALUE, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	protected ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		return responseBody(HttpStatus.BAD_REQUEST, ex);
	}

	@ApiResponse(responseCode = NOT_FOUND_VALUE, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiError.class))})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {NoSuchElementException.class})
	public final ApiError handleNoElement(final NoSuchElementException ex) {
		return responseBody(HttpStatus.NOT_FOUND, ex);
	}

	private ApiError responseBody(final HttpStatus httpStatus, final Exception ex) {
		String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
		String debugMessage = ExceptionUtils.getRootCauseMessage(ex);

		return new ApiError(httpStatus.value(), message, debugMessage);
	}
}
