package ar.com.plug.examen.domain.common.impl;

import ar.com.plug.examen.domain.common.EmailServiceValidator;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Predicate;

@Component
@Log4j2
public class EmailServiceValidatorImpl implements EmailServiceValidator {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Predicate<String> validateEmail() {
        log.info("get the amount of email");
        return email -> repository.countEmail(email) >= 1;
    }

    @Override
    public BiFunction<String,String,Boolean> validateEmailUpdate() {
        log.info("get the amount of email 'update'");
        return (oldEmail, newEmail) -> {
            if(oldEmail.equals(newEmail))
                return false;

            return repository.countEmail(newEmail) >= 1;
        };
    }
}
