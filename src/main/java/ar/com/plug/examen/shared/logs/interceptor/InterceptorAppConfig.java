package ar.com.plug.examen.shared.logs.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer {

    private LoggingGetInterceptor customLoggingInterceptor;

    public InterceptorAppConfig(LoggingGetInterceptor customLoggingInterceptor) {
        this.customLoggingInterceptor = customLoggingInterceptor;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customLoggingInterceptor);

    }
}
