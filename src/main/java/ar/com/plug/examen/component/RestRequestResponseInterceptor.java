package ar.com.plug.examen.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("restRequestInterceptor")
public class RestRequestResponseInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(RestRequestResponseInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Interceptor - REQUEST Method: " + request.getMethod() + ", URI: " + request.getRequestURI());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Interceptor - RESPONSE status: " + response.getStatus());
	}
}
