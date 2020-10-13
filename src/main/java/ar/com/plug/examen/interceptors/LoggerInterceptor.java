package ar.com.plug.examen.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpServletRequest requestCache = new ContentCachingRequestWrapper(request);
    	StringBuilder stringBuilder = new StringBuilder()
	    	.append(System.lineSeparator())
	    	.append(System.lineSeparator())
	    	.append("Procesando request con handler: " + handler.toString())
	    	.append(System.lineSeparator())
	    	.append("Request URI: " + requestCache.getRequestURI())
	    	.append(System.lineSeparator())
	    	.append("Metodo: " + requestCache.getMethod())
    		.append(System.lineSeparator());
    	logger.info(stringBuilder.toString());
        return true;
    }
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String result = this.isSuccessStatusCode(response.getStatus()) ? "SUCCESS" : "FAILURE";
    	StringBuilder stringBuilder = new StringBuilder()
	    	.append(System.lineSeparator())
	    	.append(System.lineSeparator())
	    	.append("Fin de procesamiento del request con handler: " + handler.toString())
	    	.append(System.lineSeparator())
	    	.append("Estado: " + response.getStatus())
	    	.append(System.lineSeparator())
	    	.append("Resultado: " + result)
    		.append(System.lineSeparator());
    	logger.info(stringBuilder.toString());
    }
    
    public boolean isSuccessStatusCode(int statusCode) {
    	return ((int) statusCode >= 200) && ((int) statusCode <= 299);
    }

}
