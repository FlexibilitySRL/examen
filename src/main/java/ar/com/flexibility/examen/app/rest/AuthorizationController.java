package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Transaction;
import ar.com.flexibility.examen.domain.service.impl.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorizations")
public class AuthorizationController {

    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @GetMapping
    public List<Transaction> showAuthorizations() {
        return authorizationService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public void DeleteAuthorization(@PathVariable("id") Long id) {
        authorizationService.deleteAuthorization(id);
    }
}

