package ar.com.plug.examen.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    /// CUSTOM  POINTCUT ///
    @Pointcut("execution(* ar.com.plug.examen.app.rest.*.*(..))")
    public void methodsStarterServicePointcut() {
        //No behaviour method
    }

    @Around("methodsStarterServicePointcut()")
    public void beforeMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("### START ###" +
                        "CLASS NAME : " + joinPoint.getSignature().getDeclaringTypeName() +
                " - METHOD : " + joinPoint.getSignature().getName()
                );

        joinPoint.proceed();

        logger.info("### END SUCCESSFUL ###" +
                "CLASS NAME : " + joinPoint.getSignature().getDeclaringTypeName() +
                " - METHOD : " + joinPoint.getSignature().getName()
        );

    }
}
