package ar.com.flexibility.examen.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {

    public Integer getCurrentAuditor() {
      
		return 1;
	}

}
