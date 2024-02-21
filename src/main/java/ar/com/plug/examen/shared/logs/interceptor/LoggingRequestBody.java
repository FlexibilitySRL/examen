package ar.com.plug.examen.shared.logs.interceptor;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import ar.com.plug.examen.shared.logs.LogDTO;
import ar.com.plug.examen.shared.logs.LogUtils;
import ar.com.plug.examen.shared.logs.service.LoggingService;



@ControllerAdvice
public class LoggingRequestBody implements RequestBodyAdvice {
    private static final String LOG_TYPE = "Request";
    private LoggingService loggingService;
    protected HttpServletRequest httpServletRequest;

    public LoggingRequestBody(LoggingService loggingService, HttpServletRequest request) {
        this.loggingService = loggingService;
        this.httpServletRequest = request;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        String method = httpServletRequest.getMethod();
        loggingService.logData(
                LogDTO.builder().eventType(LOG_TYPE).headers(LogUtils.getHeaders(httpServletRequest)).payload(body).method(method).build());
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
