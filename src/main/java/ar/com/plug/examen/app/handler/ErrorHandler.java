package ar.com.plug.examen.app.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import ar.com.plug.examen.app.dtoResponse.ErrorDTO;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.app.exception.BusinessException;

import ar.com.plug.examen.domain.model.ErrorResponse;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {
    private static final ErrorCodeEnumeration ERROR_CODE = ErrorCodeEnumeration.INTERNAL_ERROR;
    private static final ErrorCodeEnumeration ERROR_CODE_BAD_REQUEST = ErrorCodeEnumeration.INVALID_FIELD;
    private static final String INVALID_ARGUMENTS_DESCRIPTION = "Validation Error. Verify: ";
    private static final String COMMA = ", ";
    Log log = LogFactory.getLog(this.getClass());

    @ExceptionHandler(ApiException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleApiException(final ApiException ex, final WebRequest request) {
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).status(String.valueOf(ex.getHttpStatus().value()))
                .title(ex.getDescription()).build();
        log.error("handleApiException - ApiException: " + ex.getMessage());
        log.error("handleApiException - ERROR: " + error.toString());
        return handleExceptionInternal(ex, error, ex.getHttpStatus(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleInvalidFormatException(HttpMessageNotReadableException exception,
                                                                       final WebRequest request) {
        ErrorDTO error = new ErrorDTO();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setCode(ERROR_CODE_BAD_REQUEST.getCode());
        error.setStatus(String.valueOf(status.value()));
        String croppedMessage;
        if (exception.getLocalizedMessage() == null || exception.getLocalizedMessage().contains("double-quote")) {
            croppedMessage = "Bad json body";
        } else {
            croppedMessage = cropMessage(exception.getLocalizedMessage());
        }
        error.setTitle("Validation failed. " + croppedMessage);

        log.error("handleInvalidFormatException - HttpMessageNotReadableException: " + exception.getMessage());
        log.error("handleInvalidFormatException - ERROR: " + error.toString());
        return handleExceptionInternal(exception, error, status, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleMethodValidException(MethodArgumentNotValidException exception,
                                                                     final WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = new ErrorDTO();
        error.setCode(ERROR_CODE_BAD_REQUEST.getCode());
        String parameters = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(COMMA));
        error.setTitle(INVALID_ARGUMENTS_DESCRIPTION + parameters);
        error.setStatus(String.valueOf(status.value()));

        log.error("handleMethodValidException - MethodArgumentNotValidException: " + exception.getMessage());
        log.error("handleMethodValidException - ERROR: " + error.toString());
        return handleExceptionInternal(exception, error, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex,
                                                                             final WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = ErrorDTO.builder().code(ERROR_CODE_BAD_REQUEST.getCode()).status(ERROR_CODE_BAD_REQUEST.getStatus())
                .title(ERROR_CODE_BAD_REQUEST.getMessage()).build();
        if (error == null) {
            error = new ErrorDTO(ERROR_CODE_BAD_REQUEST.getCode(), String.valueOf(status.value()), "Validation error ");
        }

        List<String> messages = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            messages.add(violation.getMessage());
        }

        error.setTitle(error.getTitle() + StringUtils.join(messages, ", "));
        log.error("handleConstraintViolationException - ConstraintViolationException: " + ex.getMessage());
        log.error("handleConstraintViolationException - ERROR: " + error.toString());
        return handleExceptionInternal(ex, error, status, request);
    }

    @ExceptionHandler(Exception.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleException(Exception exception, final WebRequest request) {

        ErrorDTO error = ErrorDTO.builder().code(ERROR_CODE.getCode()).status(ERROR_CODE.getStatus())
                .title(ERROR_CODE.getMessage()).build();
        final HttpStatus status = HttpStatus.valueOf(Integer.parseInt(ERROR_CODE.getStatus()));
        log.error("handleException - Exception: " + exception.getMessage());
        log.error("handleException - ERROR: " + error.toString());
        return handleExceptionInternal(exception, error, status, request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception, final WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = new ErrorDTO();
        error.setCode(ERROR_CODE_BAD_REQUEST.getCode());
        String parameters = exception.getParameterName();
        error.setTitle(INVALID_ARGUMENTS_DESCRIPTION + parameters);
        error.setStatus(String.valueOf(status.value()));
        log.error("handleMissingServletRequestParameterException - MissingServletRequestParameterException: " + exception.getMessage());
        log.error("handleMissingServletRequestParameterException - ERROR: " + error.toString());
        return handleExceptionInternal(exception, error, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    @Nullable
    public final ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex, final WebRequest request) {
        ErrorDTO errorDto = decodeListError(ex.getPayload());
        if (errorDto == null) {
            errorDto = decodeError(ex.getPayload());
        }
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        log.error("handleBusinessException - BusinessException: " + ex.getMessage());
        log.error("handleBusinessException - ERROR: " + errorDto.toString());
        return handleExceptionInternal(ex, errorDto, status, request);
    }

    public ErrorDTO decodeListError(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorResponse error = mapper.readValue(response, ErrorResponse.class);
            return ErrorDTO.builder().code(error.getErrors().get(0).getCode())
                    .status(error.getErrors().get(0).getStatus()).title(error.getErrors().get(0).getTitle()).build();
        } catch (JsonProcessingException e) {
            log.error("decodeListError - ERROR: " + e.getMessage());
            return null;
        }
    }

    private ErrorDTO decodeError(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorDTO error = mapper.readValue(response, ErrorDTO.class);
            return ErrorDTO.builder().code(error.getCode()).status(error.getStatus()).title(error.getTitle()).build();
        } catch (JsonProcessingException e) {
            log.error("decodeError - ERROR: " + e.getMessage());
            return null;
        }
    }

    protected ResponseEntity<ErrorDTO> handleExceptionInternal(Exception excepcion, ErrorDTO error, HttpStatus status,
                                                               final WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, excepcion, RequestAttributes.SCOPE_REQUEST);
        }
        log.error("handleExceptionInternal - Exception: " + excepcion.getMessage());
        log.error("handleExceptionInternal - ERROR: " + error.toString());
        return new ResponseEntity<>(error, status);
    }

    private String cropMessage(final String messageToCrop) {
        int[] valueCropIndexes = getValueCropIndexes(messageToCrop);
        int[] fieldCropIndexes = getFieldCropIndexes(messageToCrop);
        String valueMessage = cropString(valueCropIndexes, messageToCrop).replace(":", " is");
        String field = cropString(fieldCropIndexes, messageToCrop);
        return "Field " + field + ": " + valueMessage.replace("\"", "'");
    }

    private int[] getValueCropIndexes(final String messageToCrop) {
        int beginIndex = messageToCrop.indexOf("\"");
        int endIndex = messageToCrop.indexOf(";");
        if ((beginIndex > endIndex) || (beginIndex == 0 || endIndex == 0)) {
            beginIndex = messageToCrop.indexOf(":") + 2;
            endIndex = messageToCrop.indexOf("?") + 1;
        }
        return new int[] { beginIndex, endIndex };
    }

    private int[] getFieldCropIndexes(final String messageToCrop) {
        int beginIndex = messageToCrop.lastIndexOf("[\"") + 2;
        int endIndex = messageToCrop.lastIndexOf("\"]");
        return new int[] { beginIndex, endIndex };
    }

    private String cropString(final int[] valueCropIndexes, final String stringToCrop) {
        return stringToCrop.substring(valueCropIndexes[0], valueCropIndexes[1]);
    }

}