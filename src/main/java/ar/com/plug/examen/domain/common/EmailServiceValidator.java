package ar.com.plug.examen.domain.common;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public interface EmailServiceValidator {

    public Predicate<String> validateEmail();

    public BiFunction<String,String,Boolean> validateEmailUpdate();
}
