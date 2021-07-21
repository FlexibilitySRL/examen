package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Transac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransacService {
    Page<Transac> findAll(Pageable pageable);
    Transac getById(Long id);
    Boolean approve(Long transacId);
}
