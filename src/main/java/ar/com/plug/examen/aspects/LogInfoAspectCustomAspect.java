package ar.com.plug.examen.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LogInfoAspectCustomAspect {

  static Logger logger = LoggerFactory.getLogger(LogInfoAspectCustomAspect.class);

  @Before("@within(ar.com.plug.examen.aspects.LogAnnotation)")
  public void beforeMethods(JoinPoint jp) throws Throwable {
    logger.info("Executing " + jp.getSignature());
  }

  @After("@within(ar.com.plug.examen.aspects.LogAnnotation)")
  public void afterMethods(JoinPoint jp) throws Throwable {
    logger.info(jp.getSignature() + " executed Successful");
  }

}
