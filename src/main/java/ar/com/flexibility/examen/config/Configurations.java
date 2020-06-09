package ar.com.flexibility.examen.config;

import ch.qos.logback.access.servlet.TeeFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class Configurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean requestResponseFilter() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        TeeFilter filter = new TeeFilter();
        filterRegBean.setFilter(filter);
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setName("Elide Request Response Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }

/*    @Bean
    public FilterRegistrationBean requestLoggingFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new TeeFilter());
        // filterRegistrationBean.setInitParameters(...);
        return filterRegistrationBean;
    }*/


/*    @Autowired
    @Bean
    public FilterRegistrationBean requestResponseFilter() {

        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        TeeFilter filter = new TeeFilter();
        filterRegBean.setFilter(filter);
        filterRegBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegBean.setName("Request Response Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }*/

}
