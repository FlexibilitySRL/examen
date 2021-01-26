package ar.com.plug.examen.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggerApi {

	private final Log log = LogFactory.getLog(LoggerApi.class);

	@Around("execution(* ar.com.plug..*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		Exception ex = null;
		Object retVal = null;

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		try {
			retVal = joinPoint.proceed();
		} catch (Exception e) {
			ex = e;
		}

		stopWatch.stop();

		StringBuffer logMessage = new StringBuffer();
		logMessage.append(joinPoint.getTarget().getClass().getSimpleName());
		logMessage.append("|");
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("(");

		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logMessage.append(String.valueOf(args[i])).append(",");
		}

		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append(")");
		if (ex != null) {
			logMessage.append("|");
			logMessage.append("Error");
		}
		logMessage.append("|");
		logMessage.append(stopWatch.getTotalTimeMillis());
		logMessage.append(" ms");
		if (ex != null) {
			log.error(logMessage.toString());
			throw ex;
		} else
			log.info(logMessage.toString());
		return retVal;

	}
}
