package ar.com.plug.examen.domain.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {
        if (result instanceof ResponseEntity && ((ResponseEntity<?>) result).getStatusCode().is2xxSuccessful()) {
            log.info("API Call successfully : {} at {}", joinPoint.getSignature().getName(), LocalDateTime.now());
        }
    }

    @AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", throwing = "exception")
    public void logErrorInController(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception during execution: {}, error: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }

}
