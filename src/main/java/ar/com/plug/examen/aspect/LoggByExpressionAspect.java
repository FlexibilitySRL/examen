package ar.com.plug.examen.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LoggByExpressionAspect {

	static Logger logger = LoggerFactory.getLogger(LoggAnnotatedClassAspect.class);

	@Before("execution(* ar.com.plug.examen.app.rest.*.*(..))")
	public void beforeMethods(JoinPoint jp) {
		logger.info("Executing " + jp.getSignature());
	}

	@After("execution(* ar.com.plug.examen.app.rest.*.*(..))")
	public void afterMethods(JoinPoint jp) {
		logger.info(jp.getSignature() + " executed Successful");
	}

	@AfterThrowing(pointcut = "execution(* ar.com.plug.examen.app.rest.*.*(..))", throwing = "excep")
	public void afterThrowing(JoinPoint jp, Throwable excep) throws Throwable {
		logger.error(jp.getSignature() + " Exception : ", excep);
	}

}
