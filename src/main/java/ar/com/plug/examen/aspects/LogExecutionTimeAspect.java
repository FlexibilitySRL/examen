package ar.com.plug.examen.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LogExecutionTimeAspect {

  static Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

  @Around("execution(* ar.com.plug.examen.app.rest.*.*(..))")
  public Object logExecutionTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
    logger.info("/////// AROUND START  logExecutionTime annotation //////");
    long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long executionTime = System.currentTimeMillis() - start;
    logger.info("/////// " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
    logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
    return proceed;
  }
}
