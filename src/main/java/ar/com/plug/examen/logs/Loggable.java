package ar.com.plug.examen.logs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

	String message() default "Audit Message";

}
