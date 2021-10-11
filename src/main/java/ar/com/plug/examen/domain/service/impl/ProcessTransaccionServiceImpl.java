package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.repository.TransaccionRepository;

@Service
public class ProcessTransaccionServiceImpl {

    @Autowired
    TransaccionRepository repo;

    public Transaccion addTransaccion(Transaccion transaccion) {
        transaccion.setIdTransaccion(0);
        return repo.save(transaccion);
    }

    public Optional<Transaccion> getTransaccion(Integer transaccion) {
        return repo.findById(transaccion);
    }

    public void delete(Integer transaccion) {
        repo.deleteById(transaccion);
    }
}
