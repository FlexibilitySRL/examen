package ar.com.plug.examen.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogControllerImpl
{
	@Around(value = "@annotation(ar.com.plug.examen.aspect.LogController)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//before
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodUuid = UUID.randomUUID().toString();
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("uuid", methodUuid);
		parameters.put("method", signature.toString());
		int i=0;
		for (String parameterName : signature.getParameterNames()) {
			parameters.put(parameterName, joinPoint.getArgs()[i]);
			i++;
		}
		log.info(parameters);
		//execute method
		Object result = joinPoint.proceed();
		Map<String,Object> outParameters = new HashMap<>();
		outParameters.put("uuid", methodUuid);
		outParameters.put("method", signature.toString());
		outParameters.put("response", result);
		//after
		log.info(outParameters);
		return result;
	}

}
